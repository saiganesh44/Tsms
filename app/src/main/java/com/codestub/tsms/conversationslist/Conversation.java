package com.codestub.tsms.conversationslist;

import android.content.Context;
import android.database.Cursor;

import com.codestub.tsms.utils.DateUtils;

import java.util.Date;

/**
 * This is a POJO class for Conversation
 * Created by ganesh on 20/4/16.
 */
public class Conversation {
    private String thread_id, contactName, senderPhNo, date, abstractConvo;

    public Conversation(String senderPhNo, String abstractConvo) {
        this.senderPhNo = senderPhNo;
        this.abstractConvo = abstractConvo;
    }

    public Conversation(Context context, Cursor cursor) {
        this.thread_id = cursor.getString(0);
        this.senderPhNo = cursor.getString(1);
        this.abstractConvo = cursor.getString(2);
        this.date = DateUtils.getDate(cursor.getString(3));
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
}
