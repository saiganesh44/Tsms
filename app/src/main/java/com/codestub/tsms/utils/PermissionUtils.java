package com.codestub.tsms.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Utils class for permissions
 * Created by ganesh on 23/4/16.
 */
public class PermissionUtils {

    public static boolean isGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context.getApplicationContext(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void require(Activity activity, String[] permissions, int requestCode) {
        Boolean[] isGranted = new Boolean[permissions.length];
        Context context = activity.getApplicationContext();

        for(int i = 0; i < permissions.length; i++ ) {
            isGranted[i] = isGranted(context, permissions[i]);
        }

        List<String> permissionsToAsk = new ArrayList<>();
        for(int i = 0,j=0; i < permissions.length; i++) {
            if(!isGranted[i]) {
                permissionsToAsk.add(permissions[i]);
                j++;
            }
        }

        if(permissionsToAsk.size() > 0) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[0])){
                //TODO : revisit and modify the above code to handle multiple request explanations
                ActivityCompat.requestPermissions(activity,permissionsToAsk.toArray(new String[0]),requestCode);
            } else {
                ActivityCompat.requestPermissions(activity,permissionsToAsk.toArray(new String[0]),requestCode);
            }
        }
    }
}
