package com.codestub.tsms.contact;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
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

            Uri contactURI = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contact.getContactID());
            Uri photoURI = Uri.withAppendedPath(contactURI, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
            Cursor photoCursor = context.getContentResolver().query(photoURI, new String[] {ContactsContract.Contacts.Photo.PHOTO}, null, null, null);
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
    }
}
