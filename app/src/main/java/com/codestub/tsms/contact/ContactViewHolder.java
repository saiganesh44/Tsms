package com.codestub.tsms.contact;

import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Contact View Holder POJO Class
 * Created by ganesh on 28/5/16.
 */
public class ContactViewHolder {
    TextView displayName;
    TextView number;
    ImageView contactPhoto;

    WeakReference<AsyncTask> asyncTaskReference;

    public void setAsyncTaskReference(AsyncTask task) {
        asyncTaskReference = new WeakReference<>(task);
    }

    public AsyncTask getAsyncTask() {
        return asyncTaskReference.get();
    }
}
