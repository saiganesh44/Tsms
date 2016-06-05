package com.codestub.tsms.contact;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.ContactsContract;

/**
 * This class is a pojo class which maintains contacts information
 * Created by ganesh on 29/4/16.
 */
public class Contact {

    private Context context;
    private String address, displayName;
    private boolean hasPhoneNumber;
    private String phoneNumber;
    private long contactID;
    private Bitmap bitmapPhoto;
    private String type;

    public Contact(Context context, String address) {
        this.context = context;
        this.address = address;
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

    public Bitmap getBitmapPhoto() {
        return bitmapPhoto;
    }

    public String getPhoneNumber() {
        if(phoneNumber != null){
            return phoneNumber;
        }
        if(hasPhoneNumber) {
            Cursor c = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{String.valueOf(getContactID())}, null);
            try {
                if (c.moveToNext()) {
                    String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    this.setPhoneNumber(number);
                    this.setType(getType(c));
                }
            }catch (Exception e) {
                //Do nothing
            } finally {
                if(c != null) {
                    c.close();
                }
            }
        }
        return this.phoneNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String getType(Cursor c) {
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
}
