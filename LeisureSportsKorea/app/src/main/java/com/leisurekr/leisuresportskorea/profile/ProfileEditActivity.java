package com.leisurekr.leisuresportskorea.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.leisurekr.leisuresportskorea.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileEditActivity extends AppCompatActivity {

    ImageView backImage;
    CircleImageView profileImage;
    Toolbar toolbar;
    String s="Male";
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
        switch (s){
            case "Male":
                profileImage.setImageResource(R.drawable.ic_ma);
                break;
            case "Female":
                profileImage.setImageResource(R.drawable.ic_fe);
                break;
            case "Gender":
                profileImage.setImageResource(R.drawable.ic_ne);
                break;
            default:
                profileImage.setImageResource(R.drawable.ic_ne);
                break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.save_action:
                Toast.makeText(ProfileEditActivity.this,"It is saved",Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
