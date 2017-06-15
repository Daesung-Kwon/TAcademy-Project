package com.leisurekr.leisuresportskorea;

import android.content.Intent;
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

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by mobile on 2017. 5. 10..
 */

public class LoginActivity extends AppCompatActivity{

    private CallbackManager callbackManager;

    LoginButton facebookLoginBtn;
    Button lineLoginBtn, signInBtn, regBtn;
    TextView skipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
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

        lineLoginBtn = (Button) findViewById(R.id.lineLoginButton);
        lineLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : Line API
            }
        });

        skipBtn = (TextView) findViewById(R.id.loginSkipButton);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
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
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
