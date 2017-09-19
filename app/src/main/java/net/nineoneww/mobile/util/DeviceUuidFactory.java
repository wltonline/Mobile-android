package net.nineoneww.mobile.util;

/**
 * Created by lilian on 2017/9/19.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.UUID;

public class DeviceUuidFactory {

    protected static final String PREFS_FILE = "device_id.xml";
    protected static final String PREFS_DEVICE_ID = "device_id";
    protected static volatile UUID uuid;

    public DeviceUuidFactory(Context context) {

    }

    public static String getDeviceId(Context context) {
        String deviceId = "";
		if (deviceId != null && !"".equals(deviceId)) {
            return deviceId;
        }
        if (deviceId == null || "".equals(deviceId)) {
            try {
                deviceId = getLocalMac(context).replace(":", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (deviceId == null || "".equals(deviceId)) {
            try {
                deviceId = getAndroidId(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (deviceId == null || "".equals(deviceId)) {
            if (deviceId == null || "".equals(deviceId)) {
                UUID uuid = UUID.randomUUID();
                deviceId = uuid.toString().replace("-", "");
                writeDeviceID(deviceId);
            }
        }
        return deviceId;
    }

    public static void writeDeviceID(String str) {

    }


    // IMEI码
    private static String getIMIEStatus(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        return deviceId;
    }

    // Mac地址
    private static String getLocalMac(Context context) {
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    // Android Id
    private static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }

    public static void saveDeviceID(String str) {
        try {
            FileOutputStream fos = new FileOutputStream(PREFS_FILE);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(str);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readDeviceID() {
        StringBuffer buffer = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream(PREFS_FILE);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            Reader in = new BufferedReader(isr);
            int i;
            while ((i = in.read()) > -1) {
                buffer.append((char) i);
            }
            in.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
