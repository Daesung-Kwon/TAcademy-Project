package com.leisurekr.leisuresportskorea.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.leisurekr.leisuresportskorea.FavorObject;
import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.MainActivity;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.shop.TabFragment2;
import com.leisurekr.leisuresportskorea.shop_detail.ShopDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mobile on 2017. 5. 11..
 */

public class TabFragment1 extends android.support.v4.app.Fragment implements View.OnClickListener{

    public static FragmentTransaction ft;
    public static TabFragment1 tabFragment1;
    static MainActivity owner;
    CircleAnimIndicator circleAnimIndicator;

    LinearLayout adverticeImage1;
    LinearLayout adverticeImage2;
    LinearLayout adverticeImage3;
    LinearLayout adverticeImage4;
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

    LinearLayout mShopMainImage1;
    LinearLayout dim1;
    ImageView mShopCircleImage1;
    TextView mShopName1;
    TextView mShopLocation1;
    TextView mShopRating1;
    ImageView heart1;
    ImageView share1;

    LinearLayout mShopMainImage2;
    LinearLayout dim2;
    ImageView mShopCircleImage2;
    TextView mShopName2;
    TextView mShopLocation2;
    TextView mShopRating2;
    ImageView heart2;
    ImageView share2;

    LinearLayout mShopMainImage3;
    LinearLayout dim3;
    ImageView mShopCircleImage3;
    TextView mShopName3;
    TextView mShopLocation3;
    TextView mShopRating3;
    ImageView heart3;
    ImageView share3;

    LinearLayout mShopMainImage4;
    LinearLayout dim4;
    ImageView mShopCircleImage4;
    TextView mShopName4;
    TextView mShopLocation4;
    TextView mShopRating4;
    ImageView heart4;
    ImageView share4;

    LinearLayout mShopMainImage5;
    LinearLayout dim5;
    ImageView mShopCircleImage5;
    TextView mShopName5;
    TextView mShopLocation5;
    TextView mShopRating5;
    ImageView heart5;
    ImageView share5;

    int count = 0;//뷰 플리퍼 레이아웃 체인지 리스너가 기본 1번 실행되므로 1을 더한수가 1이 되야함
    int count2 = 0;
    boolean flag=false;

