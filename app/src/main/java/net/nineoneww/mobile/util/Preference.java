package net.nineoneww.mobile.util;

import android.content.SharedPreferences;

import java.util.UUID;

/**
 * Created by lilian on 2017/9/19.
 */

public class Preference {
    private static final String PREFERENCE = "preference";
    private static final String LOGIN = "LOGIN";
    private static final String DEVICE_UUID = "DEVICE_UUID";

    protected static volatile UUID uuid;

    public static void setLogin(SharedPreferences sp) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(LOGIN, true);
        editor.apply();
    }

    public static boolean isLogin(SharedPreferences sp) {
        return sp.getBoolean(LOGIN, false);
    }

    public static void DeviceUuidFactory(SharedPreferences sp) {

    }

    public static String getUuid(SharedPreferences sp) {
        return sp.getString(DEVICE_UUID, "");
    }
}
