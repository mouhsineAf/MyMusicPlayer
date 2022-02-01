package com.my.musicplayer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import com.my.musicplayer.R;
import com.my.musicplayer.utils.Constants;
import com.my.musicplayer.utils.DataHelper;

import java.security.Permissions;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";


    private CompositeDisposable mCompositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mCompositeDisposable = new CompositeDisposable();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // open();
            }
        },3000);

        check();

    }


    private void check(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkRequestPermissions()){
                buildLibrary();
            }
        }else{
          //  buildLibrary();
        }
    }

    private boolean checkRequestPermissions() {
        int modifyAudioPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (modifyAudioPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), Constants.REQUEST_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Constants.REQUEST_PERMISSIONS == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                buildLibrary();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder((Context) this);
                builder.setTitle(R.string.grant_permission);
                builder.setMessage(R.string.grant_permission_message);
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SplashActivity.this.finish();
                    }
                });


                builder.setPositiveButton(R.string.open_settings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", SplashActivity.this.getPackageName(), null);
                        intent.setData(uri);
                        SplashActivity.this.startActivity(intent);

                    }
                });
                builder.create().show();
            }
        }
    }


    private void buildLibrary() {
      // mCompositeDisposable.add(Observable.fromCallable(() -> DataHelper.buildMusicLibrary())
      //         .subscribeOn(Schedulers.io())
      //         .observeOn(AndroidSchedulers.mainThread())
      //         .subscribeWith(new DisposableObserver<Boolean>() {
      //             @Override
      //             public void onNext(Boolean aBoolean) {
      //                 Intent intent = new Intent((Context) SplashActivity.this, MainActivity.class);
      //                 startActivity(intent);
      //                 SplashActivity.this.finish();
      //             }

      //             @Override
      //             public void onError(Throwable e) {
      //                 Log.e(TAG, "" + e.getMessage());
      //             }

      //             @Override
      //             public void onComplete() {

      //             }
      //         })
      // );
        open();
    }

    private void open(){
        Intent startActivityIntent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(startActivityIntent);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }

}