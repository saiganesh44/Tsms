package com.codestub.tsms.conversationslist;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Telephony;

import com.codestub.tsms.Permissions;
import com.codestub.tsms.cache.Cache;
import com.codestub.tsms.model.BasicProvider;
import com.codestub.tsms.utils.PermissionUtils;

/**
 * This class provides data for conversations list
 * Created by ganesh on 23/4/16.
 */
public class ConversationsListProvider extends BasicProvider<ConversationTile>{

    private Activity activity;
    private Cursor cursor;
    private Cache cache;
    private String[] threadIDs;

    public ConversationsListProvider(Activity activity) {
        super(activity);
        this.activity = activity;
        this.initCursor();
        cache = Cache.getInstance(getClass().getName());
        threadIDs = new String[this.size()];
        this.loadAllConversations();
    }

    protected Cursor getCursor() {
        return cursor;
    }

    protected void initCursor() {
        if(!PermissionUtils.isGranted(activity, Permissions.READ_SMS_PERMISSION)) {
            return;
        }
        ContentResolver contentResolver = activity.getContentResolver();
        cursor = contentResolver.query(Telephony.Threads.CONTENT_URI, new String[]{Telephony.TextBasedSmsColumns.THREAD_ID, Telephony.TextBasedSmsColumns.ADDRESS, Telephony.TextBasedSmsColumns.BODY, Telephony.TextBasedSmsColumns.DATE}, null, null, Telephony.ThreadsColumns.DATE + " DESC");
    }

    /**
     *
     * @param position of the item
     * @return the conversation object
     */
    public ConversationTile getItem(int position) {
        return (ConversationTile) cache.get(threadIDs[position]);
    }

    private void loadAllConversations() {
        int i=0;
        if(cursor.moveToNext()) {
            do {
                threadIDs[i] = cursor.getString(0);
                cache.put(cursor.getString(0),new ConversationTile(activity, cursor));
                i++;
            } while(cursor.moveToNext());
        }
    }
}
