package com.codestub.tsms.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Utils class for permissions
 * Created by ganesh on 23/4/16.
 */
public class PermissionUtils {

    public static boolean isGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context.getApplicationContext(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void require(Activity activity, String permission, int requestCode) {
        if(!isGranted(activity.getApplicationContext(),permission)) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)){
                //TODO : add request explanation
                ActivityCompat.requestPermissions(activity,new String[]{permission},requestCode);
            } else {
                ActivityCompat.requestPermissions(activity,new String[]{permission},requestCode);
            }
        }
    }
}
