package com.example.mochico.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.mochico.R;

public class SplashActivity extends AppCompatActivity {
    //private static final int REQUEST_PERMISSIONS = 20;
   // ImageView icon;
    //Animation zoomout, rotate;
   // String type;
    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSession();
    }
    private void getSession() {
        try {
            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), Setting.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    SplashActivity.this.finish();
                    handler.removeCallbacks(this);
                }
            }, SPLASH_TIME_OUT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // session = new AppSharedPreference(getApplicationContext()));
        // HashMap<String, String> user = session.getUserDetails();
        // type = user.get(SessionManager.KEY_USER_TYPE);

        // icon = (ImageView) findViewById(R.id.ivImage);
        // zoomout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        // icon.setAnimation(zoomout);

     /*   Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finishAffinity();

                   *//*Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("station_code", "S10");
                    intent.putExtra("operator_id", "01");
                    intent.putExtra("operator_id2", "01");
                    intent.putExtra("product_code", "10058589");
                    intent.putExtra("gauge_code", "2");
                    intent.putExtra("po_number", "W34425");
                    intent.putExtra("heat_code", "6");
                    intent.putExtra("total_grass",  "51.00");
                    intent.putExtra("tolarance_valuep",  "0.51");
                    intent.putExtra("box_qty", String.valueOf(10));
                    intent.putExtra("empty_weight", String.valueOf(0.25));
                    intent.putExtra("net_weight", String.valueOf(0.27));
                    intent.putExtra("max_weight", String.valueOf(14.00));
                    intent.putExtra("min_weight", String.valueOf(13.00));
                    startActivity(intent);
                    finish();*//*
                }
            }
        };
        timer.start();*/
    }

  /*  @Override
    protected void onRestart() {
        super.onRestart();
        if (isDeviceBuildVersionMarshmallow()) {
            getPermisson();
        } else {
            getSession();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        getSession();
    }

    private void initialize() {
        if (isDeviceBuildVersionMarshmallow()) {
            getPermisson();
        } else {
            getSession();
        }
    }


    private void getPermisson() {
        SplashActivity.super.requestAppPermissions(new
                        String[]{

                        android.Manifest.permission.INTERNET,
                        android.Manifest.permission.READ_PHONE_STATE,


                }, R.string.runtime_permissions_txt
                , REQUEST_PERMISSIONS);
    }

    private boolean isDeviceBuildVersionMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }*/

}