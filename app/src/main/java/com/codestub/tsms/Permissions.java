package com.codestub.tsms;

import android.Manifest;

/**
 * This interface holds all the Permissions required by this app
 * Created by ganesh on 29/4/16.
 */
public interface Permissions {
    String READ_SMS_PERMISSION = Manifest.permission.READ_SMS;
    String READ_CONTACTS_PERMISSION = Manifest.permission.READ_CONTACTS;
}
