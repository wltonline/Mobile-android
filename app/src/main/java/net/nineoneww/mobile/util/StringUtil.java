package net.nineoneww.mobile.util;

import android.content.Context;
import android.content.pm.PackageManager;

import net.nineoneww.mobile.R;

import java.text.DecimalFormat;

/**
 * Created by lilian on 2017/8/8.
 */

public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    public static String convCommaNumber(String number) {
        if (StringUtil.isEmpty(number)) {
            return "0";
        }
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(Integer.parseInt(number));
    }

//    public static String getPanelsiteUrl(Context context, int pathId) {
//        return context.getResources().getString(R.string.domain_panelsite)
//                + context.getResources().getString(pathId);
//    }
//
//    public static String getAdTrackerUrl(Context context, String respondentId) {
//        return context.getResources().getString(R.string.domain_panelsite)
//                + context.getResources().getString(R.string.path_tracker_intage)
//                + "?respondent_id=" + respondentId;
//    }

    public static String getUserAgentName(Context context){
        String name = context.getString(R.string.app_name) + "/" ;
        try {
            name += context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            name += " (" + getDeviceInfo() + ")";
        } catch (PackageManager.NameNotFoundException e) {
            name += "unknown (" + getDeviceInfo() + ")";
        }
        return name;
    }

    public static String getDeviceInfo(){
        return  "Android: " +android.os.Build.VERSION.RELEASE
                +"; MODEL:"+android.os.Build.MODEL
                +"; PRODUCT:"+android.os.Build.PRODUCT
                +"; MANUFACTURER:"+android.os.Build.MANUFACTURER
                +";";
    }

    public static String format(String string, String... arguments){
        for (int i = 1; i <= arguments.length; i++) {
            string = string.replace("%" + i, arguments[i-1]);
        }
        return string;
    }
}
