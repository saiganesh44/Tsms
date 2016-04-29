package com.codestub.tsms.conversationslist;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Telephony;
import android.util.Log;

import com.codestub.tsms.Permissions;
import com.codestub.tsms.RequestCodes;
import com.codestub.tsms.utils.PermissionUtils;

/**
 * This class provides data for conversations list
 * Created by ganesh on 23/4/16.
 */
public class ConversationsListProvider {

    private Activity activity;
    private Cursor cursor;

    public ConversationsListProvider(Activity activity) {
        this.activity = activity;
        this.initCursor();
    }

    public void initCursor() {
        if(!PermissionUtils.isGranted(activity, Permissions.READ_SMS_PERMISSION)) {
            return;
        }
        ContentResolver contentResolver = activity.getContentResolver();
        cursor = contentResolver.query(Telephony.Threads.CONTENT_URI, new String[]{Telephony.TextBasedSmsColumns.THREAD_ID, Telephony.TextBasedSmsColumns.ADDRESS, Telephony.TextBasedSmsColumns.BODY, Telephony.TextBasedSmsColumns.DATE}, null, null, Telephony.ThreadsColumns.DATE+ " DESC");
    }

    /**
     *
     * @param position of the item
     * @return the conversation object
     */
    public Conversation getItem(int position) {
        cursor.moveToPosition(position);
        return new Conversation(activity, cursor);
    }

    /**
     *
     * @return the number of items in the cursor
     */
    public int size() {
        if(cursor != null) {
            return cursor.getCount();
        } else {
            return 0;
        }
    }
}
