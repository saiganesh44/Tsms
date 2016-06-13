package com.codestub.tsms.contact;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.ContactsContract;

import com.codestub.tsms.utils.BitmapUtils;
import com.codestub.tsms.utils.BuildUtils;

/**
 * This class is a pojo class which maintains contacts information
 * Created by ganesh on 29/4/16.
 */
public class Contact {

    private Context context;
    private String displayName;
    private boolean hasPhoneNumber;
    private String phoneNumber;
    private long contactID;
    private Bitmap bitmapPhoto;
    private Bitmap circularBitmapPhoto;
    private String type;

    public Contact(Context context, String address) {
        this.context = context;
        this.phoneNumber = address;
        //by default assign address to displayName
        this.displayName = address;
        ContactUtils.fillup(context, address, this);
    }

    /**
     * To be called from ContactsAdapter
     * @param cursor of contacts
     */
    public Contact(Context context, Cursor cursor) {
        this.context = context;
        this.displayName = cursor.getString(ContactsQuery.DISPLAY_NAME);
        this.contactID = cursor.getLong(ContactsQuery.ID);
        this.hasPhoneNumber = Integer.parseInt(cursor.getString(ContactsQuery.HAS_PHONE_NUMBER)) > 0;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean hasPhoneNumber() {
        return this.hasPhoneNumber;
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

    public void setCircularBitmapPhoto(Bitmap bitmap) {
        this.circularBitmapPhoto = bitmap;
    }

    public Bitmap getBitmapPhoto() {
        if(bitmapPhoto != null) {
            return bitmapPhoto;
        }
        //Make sure this has to be run on background task
        ContactUtils.setContactPhoto(this);
        return bitmapPhoto;
    }

    public Bitmap getCircularBitmapPhoto() {
        if(circularBitmapPhoto != null) {
            return circularBitmapPhoto;
        } else if(bitmapPhoto != null){
            this.circularBitmapPhoto = BitmapUtils.getCircularBitmap(bitmapPhoto);
            return circularBitmapPhoto;
        } else {
            //as default return bitmapPhoto
            return null;
        }
    }

    public Context getContext() {
        return context;
    }

    public String getPhoneNumber() {
        if(phoneNumber != null){
            return phoneNumber;
        }
        //Make sure this has to be run on as background task as it would have an performance impact if run
        // on UI
        if(hasPhoneNumber) {
            ContactUtils.setPhoneNumberAndType(this);
        }
        return phoneNumber;
    }

    public void setType(String type) {
        this.type = type;
    }


}
