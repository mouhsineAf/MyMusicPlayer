package com.my.musicplayer.utils;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class Common extends MultiDexApplication{

    private static Common mContext;



    public static synchronized Common getInstance() {
        return mContext;
    }

}