    HomeObject homeObject;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.home_fragment, container, false);

        tabFragment1 = this;
        ft = getFragmentManager().beginTransaction();


        owner = (MainActivity)getActivity();
        count=0;
        count2=0;

        Log.e("viewFliper", "onCreateView()");
        //Home 화면 advertice 부분
        View advertice = view.findViewById(R.id.advertice);

        adverticeImage1 = (LinearLayout) advertice.findViewById(R.id.home_image1_ad);
        adverticeImage2 = (LinearLayout) advertice.findViewById(R.id.home_image2_ad);
        adverticeImage3 = (LinearLayout) advertice.findViewById(R.id.home_image3_ad);
        adverticeImage4 = (LinearLayout) advertice.findViewById(R.id.home_image4_ad);

        adverticeImage1.setOnClickListener(this);
        adverticeImage2.setOnClickListener(this);
        adverticeImage3.setOnClickListener(this);
        adverticeImage4.setOnClickListener(this);

        viewFlipper = (ViewFlipper) advertice.findViewById(R.id.home_viewflipper_ad);
        viewFlipper.setFlipInterval(3000);//자동 넘김 시간 3초로 설정
        viewFlipper.setAutoStart(true);//자동으로 실행되며 마지막에 도달하면 처음부터 다시시작
                                        //.startFlipping()의 경우 마지막에 도달하면 멈춘다.*/

        //화면이 변할때마다 카운트 변수를 관리하여 인디케이터를 알맞게 조정한다.
        viewFlipper.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Log.e("viewFliper", " "+count2);
                if(count2<13)
                    ++count2;

                if(count2<=8&&count2%2==0) {

                        count++; //레이아웃이 변하면 카운트를 증가시키고
                        Log.e("viewFliper", "change" + count);
                        if (count > 4)  //4보다 커지면 다시 첫번째를 의미하므로 1로 변경
                            count = 1;
                        else if (count < 1) //1보다 작아지면 마지막번째인 4번째를 의미하므로 4로 변경
                            count = 4;
                        circleAnimIndicator.selectDot(count - 1); //인디케이터는 배열 인덱스 방식이므로 -1을 한다.
                        flag = false;
                }else if(count2>8){
                    count++; //레이아웃이 변하면 카운트를 증가시키고
                    Log.e("viewFliper", "change" + count);
                    if (count > 4)  //4보다 커지면 다시 첫번째를 의미하므로 1로 변경
                        count = 1;
                    else if (count < 1) //1보다 작아지면 마지막번째인 4번째를 의미하므로 4로 변경
                        count = 4;
                    circleAnimIndicator.selectDot(count - 1); //인디케이터는 배열 인덱스 방식이므로 -1을 한다.
                }
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
                flag=true;
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
                flag=true;
            }
        });

        //Home 화면 recommendActivity 부분
        View recommendActivity = view.findViewById(R.id.recommendactivity);

        groupActivityName = (TextView) recommendActivity.findViewById(R.id.home_activity_groupname);

        activityImage1 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image1));
        activityImage2 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image2));
        activityImage3 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image3));
        activityImage4 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image4));
        activityImage5 = new ActivityImageObject(recommendActivity.findViewById(R.id.home_activity_image5));

        activityImage1.setOnClick(this);
        activityImage2.setOnClick(this);
        activityImage3.setOnClick(this);
        activityImage4.setOnClick(this);
        activityImage5.setOnClick(this);

        groupActivityName.setText("Recommended Activities");

        //Home 화면 Best Shop 부분
        View bestshop = view.findViewById(R.id.bestshop);
        View shop1 = bestshop.findViewById(R.id.home_image1_shop);
        View shop2 = bestshop.findViewById(R.id.home_image2_shop);
        View shop3 = bestshop.findViewById(R.id.home_image3_shop);
        View shop4 = bestshop.findViewById(R.id.home_image4_shop);
        View shop5 = bestshop.findViewById(R.id.home_image5_shop);

        mShopMainImage1 = (LinearLayout) shop1.findViewById(R.id.bestshop_main_image);
        dim1 = (LinearLayout) shop1.findViewById(R.id.bestshop_dim);
        mShopCircleImage1 = (ImageView) shop1.findViewById(R.id.bestshop_circle_image);
        mShopName1 = (TextView) shop1.findViewById(R.id.bestshop_name_text);
        mShopLocation1 = (TextView) shop1.findViewById(R.id.bestshop_location_text);
        mShopRating1 = (TextView) shop1.findViewById(R.id.bestshop_rating_text);
        heart1 = (ImageView) shop1.findViewById(R.id.favorite_item_icon_in_shop);
        share1 = (ImageView) shop1.findViewById(R.id.share_item_icon_in_shop);
        heart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("heart in home","click");
                final FavorObject favorObject = new FavorObject();
                favorObject.setShopId(shops.get(0).id);
                favorObject.setUserId(1);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("heart","전");
                        final String result = OkHttpAPIHelperHandler.favorJSONInsert(favorObject);
                        Log.e("heart","후: "+result);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(result.equals("success")) {
                                    if(heart1.isSelected()) {
                                        heart1.setImageResource(R.drawable.btn_heart_unpress);
                                        heart1.setSelected(false);
                                    }else{
                                        heart1.setImageResource(R.drawable.btn_heart_press);
                                        heart1.setSelected(true);
                                    }
                                }
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.detach(TabFragment2.tabFragment2)
                                        .attach(TabFragment2.tabFragment2).commit();
                            }
                        });
                    }
                }).start();

            }
        });
        mShopMainImage2 = (LinearLayout) shop2.findViewById(R.id.bestshop_main_image);
        dim2 = (LinearLayout) shop2.findViewById(R.id.bestshop_dim);
        mShopCircleImage2 = (ImageView) shop2.findViewById(R.id.bestshop_circle_image);
        mShopName2 = (TextView) shop2.findViewById(R.id.bestshop_name_text);
        mShopLocation2 = (TextView) shop2.findViewById(R.id.bestshop_location_text);
        mShopRating2 = (TextView) shop2.findViewById(R.id.bestshop_rating_text);
        heart2 = (ImageView) shop2.findViewById(R.id.favorite_item_icon_in_shop);
        share2 = (ImageView) shop2.findViewById(R.id.share_item_icon_in_shop);
        heart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("heart in home","click");
                final FavorObject favorObject = new FavorObject();
                favorObject.setShopId(shops.get(1).id);
                favorObject.setUserId(1);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String result = OkHttpAPIHelperHandler.favorJSONInsert(favorObject);
                        owner.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("heart",result);
                                if(result.equals("success")) {
                                    if(heart2.isSelected()) {
                                        heart2.setImageResource(R.drawable.btn_heart_unpress);
                                        heart2.setSelected(false);
                                    }else{
                                        heart2.setImageResource(R.drawable.btn_heart_press);
                                        heart2.setSelected(true);
                                    }
                                }
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.detach(TabFragment2.tabFragment2)
                                        .attach(TabFragment2.tabFragment2).commit();
                            }
                        });
                    }
                }).start();

            }
        });
        mShopMainImage3 = (LinearLayout) shop3.findViewById(R.id.bestshop_main_image);
        dim3 = (LinearLayout) shop3.findViewById(R.id.bestshop_dim);
        mShopCircleImage3 = (ImageView) shop3.findViewById(R.id.bestshop_circle_image);
        mShopName3 = (TextView) shop3.findViewById(R.id.bestshop_name_text);
        mShopLocation3 = (TextView) shop3.findViewById(R.id.bestshop_location_text);
        mShopRating3 = (TextView) shop3.findViewById(R.id.bestshop_rating_text);
        heart3 = (ImageView) shop3.findViewById(R.id.favorite_item_icon_in_shop);
        share3 = (ImageView) shop3.findViewById(R.id.share_item_icon_in_shop);
        heart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("heart in home","click");
                final FavorObject favorObject = new FavorObject();
                favorObject.setShopId(shops.get(2).id);
                favorObject.setUserId(1);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String result = OkHttpAPIHelperHandler.favorJSONInsert(favorObject);
                        owner.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("heart",result);
                                if(result.equals("success")) {
                                    if(heart3.isSelected()) {
                                        heart3.setImageResource(R.drawable.btn_heart_unpress);
                                        heart3.setSelected(false);
                                    }else{
                                        heart3.setImageResource(R.drawable.btn_heart_press);
                                        heart3.setSelected(true);
                                    }
                                }
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.detach(TabFragment2.tabFragment2)
                                        .attach(TabFragment2.tabFragment2).commit();
                            }
                        });
                    }
                }).start();

            }
        });
        mShopMainImage4 = (LinearLayout) shop4.findViewById(R.id.bestshop_main_image);
        dim4 = (LinearLayout) shop4.findViewById(R.id.bestshop_dim);
        mShopCircleImage4 = (ImageView) shop4.findViewById(R.id.bestshop_circle_image);
        mShopName4 = (TextView) shop4.findViewById(R.id.bestshop_name_text);
        mShopLocation4 = (TextView) shop4.findViewById(R.id.bestshop_location_text);
        mShopRating4 = (TextView) shop4.findViewById(R.id.bestshop_rating_text);
        heart4 = (ImageView) shop4.findViewById(R.id.favorite_item_icon_in_shop);
        share4 = (ImageView) shop4.findViewById(R.id.share_item_icon_in_shop);
        heart4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("heart in home","click");
                final FavorObject favorObject = new FavorObject();
                favorObject.setShopId(shops.get(3).id);
                favorObject.setUserId(1);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String result = OkHttpAPIHelperHandler.favorJSONInsert(favorObject);
                        owner.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("heart",result);
                                if(result.equals("success")) {
                                    if(heart4.isSelected()) {
                                        heart4.setImageResource(R.drawable.btn_heart_unpress);
                                        heart4.setSelected(false);
                                    }else{
                                        heart4.setImageResource(R.drawable.btn_heart_press);
                                        heart4.setSelected(true);
                                    }
                                }
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.detach(TabFragment2.tabFragment2)
                                        .attach(TabFragment2.tabFragment2).commit();
                            }
                        });
                    }
                }).start();

            }
        });
        mShopMainImage5 = (LinearLayout) shop5.findViewById(R.id.bestshop_main_image);
        dim5 = (LinearLayout) shop5.findViewById(R.id.bestshop_dim);
        mShopCircleImage5 = (ImageView) shop5.findViewById(R.id.bestshop_circle_image);
        mShopName5 = (TextView) shop5.findViewById(R.id.bestshop_name_text);
        mShopLocation5 = (TextView) shop5.findViewById(R.id.bestshop_location_text);
        mShopRating5 = (TextView) shop5.findViewById(R.id.bestshop_rating_text);
        heart5 = (ImageView) shop5.findViewById(R.id.favorite_item_icon_in_shop);
        share5 = (ImageView) shop5.findViewById(R.id.share_item_icon_in_shop);
        heart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("heart in home","click");
                final FavorObject favorObject = new FavorObject();
                favorObject.setShopId(shops.get(4).id);
                favorObject.setUserId(1);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String result = OkHttpAPIHelperHandler.favorJSONInsert(favorObject);
                        owner.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("heart",result);
                                if(result.equals("success")) {
                                    if(heart5.isSelected()) {
                                        heart5.setImageResource(R.drawable.btn_heart_unpress);
                                        heart5.setSelected(false);
                                    }else{
                                        heart5.setImageResource(R.drawable.btn_heart_press);
                                        heart5.setSelected(true);
                                    }
                                }
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.detach(TabFragment2.tabFragment2)
                                        .attach(TabFragment2.tabFragment2).commit();
                            }
                        });
                    }
                }).start();

            }
        });

        groupShopName = (TextView) bestshop.findViewById(R.id.home_groupname_shop);

        groupShopName.setText("Best Shop");
        initIndicaotor();

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("viewFliper", "onViewCreated()");

        new AsyncShopInfoJSONList().execute();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Log.e("TabFragment1", R.id.home_image1_shop+"/"+v.getId());
        switch (v.getId()){
            case R.id.home_image1_ad:
                intent = new Intent(v.getContext(), ShopDetailActivity.class);
                intent.putExtra("shopId", banners.get(0).id); // shopInfo.shopId
                owner.startActivity(intent);
                break;
            case R.id.home_image2_ad:
                intent = new Intent(v.getContext(), ShopDetailActivity.class);
                intent.putExtra("shopId", banners.get(1).id); // shopInfo.shopId
                owner.startActivity(intent);
                break;
            case R.id.home_image3_ad:
                intent = new Intent(v.getContext(), ShopDetailActivity.class);
                intent.putExtra("shopId", banners.get(2).id); // shopInfo.shopId
                owner.startActivity(intent);
                break;
            case R.id.home_image4_ad:
                intent = new Intent(v.getContext(), ShopDetailActivity.class);
                intent.putExtra("shopId", banners.get(3).id); // shopInfo.shopId
                owner.startActivity(intent);
                break;
            case R.id.home_activity_image1:
                intent = new Intent(v.getContext(), ShopDetailActivity.class);
                intent.putExtra("shopId", activities.get(0).shopId); // shopInfo.shopId
                owner.startActivity(intent);
                break;
            case R.id.home_activity_image2:
                intent = new Intent(v.getContext(), ShopDetailActivity.class);
                intent.putExtra("shopId", activities.get(1).shopId); // shopInfo.shopId
                owner.startActivity(intent);
                break;
            case R.id.home_activity_image3:
                intent = new Intent(v.getContext(), ShopDetailActivity.class);
                intent.putExtra("shopId", activities.get(2).shopId); // shopInfo.shopId
                owner.startActivity(intent);
                break;
            case R.id.home_activity_image4:
                intent = new Intent(v.getContext(), ShopDetailActivity.class);
                intent.putExtra("shopId", activities.get(3).shopId); // shopInfo.shopId
                owner.startActivity(intent);
                break;
            case R.id.home_activity_image5:
                intent = new Intent(v.getContext(), ShopDetailActivity.class);
                intent.putExtra("shopId", activities.get(4).shopId); // shopInfo.shopId
                owner.startActivity(intent);
                break;
        }
    }


    public class AsyncShopInfoJSONList
            extends AsyncTask<String, Integer, HomeObject> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dialog = ProgressDialog.show(getContext(), "", "Data Loading...", true);
        }

        @Override
        protected HomeObject doInBackground(String... params) {
            return OkHttpAPIHelperHandler.homeJSONAllSelect();
        }

        @Override
        protected void onPostExecute(HomeObject result) {

            //dialog.dismiss();
            if (result == null) {
                Log.i("TabFramgent1","result is null");
            }else {
                homeObject = result;
                updateUi();
            }
        }
    }

    ArrayList<BannerObject> banners;
    ArrayList<ActivityObject> activities;
    ArrayList<ShopObject> shops;

    public void updateUi() {

        if(homeObject!=null) {
            banners = homeObject.banners;
            activities = homeObject.activities;
            shops = homeObject.shops;
        }

        if(banners!=null&&banners.size()==4) {

            //Log.e("banner image",bannerTest.banners.get(0).image);
            Glide.with(this).load(banners.get(0).image).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new ViewTarget<LinearLayout, GlideDrawable>(adverticeImage1) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            adverticeImage1.setBackground(resource);
                        }
                    });
            Glide.with(this).load(banners.get(1).image).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new ViewTarget<LinearLayout, GlideDrawable>(adverticeImage2) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            adverticeImage2.setBackground(resource);
                        }
                    });
            Glide.with(this).load(banners.get(2).image).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new ViewTarget<LinearLayout, GlideDrawable>(adverticeImage3) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            adverticeImage3.setBackground(resource);
                        }
                    });
            Glide.with(this).load(banners.get(3).image).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new ViewTarget<LinearLayout, GlideDrawable>(adverticeImage4) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            adverticeImage4.setBackground(resource);
                        }
                    });
        }

        if(activities!=null&&activities.size()==5) {
            activityImage1.setData(activities.get(0));
            activityImage2.setData(activities.get(1));
            activityImage3.setData(activities.get(2));
            activityImage4.setData(activities.get(3));
            activityImage5.setData(activities.get(4));
        }

        if(activities!=null&&shops.size()==5) {
            Glide.with(LKApplication.getLKApplication()).load(shops.get(0).image)
                    .into(new ViewTarget<LinearLayout,GlideDrawable>(mShopMainImage1) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            mShopMainImage1.setBackground(resource);
                        }
                    });
            mShopMainImage1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
                    intent.putExtra("shopId", shops.get(0).id); // shopInfo.shopId
                    owner.startActivity(intent);
                }
            });
            Glide.with(LKApplication.getLKApplication()).load(shops.get(0).logoImage)
                    .into(mShopCircleImage1);
            share1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendShare();
                }
            });
            dim1.setAlpha(0.9f);
            mShopName1.setText(shops.get(0).shopName);
            mShopLocation1.setText(shops.get(0).address2 + ", " + shops.get(0).address1);
            mShopRating1.setText(Double.toString(shops.get(0).score));

            if (shops.get(0).likes) {
                heart1.setImageResource(R.drawable.btn_heart_press);
                heart1.setSelected(true);
            } else {
                heart1.setImageResource(R.drawable.btn_heart_unpress);
                heart1.setSelected(false);
            }

            Glide.with(LKApplication.getLKApplication()).load(shops.get(1).image)
                    .into(new ViewTarget<LinearLayout,GlideDrawable>(mShopMainImage2) {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    mShopMainImage2.setBackground(resource);
                }
            });
            mShopMainImage2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
                    intent.putExtra("shopId", shops.get(1).id); // shopInfo.shopId
                    owner.startActivity(intent);
                }
            });
            Glide.with(LKApplication.getLKApplication()).load(shops.get(1).logoImage)
                    .into(mShopCircleImage2);
            dim2.setAlpha(0.9f);
            mShopName2.setText(shops.get(1).shopName);
            mShopLocation2.setText(shops.get(1).address2 + ", " + shops.get(1).address1);
            mShopRating2.setText(Double.toString(shops.get(1).score));

            if (shops.get(1).likes) {
                heart2.setImageResource(R.drawable.btn_heart_press);
                heart2.setSelected(true);
            } else {
                heart2.setImageResource(R.drawable.btn_heart_unpress);
                heart2.setSelected(false);
            }
            share2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendShare();
                }
            });


            Glide.with(LKApplication.getLKApplication()).load(shops.get(2).image)
                    .into(new ViewTarget<LinearLayout,GlideDrawable>(mShopMainImage3) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            mShopMainImage3.setBackground(resource);
                        }
                    });
            mShopMainImage3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
                    intent.putExtra("shopId", shops.get(2).id); // shopInfo.shopId
                    owner.startActivity(intent);
                }
            });
            Glide.with(LKApplication.getLKApplication()).load(shops.get(0).logoImage)
                    .into(mShopCircleImage3);
            dim3.setAlpha(0.9f);
            mShopName3.setText(shops.get(2).shopName);
            mShopLocation3.setText(shops.get(2).address2 + ", " + shops.get(2).address1);
            mShopRating3.setText(Double.toString(shops.get(2).score));

            if (shops.get(2).likes) {
                heart3.setImageResource(R.drawable.btn_heart_press);
                heart3.setSelected(true);
            } else {
                heart3.setImageResource(R.drawable.btn_heart_unpress);
                heart3.setSelected(false);
            }
            share3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendShare();
                }
            });

            Glide.with(LKApplication.getLKApplication()).load(shops.get(3).image)
                    .into(new ViewTarget<LinearLayout,GlideDrawable>(mShopMainImage4) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            mShopMainImage4.setBackground(resource);
                        }
                    });
            mShopMainImage4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
                    intent.putExtra("shopId", shops.get(3).id); // shopInfo.shopId
                    owner.startActivity(intent);
                }
            });
            Glide.with(LKApplication.getLKApplication()).load(shops.get(3).logoImage)
                    .into(mShopCircleImage4);
            dim4.setAlpha(0.9f);
            mShopName4.setText(shops.get(3).shopName);
            mShopLocation4.setText(shops.get(3).address2 + ", " + shops.get(3).address1);
            mShopRating4.setText(Double.toString(shops.get(3).score));

            if (shops.get(3).likes) {
                heart4.setImageResource(R.drawable.btn_heart_press);
                heart4.setSelected(true);
            } else {
                heart4.setImageResource(R.drawable.btn_heart_unpress);
                heart4.setSelected(false);
            }
            share4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendShare();
                }
            });

            Glide.with(LKApplication.getLKApplication()).load(shops.get(4).image)
                    .into(new ViewTarget<LinearLayout,GlideDrawable>(mShopMainImage5) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            mShopMainImage5.setBackground(resource);
                        }
                    });
            mShopMainImage5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
                    intent.putExtra("shopId", shops.get(4).id); // shopInfo.shopId
                    owner.startActivity(intent);
                }
            });
            Glide.with(LKApplication.getLKApplication()).load(shops.get(4).logoImage)
                    .into(mShopCircleImage5);
            dim5.setAlpha(0.9f);
            mShopName5.setText(shops.get(4).shopName);
            mShopLocation5.setText(shops.get(4).address2 + ", " + shops.get(4).address1);
            mShopRating5.setText(Double.toString(shops.get(4).score));

            if (shops.get(4).likes) {
                heart5.setImageResource(R.drawable.btn_heart_press);
                heart5.setSelected(true);
            } else {
                heart5.setImageResource(R.drawable.btn_heart_unpress);
                heart5.setSelected(false);
            }
            share5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendShare();
                }
            });
        }
    }

    private void initIndicaotor() {

        //원사이의 간격
        circleAnimIndicator.setItemMargin(15);
        //애니메이션 속도
        circleAnimIndicator.setAnimDuration(300);
        //indecator 생성
        circleAnimIndicator.setSavePosition(0);
        circleAnimIndicator.createDotPanel(4
                , R.drawable.icon_navi_unpress, R.drawable.icon_navi_press);
    }

    private void sendShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");

        List<ResolveInfo> resInfo = owner.getPackageManager().queryIntentActivities(intent, 0);
        if (resInfo.isEmpty()) {
            return;
        }

        List<Intent> shareIntentList = new ArrayList<Intent>();

        for (ResolveInfo info : resInfo) {
            Intent shareIntent = (Intent) intent.clone();

            if (info.activityInfo.packageName.toLowerCase().equals("com.facebook.katana")) {
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http:/leisurekr.com");
            } else if(info.activityInfo.packageName.toLowerCase().equals("com.kakao.talk")) {
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http:/leisurekr.com");
            }else{
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http:/leisurekr.com");
            }
            shareIntent.setPackage(info.activityInfo.packageName);
            shareIntentList.add(shareIntent);
        }

        Intent chooserIntent = Intent.createChooser(shareIntentList.remove(0), "select");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, shareIntentList.toArray(new Parcelable[]{}));
        startActivity(chooserIntent);
    }
}
