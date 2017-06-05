package com.leisurekr.leisuresportskorea.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.leisurekr.leisuresportskorea.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileEditActivity extends AppCompatActivity {

    ImageView backImage;
    CircleImageView profileImage;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit_activity);

        toolbar = (Toolbar) findViewById(R.id.profile_edit_toolbar);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        backImage = (ImageView)findViewById(R.id.profile_edit_backimage);
        profileImage = (CircleImageView) findViewById(R.id.profile_edit_circleimage);

        backImage.setImageResource(R.drawable.pic_bg_surfing);
        profileImage.setImageResource(R.drawable.ic_ne);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
