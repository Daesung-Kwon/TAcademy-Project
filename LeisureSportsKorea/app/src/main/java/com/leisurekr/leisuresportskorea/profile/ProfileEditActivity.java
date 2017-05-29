package com.leisurekr.leisuresportskorea.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.leisurekr.leisuresportskorea.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileEditActivity extends AppCompatActivity {

    ImageView backImage;
    CircleImageView profileImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit_activity);

        backImage = (ImageView)findViewById(R.id.profile_edit_backimage);
        profileImage = (CircleImageView) findViewById(R.id.profile_edit_circleimage);

        backImage.setImageResource(R.drawable.background);
        profileImage.setImageResource(R.mipmap.ic_launcher);

    }
}
