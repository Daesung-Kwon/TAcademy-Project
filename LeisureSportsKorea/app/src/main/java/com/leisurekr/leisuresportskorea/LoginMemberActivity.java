package com.leisurekr.leisuresportskorea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by mobile on 2017. 5. 11..
 */

public class LoginMemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_member);

        Button loginBtn = (Button) findViewById(R.id.loginButton);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
                Intent loginIntent = new Intent(LoginMemberActivity.this, MainActivity.class);
                LoginMemberActivity.this.startActivity(loginIntent);
            }
        });
    }
}
