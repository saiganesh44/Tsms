package com.codestub.tsms.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * This class is used for conversion operations
 * Created by ganesh on 30/4/16.
 */
public class ConversionUtils {

    /**
     * Converts dp to px
     * @param context from the activity
     * @param dp the display independent pixel value
     * @return pixel equal of the supplied dp
     */
    public static float dpTopx(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }
}
