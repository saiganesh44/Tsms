package com.codestub.tsms.conversationslist;

import android.app.Activity;
import android.database.Cursor;

import com.codestub.tsms.contact.Contact;
import com.codestub.tsms.contact.ContactUtils;
import com.codestub.tsms.utils.DateUtils;

/**
 * This is a POJO class for ConversationTile
 * Created by ganesh on 20/4/16.
 */
public class ConversationTile {
    private String threadID, contactName, address, senderPhNo, date, abstractConvo;
    private Contact contact;

    public ConversationTile(String senderPhNo, String abstractConvo) {
        this.senderPhNo = senderPhNo;
        this.address = senderPhNo;
        this.abstractConvo = abstractConvo;
    }

    public ConversationTile(Activity activity, Cursor cursor) {
        this.threadID = cursor.getString(0);
        this.abstractConvo = cursor.getString(2);
        this.date = DateUtils.getDate(cursor.getString(3));
        this.address = cursor.getString(1);
        this.contact = new Contact(activity, this.address);
        this.senderPhNo = this.contact.getDisplayName();
    }

    public void setSenderPhNo(String senderPhNo) {
        this.senderPhNo = senderPhNo;
    }

    public String getSenderPhNo() {
        return senderPhNo;
    }

    public void setAbstractConvo(String abstractConvo) {
        this.abstractConvo = abstractConvo;
    }

    public String getAbstractConvo() {
        return abstractConvo;
    }

    public void setDate(String date) { this.date = date;}

    public String getDate() {return date;}

    public Contact getContact() {
        return contact;
    }

    public String getThreadID() {
        return threadID;
    }
}
