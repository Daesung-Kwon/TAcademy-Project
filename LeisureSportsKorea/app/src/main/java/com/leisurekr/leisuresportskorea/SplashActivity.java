package com.leisurekr.leisuresportskorea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.leisurekr.leisuresportskorea.sharedPreferences.LKSharedPreferencesManager;

import static com.leisurekr.leisuresportskorea.common.SharedPreferencesDefineConstant.PREF_NAME;
import static com.leisurekr.leisuresportskorea.sharedPreferences.LKSharedPreferencesManager.KEY_TOKEN;

/**
 * Created by mobile on 2017. 6. 7..
 */

public class SplashActivity extends AppCompatActivity {
    SharedPreferences mPrefs;

    public static final String TOKEN_IS_EMPTY = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        mPrefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String keyToken = LKSharedPreferencesManager.getInstance().getKeyToken();
                Log.i("get token", keyToken);
                if (TOKEN_IS_EMPTY.equals(keyToken)) {

                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Log.i("token", mPrefs.getString(KEY_TOKEN, "EMPTY"));
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 3000);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
