package com.lxd.mydevise.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.collection.SimpleArrayMap;

import com.lxd.mydevise.EnApplication;

/**
 * @Author lixd
 */
public final class SharedPreferenceUtils {

    private static final SimpleArrayMap<String,SharedPreferenceUtils> mCache = new SimpleArrayMap<>();
    private SharedPreferences mSharedPreferences;

    private SharedPreferenceUtils(final String spName,final int mode){
       mSharedPreferences = EnApplication.get().getSharedPreferences(spName,mode);

    }

    public static SharedPreferenceUtils getInstance(String spName){
        SharedPreferenceUtils sharedPreferenceUtils = mCache.get(spName);
        if(sharedPreferenceUtils == null){
            sharedPreferenceUtils = new SharedPreferenceUtils(spName, Context.MODE_PRIVATE);
            mCache.put(spName,sharedPreferenceUtils);
        }
        return sharedPreferenceUtils;
    }

    public void put(final String key,final String value){
        mSharedPreferences.edit().putString(key,value).apply();
    }

    public String get(final String key){
       return mSharedPreferences.getString(key,"");
    }
}
