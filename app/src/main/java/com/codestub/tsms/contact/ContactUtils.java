package com.codestub.tsms.contact;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import com.codestub.tsms.Permissions;
import com.codestub.tsms.utils.PermissionUtils;
import com.codestub.tsms.utils.StringUtils;

import java.io.ByteArrayInputStream;

/**
 * This class helps app to read contacts data
 * Created by ganesh on 28/4/16.
 */
public class ContactUtils {

    public static String getContactName(Activity activity, String address) {
        if(!PermissionUtils.isGranted(activity, Permissions.READ_CONTACTS_PERMISSION)) {
            return address;
        }
        if(StringUtils.isEmpty(address)) {
            return address;
        }

        String name = address;

        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(address));
        ContentResolver resolver = activity.getApplicationContext().getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if(cursor != null) {
            if (cursor.moveToNext()) {
                name = cursor.getString(0);
            }
            cursor.close();
        }
        return name;
    }

    public static void setPhoneNumberAndType(Contact contact) {
        Cursor c = contact.getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{String.valueOf(contact.getContactID())}, null);
        try {
            if (c != null && c.moveToNext()) {
                String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contact.setPhoneNumber(number);
                contact.setType(getType(c));
            }
        }catch (Exception e) {
            //Do nothing
        } finally {
            if(c != null) {
                c.close();
            }
        }
    }

    private static String getType(Cursor c) {
        int type = c.getInt(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
        String returnType = "";
        switch(type) {
            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME :
                returnType = "Home";
                break;
            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE :
                returnType = "Mobile";
                break;
            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK :
                returnType = "Work";
                break;
            case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER :
                returnType = "Other";
                break;
            case ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM :
                returnType = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LABEL));
                break;
        }
        return returnType;
    }

    public static void setContactPhoto(Contact contact) {
        Uri contactURI = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contact.getContactID());
        Uri photoURI = Uri.withAppendedPath(contactURI, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        Cursor photoCursor = contact.getContext().getContentResolver().query(photoURI, new String[] {ContactsContract.Contacts.Photo.PHOTO}, null, null, null);
        if (photoCursor != null) {
            try {
                if (photoCursor.moveToFirst()) {
                    byte[] data = photoCursor.getBlob(0);
                    if (data != null) {
                        contact.setBitmapPhoto(BitmapFactory.decodeStream(new ByteArrayInputStream(data)));
                    }
                }
            } finally {
                photoCursor.close();
            }
        }
    }

    protected static void fillup(Context context, String address, Contact contact) {
        //by making this protected we are eliminating the need to check if obj is null
        if(PermissionUtils.isGranted(context, Permissions.READ_CONTACTS_PERMISSION) && !StringUtils.isEmpty(address)) {
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(address));
            ContentResolver resolver = context.getContentResolver();
            Cursor cursor = resolver.query(uri, new String[]{ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
            if(cursor != null) {
                try {
                    if (cursor.moveToNext()) {
                        contact.setContactID(cursor.getLong(0));
                        contact.setDisplayName(cursor.getString(1));
                    }
                } finally {
                    cursor.close();
                }
            }

            setContactPhoto(contact);
        }
    }
}
