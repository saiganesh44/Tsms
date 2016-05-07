package com.codestub.tsms.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * This class is used for bitmap operations
 * Created by ganesh on 30/4/16.
 */
public class BitmapUtils {

    public static Bitmap getLetteredAvatar(Context context, char c, int bitmapWidth, int bitmapHeight) {
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xff3F51B5);
        canvas.drawCircle(bitmapWidth/2, bitmapHeight/2, bitmapWidth/2, paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(bitmapHeight/2);
        Rect rect = new Rect();
        paint.getTextBounds("" + c,0,1,rect);
        canvas.drawText("" + c,(bitmap.getWidth() - rect.width())/2, (bitmap.getHeight() + rect.height())/2, paint);
        return bitmap;
    }

    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Rect rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        RectF rectF = new RectF(rect);
        canvas.drawRoundRect(rectF, bitmap.getWidth()/2,bitmap.getHeight()/2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
