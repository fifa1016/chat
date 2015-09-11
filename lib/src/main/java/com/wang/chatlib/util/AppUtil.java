package com.wang.chatlib.util;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by shawn on 9/9/15.
 * Get information about device, os, app
 */
public class AppUtil {
    public static String getVersionName(Activity activity){
        String versionName = "unknown";
        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
