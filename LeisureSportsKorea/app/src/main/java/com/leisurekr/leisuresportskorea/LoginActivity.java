package com.leisurekr.leisuresportskorea;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpJSONDataParseHandler;
import com.leisurekr.leisuresportskorea.sharedPreferences.LKSharedPreferencesManager;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopListObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static com.leisurekr.leisuresportskorea.common.SharedPreferencesDefineConstant.PREF_NAME;
import static com.leisurekr.leisuresportskorea.sharedPreferences.LKSharedPreferencesManager.KEY_INIT_INTERESTS;
import static com.leisurekr.leisuresportskorea.sharedPreferences.LKSharedPreferencesManager.KEY_TOKEN;

/**
 * Created by mobile on 2017. 5. 10..
 */

public class LoginActivity extends AppCompatActivity{

    static SharedPreferences mPrefs;
    private CallbackManager callbackManager;

    LoginButton facebookLoginBtn;
    Button googleLoginBtn;
    TextView skipBtn;

    final String PREFS_FOR_INTERESTS = "interests";

    static ArrayList<String> userInfoFromFB = new ArrayList<>();
    JSONObject jsonObject = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPrefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        callbackManager = CallbackManager.Factory.create();
        facebookLoginBtn = (LoginButton) findViewById(R.id.facebookLoginButton);
        facebookLoginBtn.setReadPermissions(Arrays.asList("public_profile", "email"));
        facebookLoginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("[result] : ", object.toString());
                        jsonObject = object;
                        userInfoFromFB = OkHttpJSONDataParseHandler.getJSONFacebookLogin(jsonObject);
                        new AsyncCreateUserToken().execute();
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,email,gender,age_range,locale");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("LoginErr",error.toString());
            }
        });

        googleLoginBtn = (Button) findViewById(R.id.lineLoginButton);
        googleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : Google API
            }
        });

        skipBtn = (TextView) findViewById(R.id.loginSkipButton);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent skipIntent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(skipIntent);
                    finish();

            }
        });
    }

    int FB_LOGIN_REQUEST_CODE = 64206;
    int FB_LOGIN_RESULT_OK_CODE = -1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        Log.i("[FB request code] : ", String.valueOf(requestCode));
        Log.i("[FB result code] : ", String.valueOf(resultCode));
        Log.i("[FB result data] : ", data.toString());

        if (requestCode == FB_LOGIN_REQUEST_CODE &&
                resultCode == FB_LOGIN_RESULT_OK_CODE) {

            //userInfoFromFB = OkHttpJSONDataParseHandler.getJSONFacebookLogin(jsonObject);
            //new AsyncCreateUserToken().execute();
            /*Intent intent = new Intent(this, PreInterestsActivity.class);
            startActivity(intent);
            finish();*/
        }
    }

    public class AsyncCreateUserToken
            extends AsyncTask<String, Integer, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dialog = ProgressDialog.show(LKApplication.getLKApplication(), "", "Loading...", true);
        }

        @Override
        protected String doInBackground(String... params) {
            return OkHttpAPIHelperHandler.facebookUserInfoJSONInsert(userInfoFromFB);
        }
        @Override
        protected void onPostExecute(String result) {
            //dialog.dismiss();
            if (result != null && result != "") {
                Log.i("Get Token Success", result);
                storeTokenPref(result);
                if (!PREFS_FOR_INTERESTS.equals(mPrefs.getString(KEY_INIT_INTERESTS, ""))) {
                    Intent intent = new Intent(getApplicationContext(), PreInterestsActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent skipIntent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(skipIntent);
                    finish();
                }
                Log.i("PREFS", mPrefs.getString(KEY_INIT_INTERESTS, ""));
            }else {
                Log.i("Get Token Failed", "Result is NULL");
            }
        }
    }

    public static void storeTokenPref(String token) {
        LKSharedPreferencesManager.getInstance().setKeyToken(token);
        Log.i("Store Token in Prefs", mPrefs.getString(KEY_TOKEN, ""));
    }
}
