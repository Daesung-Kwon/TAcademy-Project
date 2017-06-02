package com.leisurekr.leisuresportskorea.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.leisurekr.leisuresportskorea.R;

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
    Button next;

    TextView groupActivityName;
    ActivityImageObject activityImage1;
    ActivityImageObject activityImage2;
    ActivityImageObject activityImage3;
    ActivityImageObject activityImage4;
    ActivityImageObject activityImage5;


    TextView groupShopName;
    ImageView shopImage1;
    ImageView shopImage2;
    ImageView shopImage3;
    ImageView shopImage4;
    ImageView shopImage5;

    int count=1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.home_fragment,container,false);

        //Home 화면 advertice 부분
        View advertice = view.findViewById(R.id.advertice);

        adverticeImage1 = (ImageView) advertice.findViewById(R.id.home_image1_ad);
        adverticeImage2 = (ImageView) advertice.findViewById(R.id.home_image2_ad);
        adverticeImage3 = (ImageView) advertice.findViewById(R.id.home_image3_ad);
        adverticeImage4 = (ImageView) advertice.findViewById(R.id.home_image4_ad);

        adverticeImage1.setImageResource(R.drawable.exo_all);
        adverticeImage2.setImageResource(R.drawable.girls_generation_all);
        adverticeImage3.setImageResource(R.drawable.exo_all);
        adverticeImage4.setImageResource(R.drawable.girls_generation_all);

        viewFlipper = (ViewFlipper) advertice.findViewById(R.id.home_viewflipper_ad);

        circleAnimIndicator = (CircleAnimIndicator) advertice.findViewById(R.id.home_advertice_indicator);
        previous = (Button) advertice.findViewById(R.id.home_prevbtn_ad);
        next = (Button) advertice.findViewById(R.id.home_nextbtn_ad);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>1&&count<5) {
                    viewFlipper.showPrevious();
                    count--;
                    circleAnimIndicator.selectDot(count-1);
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0&&count<4) {
                    viewFlipper.showNext();
                    count++;
                    circleAnimIndicator.selectDot(count-1);
                }
            }
        });

        //Home 화면 recommendActivity 부분
        View recommendActivity = view.findViewById(R.id.recommendactivity);

        groupActivityName = (TextView) recommendActivity.findViewById(R.id.home_activity_groupname);
        View v = recommendActivity.findViewById(R.id.home_activity_image1);
        //ImageView image = (ImageView) v.findViewById(R.id.activity_icon);
        //image.setImageResource(R.drawable.f);
        activityImage1 = new ActivityImageObject(v);
        activityImage2 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image2));
        activityImage3 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image3));
        activityImage4 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image4));
        activityImage5 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image5));

        groupActivityName.setText("Recommend Activity");
        activityImage1.setData(R.drawable.activityimagelight,R.drawable.f,
                "Water ski","River city's water ski","Beginer Lesson Package",300);
        activityImage2.setData(R.drawable.activityimagelight,R.drawable.f,
                "Water ski","River city's water ski","Beginer Lesson Package",300);
        activityImage3.setData(R.drawable.activityimagelight,R.drawable.f,
                "Water ski","River city's water ski","Beginer Lesson Package",300);
        activityImage4.setData(R.drawable.activityimagelight,R.drawable.f,
                "Water ski","River city's water ski","Beginer Lesson Package",300);
        activityImage5.setData(R.drawable.activityimagelight,R.drawable.f,
                "Water ski","River city's water ski","Beginer Lesson Package",300);


        //Home 화면 Best Shop 부분
        View bestshop = view.findViewById(R.id.bestshop);
        groupShopName = (TextView) bestshop.findViewById(R.id.home_groupname_shop);
        shopImage1 = (ImageView) bestshop.findViewById(R.id.home_image1_shop);
        shopImage2 = (ImageView) bestshop.findViewById(R.id.home_image2_shop);
        shopImage3 = (ImageView) bestshop.findViewById(R.id.home_image3_shop);
        shopImage4 = (ImageView) bestshop.findViewById(R.id.home_image4_shop);
        shopImage5 = (ImageView) bestshop.findViewById(R.id.home_image5_shop);

        groupShopName.setText("Best Shop");
        shopImage1.setImageResource(R.drawable.girls_generation_tifany);
        shopImage2.setImageResource(R.drawable.girls_generation_tifany);
        shopImage3.setImageResource(R.drawable.girls_generation_tifany);
        shopImage4.setImageResource(R.drawable.girls_generation_tifany);
        shopImage5.setImageResource(R.drawable.girls_generation_tifany);

        initIndicaotor();
        return view;
    }

    private void initIndicaotor(){

        //원사이의 간격
        circleAnimIndicator.setItemMargin(15);
        //애니메이션 속도
        circleAnimIndicator.setAnimDuration(300);
        //indecator 생성
        circleAnimIndicator.createDotPanel(4, R.mipmap.ic_launcher , R.mipmap.ic_launcher);
    }
}
