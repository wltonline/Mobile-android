package net.nineoneww.mobile.util;

import android.content.SharedPreferences;

/**
 * Created by lilian on 2017/9/19.
 */

public class Preference {
    private static final String PREFERENCE = "preference";
    private static final String LOGIN = "LOGIN";

    public static void setLogin(SharedPreferences sp) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(LOGIN, true);
        editor.apply();
    }

    public static boolean isLogin(SharedPreferences sp) {
        return sp.getBoolean(LOGIN, false);
    }
}
