package com.leisurekr.leisuresportskorea;

import android.app.Application;
import android.content.Context;

/**
 * Created by mobile on 2017. 5. 23..
 */

public class LKApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
    public static Context getLKApplication() { return mContext; }
}
