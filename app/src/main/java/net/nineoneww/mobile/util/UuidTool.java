package net.nineoneww.mobile.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.provider.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by lilian on 2017/9/22.
 */

public class UuidTool {
    protected static final String PREFS_FILE = "device_id.xml";
    protected static final String DEVICE_UUID = "device_id";
    protected static volatile UUID uuid;

    public static String getUuidTool(Context context) {
        if (uuid == null) {
            synchronized (UuidTool.class) {
                if (uuid == null) {
//                    uuid = UUID.fromString(readDeviceID());
                    String deviceIdFromSD =readDeviceID();
                    if(deviceIdFromSD == null) {
                        final SharedPreferences prefs = context.getSharedPreferences(Constant.PREFERENCE, 0);
                        final String id = prefs.getString(DEVICE_UUID, null);
                        if (id != null) {
                            // Use the ids previously computed and stored in the
                            // prefs file
                            uuid = UUID.fromString(id);
                        } else {
                            final String androidId = Settings.Secure.getString(
                                    context.getContentResolver(), Settings.Secure.ANDROID_ID);
                            // Use the Android ID unless it's broken, in which case
                            // fallback on deviceId,
                            // unless it's not available, then fallback on a random
                            // number which we store to a prefs file
                            try {
                                if (!"9774d56d682e549c".equals(androidId)) {
                                    uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                                } else {
                                    uuid = UUID.randomUUID();
                                }
                            } catch (UnsupportedEncodingException e) {
                                throw new RuntimeException(e);
                            }
                            // Write the value out to the prefs file
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString(DEVICE_UUID, uuid.toString());
                            editor.apply();

                            saveDeviceID(uuid.toString());
                        }
                    }else{
                        uuid = UUID.fromString(deviceIdFromSD);
                    }
                }
            }
        }
        return uuid.toString();
    }


    /**
     * 判断SDCard是否存在 [当没有外挂SD卡时，内置ROM也被识别为存在sd卡]
     *
     * @return
     */
    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }


    /**
     * 获取SD卡根目录路径
     *
     * @return
     */
    public static String getSdCardPath() {
        boolean exist = isSdCardExist();
        String sdpath = "";
        if (exist) {
            sdpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {
            sdpath = "不适用";
        }
        return sdpath;

    }

    /**
     * 获取默认的文件路径
     *
     * @return
     */
    public static String getDefaultFilePath() {
        String filepath = "";
        File file = new File(Environment.getExternalStorageDirectory(),
                PREFS_FILE);
        if (file.exists()) {
            filepath = file.getAbsolutePath();
        } else {
            filepath = "不适用";
        }
        return filepath;
    }

    public static void saveDeviceID(String str) {
        if (!"不适用".equals(isSdCardExist())) {
            try {
                File file = new File(getSdCardPath(),
                        PREFS_FILE);
                FileOutputStream out = new FileOutputStream(file);
                String info = "I am a chinanese!";
                out.write(str.getBytes());
                out.close();
                System.out.println("写入成功：");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String readDeviceID() {
        String filePath = getDefaultFilePath();
        if (!"不适用".equals(filePath)) {
            try {
                File file = new File(filePath);
                BufferedReader br = new BufferedReader(new FileReader(file));
                String readline = "";
                StringBuffer buffer = new StringBuffer();
                while ((readline = br.readLine()) != null) {
                    System.out.println("readline:" + readline);
                    buffer.append(readline);
                }
                br.close();
                System.out.println("读取成功：" + buffer.toString());
                return buffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
