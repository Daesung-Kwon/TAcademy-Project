package com.leisurekr.leisuresportskorea.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mobile on 2017. 5. 11..
 */

public class TabFragment3 extends android.support.v4.app.Fragment implements View.OnClickListener {

    View view;
    View myProfile;

    LinearLayout reservationlist;
    LinearLayout cart;
    LinearLayout favorites;
    LinearLayout settingAlarm;
    LinearLayout logout;
    LinearLayout aboutUs;
    LinearLayout terms;
    LinearLayout customerSupport;

    //ImageView backImage;
    LinearLayout backImage;
    CircleImageView profileImage;
    ImageView settingBtn;
    TextView nickname, email, tag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.profile_fragment,container,false);

        reservationlist = (LinearLayout) view.findViewById(R.id.profile_reservationlayout);
        cart = (LinearLayout) view.findViewById(R.id.profile_cartlayout);
        favorites = (LinearLayout) view.findViewById(R.id.profile_favoriteslayout);
        settingAlarm = (LinearLayout) view.findViewById(R.id.profile_alarmlayout);
        logout = (LinearLayout) view.findViewById(R.id.profile_logoutlayout);
        aboutUs = (LinearLayout) view.findViewById(R.id.profile_aboutuslayout);
        terms = (LinearLayout) view.findViewById(R.id.profile_termslayout);
        customerSupport = (LinearLayout) view.findViewById(R.id.profile_customerlayout);

        myProfile = view.findViewById(R.id.profile_myprofile);
        backImage = (LinearLayout)myProfile.findViewById(R.id.profile_backimage);
        profileImage = (CircleImageView) myProfile.findViewById(R.id.profile_circleimage);
        settingBtn = (ImageView)myProfile.findViewById(R.id.profile_editbtn_profile);
        nickname = (TextView)myProfile.findViewById(R.id.profile_nickname);
        email = (TextView)myProfile.findViewById(R.id.profile_email);
        tag = (TextView)myProfile.findViewById(R.id.profile_tag);

        reservationlist.setOnClickListener(this);
        favorites.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
        cart.setOnClickListener(this);

        backImage.setBackgroundResource(R.drawable.pic_bg_surfing);
        profileImage.setImageResource(R.drawable.ic_ma);
        settingBtn.setImageResource(R.drawable.ic_setting);
        nickname.setText("Kim Yong");
        email.setText("dlddldwkak@naver.com");
        tag.setText("#WaterSki #Ski #FunBoats");

        return view;
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.profile_editbtn_profile:
                intent = new Intent(getContext(),ProfileEditActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_reservationlayout:
                intent = new Intent(getContext(),ProfileReservationActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_cartlayout:
                intent = new Intent(getContext(),ProfileCartActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_favoriteslayout:
                intent = new Intent(getContext(),ProfileFavoritesActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
