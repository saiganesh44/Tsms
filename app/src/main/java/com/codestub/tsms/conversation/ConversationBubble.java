package com.codestub.tsms.conversation;

import android.app.Activity;
import android.database.Cursor;
import android.provider.Telephony;

/**
 * POJO class for conversation bubble
 * Created by ganesh on 15/5/16.
 */
public class ConversationBubble {

    String body;
    String type;
    boolean isMe = false;

    public ConversationBubble(Activity activity, Cursor cursor) {
        body = cursor.getString(0);
        type = cursor.getString(1);
        init();
    }

    private void init() {
        int typeInt = Integer.valueOf(type);
        if(typeInt == Telephony.TextBasedSmsColumns.MESSAGE_TYPE_DRAFT
                || typeInt == Telephony.TextBasedSmsColumns.MESSAGE_TYPE_FAILED
                || typeInt == Telephony.TextBasedSmsColumns.MESSAGE_TYPE_OUTBOX
                || typeInt == Telephony.TextBasedSmsColumns.MESSAGE_TYPE_QUEUED
                || typeInt == Telephony.TextBasedSmsColumns.MESSAGE_TYPE_SENT) {
            isMe = true;
        }
    }

    public String getBody() {
        return  body;
    }

    public boolean isMe() {
        return isMe;
    }
}
