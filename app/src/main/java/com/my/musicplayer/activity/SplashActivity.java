package com.my.musicplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.my.musicplayer.R;

import java.security.Permissions;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                open();
            }
        },3000);

        check();

    }


    private void check(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          //  Permissions.check(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
          //                  Manifest.permission.WRITE_EXTERNAL_STORAGE},
          //          "", new Permissions.Options()
          //                  .setSettingsDialogTitle("Warning!").setRationaleDialogTitle("Info"),
          //          new PermissionHandler() {
          //              @Override
          //              public void onGranted() {
          //                  //do your task
          //                  new Handler().postDelayed(new Runnable() {
          //                      @Override
          //                      public void run() {
          //                          open();
          //                      }
          //                  },1000);
          //              }
//
          //              @Override
          //              public void onDenied(Context context, ArrayList<String> deniedPermissions) {
          //                  super.onDenied(context, deniedPermissions);
          //                  finish();
          //              }
          //          });
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    open();
                }
            },1000);
        }
    }

    private void open(){
        Intent startActivityIntent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(startActivityIntent);
        finish();
    }
}