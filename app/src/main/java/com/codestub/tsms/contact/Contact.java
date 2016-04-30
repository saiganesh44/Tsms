package com.codestub.tsms.contact;

import android.app.Activity;
import android.graphics.Bitmap;

/**
 * This class is a pojo class which maintains contacts information
 * Created by ganesh on 29/4/16.
 */
public class Contact {

    private Activity activity;
    private String address, displayName;
    private long contactID;
    private Bitmap bitmapPhoto;

    public Contact(Activity activity, String address) {
        this.activity = activity;
        this.address = address;
        //by default assign address to displayName
        this.displayName = address;
        ContactUtils.fillup(activity, address, this);
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    protected void setContactID (long contactID) {
        this.contactID = contactID;
    }

    protected long getContactID() {
        return this.contactID;
    }

    protected void setBitmapPhoto(Bitmap bitmapPhoto) {
        this.bitmapPhoto = bitmapPhoto;
    }

    public Bitmap getBitmapPhoto() {
        return bitmapPhoto;
    }
}
