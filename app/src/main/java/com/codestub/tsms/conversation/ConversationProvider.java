package com.codestub.tsms.conversation;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Telephony;

import com.codestub.tsms.Permissions;
import com.codestub.tsms.cache.Cache;
import com.codestub.tsms.model.BasicProvider;
import com.codestub.tsms.utils.PermissionUtils;

/**
 * Provider class for conversations among two entities
 * Created by ganesh on 14/5/16.
 */
public class ConversationProvider extends BasicProvider<ConversationBubble> {

    private Activity activity;
    private Cursor cursor;
    private String threadID;
    private Cache cache;

    public ConversationProvider(Activity activity, String threadID) {
        super(activity);
        this.activity = activity;
        this.threadID = threadID;
        this.cache = Cache.getInstance(getClass().getName() + this.threadID);
        this.initCursor();
        this.loadWholeConversation();
    }

    protected Cursor getCursor() {
        return cursor;
    }

    protected void initCursor() {
        if(!PermissionUtils.isGranted(activity, Permissions.READ_SMS_PERMISSION)) {
            return;
        }
        ContentResolver contentResolver = activity.getContentResolver();
        cursor = contentResolver.query(Telephony.Sms.CONTENT_URI, new String[]{Telephony.TextBasedSmsColumns.BODY, Telephony.TextBasedSmsColumns.TYPE}, Telephony.TextBasedSmsColumns.THREAD_ID + "=" + threadID, null, null);
    }

    @Override
    public ConversationBubble getItem(int position) {
        return (ConversationBubble) cache.get(String.valueOf(position));
    }

    private void loadWholeConversation() {
        int i = 0;
        if(cursor.moveToNext()) {
            do {
                cache.put(String.valueOf(i), new ConversationBubble(activity, cursor));
                i++;
            } while(cursor.moveToNext());
        }
    }
}
