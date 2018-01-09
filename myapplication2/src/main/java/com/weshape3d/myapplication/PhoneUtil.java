package com.weshape3d.myapplication;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

/**
 * Created by WESHAPE-DEV02 on 2017/10/27.
 */

public class PhoneUtil {
    public static  String  getPhoneMessage(Context context) {
        StringBuilder builder = new StringBuilder();
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("drummor","获取GET失败");
            e.printStackTrace();
        }
        builder.append("APP版本号:").append(packageInfo.versionName).append("\n") ;
       builder.append("Android 版本号:").append(Build.VERSION.RELEASE).append("_").append(Build.VERSION.SDK_INT).append("\n") ;
       builder.append("手机制造商:").append(Build.MANUFACTURER).append("\n") ;
       builder.append("手机型号:").append(Build.MODEL).append("\n") ;
       builder.append("CPU架构:").append(Build.CPU_ABI).append("\n") ;
        return  builder.toString();
    }
}
