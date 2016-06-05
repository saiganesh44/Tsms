package com.codestub.tsms.utils;

import android.os.Build;

/**
 * Utils class for build related method
 * Created by ganesh on 28/5/16.
 */
public class BuildUtils {

    public static boolean hasHoneyComb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }
}
