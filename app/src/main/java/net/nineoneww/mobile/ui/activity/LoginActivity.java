package net.nineoneww.mobile.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import net.nineoneww.mobile.R;
import net.nineoneww.mobile.util.Preference;
import net.nineoneww.mobile.util.UuidTool;

/**
 * Created by lilian on 2017/8/7.
 */

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Preference.DeviceUuidFactory(getSharedPreferences());
        TextView uuidStr = (TextView)findViewById(R.id.uuid);
        TextView uuidVersion = (TextView)findViewById(R.id.version);
        uuidStr.setText(UuidTool.getUuidTool(this));
    }

}
