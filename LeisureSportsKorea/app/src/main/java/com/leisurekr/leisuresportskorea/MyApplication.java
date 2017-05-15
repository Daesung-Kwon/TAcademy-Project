package com.leisurekr.leisuresportskorea;

import android.app.Application;
import android.content.Context;

/**
 * Created by user on 2017-05-15.
 */

public class MyApplication extends Application {
    private static Context myContext;

    @Override
    public void onCreate() {
        super.onCreate();
        myContext = this;
    }

    public static Context getMyContext() {
        return myContext;
    }
}
