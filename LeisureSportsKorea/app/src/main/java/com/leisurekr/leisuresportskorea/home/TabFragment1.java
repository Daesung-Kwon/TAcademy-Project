package com.leisurekr.leisuresportskorea.home;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 5. 11..
 */

public class TabFragment1 extends android.support.v4.app.Fragment {


    CircleAnimIndicator circleAnimIndicator;

    RecyclerView recyclerView;
    ViewPager viewPager;

    ImageView adverticeImage1;
    ImageView adverticeImage2;
    ImageView adverticeImage3;
    ImageView adverticeImage4;
    ViewFlipper viewFlipper;
    Button previous;
    LinearLayout predim;
    Button next;
    LinearLayout nextdim;

    TextView groupActivityName;
    ActivityImageObject activityImage1;
    ActivityImageObject activityImage2;
    ActivityImageObject activityImage3;
    ActivityImageObject activityImage4;
    ActivityImageObject activityImage5;


    TextView groupShopName;
    ShopImageObject shopImage1;
    ShopImageObject shopImage2;
    ShopImageObject shopImage3;
    ShopImageObject shopImage4;
    ShopImageObject shopImage5;

    int count = -1;//뷰 플리퍼 레이아웃 체인지 리스너가 기본 2번 실행되므로 2를 더한수가 1이 되야함

