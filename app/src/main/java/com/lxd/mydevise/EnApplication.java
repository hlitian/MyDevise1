package com.lxd.mydevise;

import android.app.Application;

/**
 * @Author lixd
 */
public class EnApplication extends Application {

    private static EnApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static EnApplication get(){
        return INSTANCE;
    }
}
