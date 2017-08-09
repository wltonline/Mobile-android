package net.nineoneww.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by lilian on 2017/8/4.
 */

public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startApplication();
    }

    private void startApplication() {
        boolean islogin = false;
        if (islogin) {
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
