package com.codestub.tsms.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.codestub.tsms.Permissions;
import com.codestub.tsms.RequestCodes;

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
}
