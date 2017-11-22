package com.erickogi14gmail.ishanu.Views.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.erickogi14gmail.ishanu.Data.Db.PrefrenceManager;
import com.erickogi14gmail.ishanu.R;
import com.erickogi14gmail.ishanu.Views.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static int spalsh_time_out = 1000;
    private boolean debug = true;
    private PrefrenceManager prefrenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        prefrenceManager = new PrefrenceManager(SplashScreenActivity.this);
        handler();
    }

    private void handler() {
        new Handler().postDelayed(() -> {
                    if (prefrenceManager.isLoggedIn()) {
                        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);


                        finish();
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);


                        finish();

                        startActivity(intent);

                    }
                }


                , spalsh_time_out);

    }
}
