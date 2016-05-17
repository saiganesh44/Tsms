package com.codestub.tsms.model;

import android.app.Activity;
import android.database.Cursor;

/**
 * This class provides the skeleton for the providers of this project
 * Created by ganesh on 15/5/16.
 */
public abstract class BasicProvider<T> {

    private Activity activity;

    public BasicProvider(Activity activity) {
        this.activity = activity;
    }

    /**
     *
     * @return the number of items in the cursor
     */
    public int size() {
        if(getCursor() != null) {
            return getCursor().getCount();
        } else {
            return 0;
        }
    }

    protected abstract Cursor getCursor();

    protected abstract void initCursor();

    public abstract T getItem(int position);
}