    HomeObject homeObject;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.home_fragment, container, false);


        //Home 화면 advertice 부분
        View advertice = view.findViewById(R.id.advertice);

        adverticeImage1 = (ImageView) advertice.findViewById(R.id.home_image1_ad);
        adverticeImage2 = (ImageView) advertice.findViewById(R.id.home_image2_ad);
        adverticeImage3 = (ImageView) advertice.findViewById(R.id.home_image3_ad);
        adverticeImage4 = (ImageView) advertice.findViewById(R.id.home_image4_ad);

        /*adverticeImage1.setImageResource(R.drawable.girls_generation_all);
        adverticeImage2.setImageResource(R.drawable.exo_all);
        adverticeImage3.setImageResource(R.drawable.girls_generation_all);
        adverticeImage4.setImageResource(R.drawable.exo_all);*/

        viewFlipper = (ViewFlipper) advertice.findViewById(R.id.home_viewflipper_ad);
        viewFlipper.setFlipInterval(3000);//자동 넘김 시간 3초로 설정
        viewFlipper.setAutoStart(true);//자동으로 실행되며 마지막에 도달하면 처음부터 다시시작
                                        //.startFlipping()의 경우 마지막에 도달하면 멈춘다.

        //화면이 변할때마다 카운트 변수를 관리하여 인디케이터를 알맞게 조정한다.
        viewFlipper.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                count++; //레이아웃이 변하면 카운트를 증가시키고
                //Log.e("viewFliper","change");
                if(count>4)  //4보다 커지면 다시 첫번째를 의미하므로 1로 변경
                    count=1;
                else if(count < 1) //1보다 작아지면 마지막번째인 4번째를 의미하므로 4로 변경
                    count=4;
                circleAnimIndicator.selectDot(count-1); //인디케이터는 배열 인덱스 방식이므로 -1을 한다.
            }
        });


        circleAnimIndicator = (CircleAnimIndicator) advertice.findViewById(R.id.home_advertice_indicator);
        previous = (Button) advertice.findViewById(R.id.home_prevbtn_ad);
        predim = (LinearLayout) advertice.findViewById(R.id.home_prevbtn_dim);
        next = (Button) advertice.findViewById(R.id.home_nextbtn_ad);
        nextdim = (LinearLayout) advertice.findViewById(R.id.home_nextbtn_dim);

        predim.setAlpha(0f);
        nextdim.setAlpha(0f);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count-=2;
                viewFlipper.showPrevious();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    viewFlipper.showNext();
            }
        });

        //Home 화면 recommendActivity 부분
        View recommendActivity = view.findViewById(R.id.recommendactivity);

        groupActivityName = (TextView) recommendActivity.findViewById(R.id.home_activity_groupname);
        //ImageView image = (ImageView) v.findViewById(R.id.activity_icon);
        //image.setImageResource(R.drawable.f);
        activityImage1 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image1));
        activityImage2 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image2));
        activityImage3 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image3));
        activityImage4 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image4));
        activityImage5 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image5));

        groupActivityName.setText("Recommended Activities");
        /*activityImage1.setData(R.drawable.pic_waterski1,
                "Water Ski", "River city's water ski", "Beginer Lesson Package", 300);
        activityImage2.setData(R.drawable.pic_funboat1,
                "Fun Boat", "River city's fun boat", "Beginer Lesson Package", 300);
        activityImage3.setData(R.drawable.pic_waterski1,
                "Water Ski", "River city's water ski", "Beginer Lesson Package", 300);
        activityImage4.setData(R.drawable.pic_funboat1,
                "Fun Boat", "River city's fun boat", "Beginer Lesson Package", 300);
        activityImage5.setData(R.drawable.pic_waterski1,
                "Water Ski", "River city's water ski", "Beginer Lesson Package", 300);*/


        //Home 화면 Best Shop 부분
        View bestshop = view.findViewById(R.id.bestshop);
        groupShopName = (TextView) bestshop.findViewById(R.id.home_groupname_shop);

        shopImage1 = new ShopImageObject(bestshop.findViewById(R.id.home_image1_shop));
        shopImage2 = new ShopImageObject(bestshop.findViewById(R.id.home_image2_shop));
        shopImage3 = new ShopImageObject(bestshop.findViewById(R.id.home_image3_shop));
        shopImage4 = new ShopImageObject(bestshop.findViewById(R.id.home_image4_shop));
        shopImage5 = new ShopImageObject(bestshop.findViewById(R.id.home_image5_shop));

        groupShopName.setText("Best Shop");
        /*shopImage1.setData(R.drawable.pic_shop, "Costa Lesuire Sport", "Han River", "4.8");
        shopImage2.setData(R.drawable.pic_shop, "Costa Lesuire Sport", "Han River", "4.8");
        shopImage3.setData(R.drawable.pic_shop, "Costa Lesuire Sport", "Han River", "4.8");
        shopImage4.setData(R.drawable.pic_shop, "Costa Lesuire Sport", "Han River", "4.8");
        shopImage5.setData(R.drawable.pic_shop, "Costa Lesuire Sport", "Han River", "4.8");*/

        initIndicaotor();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new AsyncShopInfoJSONList().execute();

    }

    public void setData() {

        ArrayList<BannerObject> banners = homeObject.banners;
        ArrayList<ActivityObject> activities = homeObject.activities;
        ArrayList<ShopObject> shops = homeObject.shops;

        //Log.e("banner image",bannerTest.banners.get(0).image);
        Glide.with(this).load(banners.get(0).image).into(adverticeImage1);
        Glide.with(this).load(banners.get(1).image).into(adverticeImage2);
        Glide.with(this).load(banners.get(2).image).into(adverticeImage3);
        Glide.with(this).load(banners.get(3).image).into(adverticeImage4);

        activityImage1.setData(activities.get(0));
        activityImage2.setData(activities.get(1));
        activityImage3.setData(activities.get(2));
        activityImage4.setData(activities.get(3));
        activityImage5.setData(activities.get(4));

        shopImage1.setData(shops.get(0));
        shopImage2.setData(shops.get(0));
        shopImage3.setData(shops.get(0));
        shopImage4.setData(shops.get(0));
        shopImage5.setData(shops.get(0));

        viewFlipper.setAutoStart(true);

    }

    public class AsyncShopInfoJSONList
            extends AsyncTask<String, Integer, HomeObject> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(getContext(), "", "Data Loading...", true);
        }

        @Override
        protected HomeObject doInBackground(String... params) {
            return OkHttpAPIHelperHandler.homeJSONAllSelect();
        }

        @Override
        protected void onPostExecute(HomeObject result) {
            dialog.dismiss();
            homeObject = result;
            setData();
        }
    }

    private void initIndicaotor() {

        //원사이의 간격
        circleAnimIndicator.setItemMargin(15);
        //애니메이션 속도
        circleAnimIndicator.setAnimDuration(300);
        //indecator 생성
        circleAnimIndicator.setSavePosition(count - 1);
        circleAnimIndicator.createDotPanel(4
                , R.drawable.icon_navi_unpress, R.drawable.icon_navi_press);
    }
}
