package com.codestub.tsms.appbar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

/**
 * Created by ganesh on 17/4/16.
 * This class is used for customizing AppBar
 */
public class TsmsAppBar extends Toolbar{

    public TsmsAppBar(Context context) {
        super(context);
        super.setTitle("");
    }

    public TsmsAppBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        super.setTitle("");
    }

    public TsmsAppBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setTitle("");
    }



}
