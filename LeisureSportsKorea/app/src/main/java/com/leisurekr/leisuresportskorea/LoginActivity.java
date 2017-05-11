package com.leisurekr.leisuresportskorea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by mobile on 2017. 5. 10..
 */

public class LoginActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button facebookLoginBtn = (Button) findViewById(R.id.facebookLoginButton);
        Button lineLoginBtn = (Button) findViewById(R.id.lineLoginButton);
        Button signInBtn = (Button) findViewById(R.id.signInButton);
        Button regBtn = (Button) findViewById(R.id.registerButton);
        TextView skipBtn = (TextView) findViewById(R.id.loginSkipButton);

        facebookLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : facebook API
            }
        });

        lineLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : Line API
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
                Intent loginMemberIntent = new Intent(LoginActivity.this, LoginMemberActivity.class);
                LoginActivity.this.startActivity(loginMemberIntent);
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
                Intent regIntent = new Intent(LoginActivity.this, MemberRegisterActivity.class);
                LoginActivity.this.startActivity(regIntent);

            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });
    }
}
