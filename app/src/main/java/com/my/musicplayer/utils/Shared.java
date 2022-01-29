package com.my.musicplayer.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared {
    private static Shared shared;
    private SharedPreferences SP;
    private static final String filename="shared";

    public Shared(Context context) {
        SP = context.getApplicationContext().getSharedPreferences(filename, Context.MODE_PRIVATE);
    }

    public static Shared getInstance(Context context) {
        if (shared == null) {
            shared = new Shared(context);
        }
        return shared;
    }

    public void putSplashData(boolean modeAdsBanner, boolean modeAdsInterstitial, boolean modeAdsNative, boolean modeAdsRewardedVideo, String maxBannerUnitId, String maxNativeUnitId, String maxInterUnitId, String maxRewardedVideoUnitId, Boolean updateState, String updateTitle, String updateMsg, String updateAction, Boolean updateClosed, String webSiteGames) {
    //   SharedPreferences.Editor editor;
    //   editor = SP.edit();
    //   editor.putBoolean(Config.ModeAdsBanner, modeAdsBanner);
    //   editor.putBoolean(Config.ModeAdsInterstitial, modeAdsInterstitial);
    //   editor.putBoolean(Config.ModeAdsNative, modeAdsNative);
    //   editor.putBoolean(Config.ModeAdsRewardedVideo, modeAdsRewardedVideo);

    //   editor.putString(Config.MaxBannerUnitId, maxBannerUnitId);
    //   editor.putString(Config.MaxNativeUnitId, maxNativeUnitId);
    //   editor.putString(Config.MaxInterUnitId, maxInterUnitId);
    //   editor.putString(Config.MaxRewardedVideoUnitId, maxRewardedVideoUnitId);

    //   editor.putBoolean(Config.UpdateState, updateState);
    //   editor.putString(Config.UpdateTitle, updateTitle);
    //   editor.putString(Config.UpdateMsg, updateMsg);
    //   editor.putString(Config.UpdateAction, updateAction);
    //   editor.putBoolean(Config.UpdateClosed, updateClosed);

    //   editor.putString(Config.WebSiteGames, webSiteGames);


    //   editor.apply();
    }


    public void putString(String key, String value) {
        SharedPreferences.Editor editor;
        editor = SP.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String mDefault) {
        return SP.getString(key, mDefault);
    }

    public int getInt(String key, int mDefault) {
        return SP.getInt(key, mDefault);
    }

    public void putInt(String key, int num) {
        SharedPreferences.Editor editor;
        editor = SP.edit();

        editor.putInt(key, num);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean mDefault) {
        return SP.getBoolean(key, mDefault);
    }

    public void putBoolean(String key, boolean b) {
        SharedPreferences.Editor editor;
        editor = SP.edit();
        editor.putBoolean(key, b);
        editor.apply();
    }


    public void clear(){
        SharedPreferences.Editor editor;
        editor = SP.edit();

        editor.clear();
        editor.apply();
    }

    public void remove(){
        SharedPreferences.Editor editor;
        editor = SP.edit();

        editor.remove(filename);
        editor.apply();
    }
}
