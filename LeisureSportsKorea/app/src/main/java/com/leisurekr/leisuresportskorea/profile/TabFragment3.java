package com.leisurekr.leisuresportskorea.profile;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.facebook.login.LoginManager;
import com.leisurekr.leisuresportskorea.LoginActivity;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.sharedPreferences.LKSharedPreferencesManager;
import com.leisurekr.leisuresportskorea.shop.TabFragment2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mobile on 2017. 5. 11..
 */

public class TabFragment3 extends android.support.v4.app.Fragment implements View.OnClickListener {

    public static TabFragment3 tabFragment3;

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

    LinearLayout backImage;
    CircleImageView profileImage;
    ImageView settingBtn;
    TextView nickname, email, tag;

    ProfileObject profileObject;

    Switch push;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        tabFragment3 = this;

        view = inflater.inflate(R.layout.profile_fragment,container,false);

        reservationlist = (LinearLayout) view.findViewById(R.id.profile_reservationlayout);
        cart = (LinearLayout) view.findViewById(R.id.profile_cartlayout);
        favorites = (LinearLayout) view.findViewById(R.id.profile_favoriteslayout);
        settingAlarm = (LinearLayout) view.findViewById(R.id.profile_alarmlayout);
        logout = (LinearLayout) view.findViewById(R.id.profile_logoutlayout);
        aboutUs = (LinearLayout) view.findViewById(R.id.profile_aboutuslayout);
        terms = (LinearLayout) view.findViewById(R.id.profile_termslayout);
        customerSupport = (LinearLayout) view.findViewById(R.id.profile_customerlayout);
        push = (Switch)view.findViewById(R.id.profile_push);

        myProfile = view.findViewById(R.id.profile_myprofile);
        backImage = (LinearLayout)myProfile.findViewById(R.id.profile_backimage);
        profileImage = (CircleImageView) myProfile.findViewById(R.id.profile_circleimage);
        settingBtn = (ImageView)myProfile.findViewById(R.id.profile_editbtn_profile);
        nickname = (TextView)myProfile.findViewById(R.id.profile_nickname);
        email = (TextView)myProfile.findViewById(R.id.profile_email);
        tag = (TextView)myProfile.findViewById(R.id.profile_tag);


        push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    LKSharedPreferencesManager.getInstance().setIsPushed(true);
                }else{
                    LKSharedPreferencesManager.getInstance().setIsPushed(false);
                }
            }
        });

        reservationlist.setOnClickListener(this);
        favorites.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
        cart.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        terms.setOnClickListener(this);
        customerSupport.setOnClickListener(this);
        logout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        new AsyncProfileJSON().execute();
    }

    public class AsyncProfileJSON extends AsyncTask<String, Integer, ProfileObject>{
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dialog = ProgressDialog.show(getContext(), "", "Data Loading...", true);
        }

        @Override
        protected ProfileObject doInBackground(String... params) {
            return OkHttpAPIHelperHandler.profileJSONAllSelect();
        }

        @Override
        protected void onPostExecute(ProfileObject result) {
            //dialog.dismiss();
            if(result != null)
            refresh(result);

        }
    }

    public void refresh(ProfileObject profileObject){
        this.profileObject = profileObject;
        Glide.with(getContext()).load(profileObject.getBackImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new ViewTarget<LinearLayout, GlideDrawable>(backImage) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                backImage.setBackgroundDrawable(resource);
            }
        });
        switch (profileObject.getSex()){
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
        settingBtn.setImageResource(R.drawable.ic_setting);
        nickname.setText(profileObject.getName());
        email.setText(profileObject.getEmail());

        String interests=" ";
        HashMap<String, Boolean> tags = profileObject.getTags();
        Set<String> keys = tags.keySet();
        Iterator<String> iterator = keys.iterator();
        while(iterator.hasNext()){
            String s = iterator.next();
            if(tags.get(s)){
                    interests = interests +"# "+ s+" ";
            }
        }
        tag.setText(interests);

        if(LKSharedPreferencesManager.getInstance().getIsPushed()){
            push.setChecked(true);
        }else{
            push.setChecked(false);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                if(requestCode == 0) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(TabFragment2.tabFragment2)
                            .attach(TabFragment2.tabFragment2).commit();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.profile_editbtn_profile:
                intent = new Intent(getContext(),ProfileEditActivity.class);
                intent.putExtra("profileObject",profileObject);
                startActivityForResult(intent,0);
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
            case R.id.profile_aboutuslayout:
                intent = new Intent(getContext(),AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_termslayout:
                intent = new Intent(getContext(),TermsActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_customerlayout:
                intent = new Intent(getContext(),CustomerActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_logoutlayout:
                new AlertDialog.Builder(getContext()).setTitle("Log Out?").setMessage("Do you want to log out?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LKSharedPreferencesManager.getInstance().removeAll();
                        LoginManager.getInstance().logOut();
                        Intent intent1 = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent1);
                    }
                }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

                break;
            default:
                break;
        }
    }
}
