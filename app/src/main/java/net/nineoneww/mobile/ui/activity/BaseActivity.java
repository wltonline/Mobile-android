package net.nineoneww.mobile.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import net.nineoneww.mobile.util.Constant;

/**
 * Created by lilian on 2017/8/4.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    public SharedPreferences getSharedPreferences() {
        return getSharedPreferences(Constant.PREFERENCE, MODE_PRIVATE);
    }

}
