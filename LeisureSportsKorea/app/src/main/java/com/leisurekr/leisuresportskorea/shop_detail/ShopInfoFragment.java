package com.leisurekr.leisuresportskorea.shop_detail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.leisurekr.leisuresportskorea.FavorObject;
import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.home.CircleAnimIndicator;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
<<<<<<< HEAD
=======
import com.leisurekr.leisuresportskorea.shop.ShopInfoOnMapObject;
import com.leisurekr.leisuresportskorea.shop.TabFragment2;

import java.util.ArrayList;
import java.util.List;
>>>>>>> 771d769530fe477a0efb97c4f2d3b0501c7385fb

/**
 * Created by mobile on 2017. 5. 31..
 */

public class ShopInfoFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    static ShopDetailActivity owner;
    LKShopInfoObject shopInfoObject;
    View view;
    Intent intent;

    ViewFlipper viewFlipper;
    GridView interestGridView;
    GridView serviceGridView;
    GridView prepareGridView;
    ImageView shopImage1;
    ImageView shopImage2;
    ImageView shopImage3;
    ImageView shopImage4;
    ImageView shopLogoImage;

    ImageView shareImageBtn;
    ImageView myFavoriteBtn;
    ImageView transportIcon;

    Button previousImageBtn;
    Button nextImageBtn;
    TextView reviewMoreBtn;
    ImageView callingBtn;
    ImageView emailContactBtn;

    CircleAnimIndicator circleAnimIndicator;

    MapView mapView;
    GoogleMap gMap;

    TextView shopName;
    TextView shopTotalRating;
    TextView shopLocation;
    LatLng shopLatLng;

    int currentPage = 1;
    static int shopImagesSize = 0;
    static int mShopId = -1;

    static final String[] shopActivityTags = new String[] {
            "0", "0", "0", "0",
            "0", "0", "0", "0",
            "0", "0", "0", "0"
    };
    static final String[] serviceValues = new String[] {
            "0", "0", "0",
            "0", "0", "0"
    };
    static final String[] prepareValues = new String[] {
            "0", "0", "0", "0",
    };

    //private Animation slideInAnimation;
    static final String MALE = "Male";
    static final String FEMALE = "Female";


    public ShopInfoFragment() {}
    public static ShopInfoFragment newInstance(int shopId) {
        ShopInfoFragment shopInfoFragment = new ShopInfoFragment();
        mShopId = shopId;
        Log.i("shopId", ""+mShopId);

        return shopInfoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        owner = (ShopDetailActivity)getActivity();

        view                = inflater.inflate(R.layout.include_shop_info_section1, container, false);
        circleAnimIndicator = (CircleAnimIndicator) view.findViewById(R.id.shop_info_flipper_indicator);
        mapView             = (MapView) view.findViewById(R.id.map_on_shop_info);

        viewFlipper         = (ViewFlipper) view.findViewById(R.id.shop_info_image_flipper);
        shopName            = (TextView) view.findViewById(R.id.shop_name_text1);
        shopLocation        = (TextView) view.findViewById(R.id.shop_location_text);
        shopTotalRating     = (TextView) view.findViewById(R.id.shop_rating_text);
        shareImageBtn       = (ImageView) view.findViewById(R.id.share_item_icon);
        myFavoriteBtn       = (ImageView) view.findViewById(R.id.favorite_item_icon);
        interestGridView    = (GridView) view.findViewById(R.id.shop_info_grid_view1);
        serviceGridView     = (GridView) view.findViewById(R.id.shop_info_grid_view2);
        prepareGridView     = (GridView) view.findViewById(R.id.shop_info_grid_view3);
        shopLogoImage       = (ImageView) view.findViewById(R.id.shop_detail_circle_image);
        shopImage1          = (ImageView) view.findViewById(R.id.shop_image_flipper1);
        shopImage2          = (ImageView) view.findViewById(R.id.shop_image_flipper2);
        shopImage3          = (ImageView) view.findViewById(R.id.shop_image_flipper3);
        shopImage4          = (ImageView) view.findViewById(R.id.shop_image_flipper4);
        previousImageBtn    = (Button) view.findViewById(R.id.shop_info_prevbtn);
        nextImageBtn        = (Button) view.findViewById(R.id.shop_info_nextbtn);
        reviewMoreBtn       = (TextView) view.findViewById(R.id.review_more_btn);
        transportIcon       = (ImageView) view.findViewById(R.id.transport_icon);
        callingBtn          = (ImageView) view.findViewById(R.id.call_btn);
        emailContactBtn     = (ImageView) view.findViewById(R.id.email_btn);
        aboutShop           = (TextView) view.findViewById(R.id.description_about_shop);
        getThere1           = (TextView) view.findViewById(R.id.get_there_text1);
        getThere2           = (TextView) view.findViewById(R.id.get_there_text2);
        reviewerCircleImage = (ImageView) view.findViewById(R.id.reviewer_circle_image);
        reviewer_name       = (TextView) view.findViewById(R.id.reviewer_name);
        review_date         = (TextView) view.findViewById(R.id.review_date);
        review_text         = (TextView) view.findViewById(R.id.review_text);
        refundPolicy        = (TextView) view.findViewById(R.id.refund_policy);

        mapView.onCreate(savedInstanceState);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new AsyncShopInfoJSONList().execute();

        myFavoriteBtn.setOnClickListener(this);
        shareImageBtn.setOnClickListener(this);
        previousImageBtn.setOnClickListener(this);
        nextImageBtn.setOnClickListener(this);
        callingBtn.setOnClickListener(this);
        emailContactBtn.setOnClickListener(this);
        reviewMoreBtn.setOnClickListener(this);
    }

    TextView aboutShop;
    TextView getThere1;
    TextView getThere2;
    TextView refundPolicy;
    public void setContentsText() {
        aboutShop.setText(shopInfoObject.about);
        getThere1.setText(shopInfoObject.howTo1);
        getThere2.setText(shopInfoObject.howTo2);
        refundPolicy.setText(shopInfoObject.refundPoliy);
    }

    public void setShopInfoText() {
        shopName.setText(shopInfoObject.name);
        shopLocation.setText(shopInfoObject.address1);
        shopTotalRating.setText(String.valueOf(shopInfoObject.score));
    }

    ImageView reviewerCircleImage;
    TextView reviewer_name;
    TextView review_date;
    TextView review_text;

    public void setReviewerInfo(int count) {
        //int count = shopInfoObject.reviewsObject.count;
        if (count > 0) {
            switch (shopInfoObject.reviewsObject.sex) {
                case "Male":
                    reviewerCircleImage.setImageResource(R.drawable.ic_ma);
                    break;
                case "Female":
                    reviewerCircleImage.setImageResource(R.drawable.ic_fe);
                    break;
                default:
                    reviewerCircleImage.setImageResource(R.drawable.ic_ne);
                    break;
            }
            reviewer_name.setText(shopInfoObject.reviewsObject.userName);
            review_date.setText(shopInfoObject.reviewsObject.date);
            review_text.setText(shopInfoObject.reviewsObject.review);
            reviewMoreBtn.setText("View other " + shopInfoObject.reviewsObject.count + " review >");
        }else {
            reviewerCircleImage.setImageResource(R.drawable.ic_ne);
            reviewer_name.setText("N/A");
            review_date.setText("00-00-00");
            review_text.setText("N/A");
            reviewMoreBtn.setText("No other review ...");
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initIndicator() {
        circleAnimIndicator.setItemMargin(10);
        circleAnimIndicator.setAnimDuration(300);
        circleAnimIndicator.createDotPanel(4, R.drawable.icon_navi_unpress, R.drawable.icon_navi_press);
    }

    private void initVariableIndicator(int maxIndicator) {
        circleAnimIndicator.setItemMargin(10);
        circleAnimIndicator.setAnimDuration(300);
        circleAnimIndicator.createDotPanel(maxIndicator, R.drawable.icon_navi_unpress, R.drawable.icon_navi_press);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call_btn:
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-2056-7973"));
                startActivity(intent1);
                break;
            case R.id.email_btn:
                sendEmail();
                break;
            case R.id.shop_info_prevbtn:
                if(currentPage > 1 && currentPage < 5) {
                    viewFlipper.showPrevious();
                    currentPage--;
                    circleAnimIndicator.selectDot(currentPage-1);
                }
                break;
            case R.id.shop_info_nextbtn:
                if(currentPage > 0 && currentPage < 4) {
                    viewFlipper.showNext();
                    currentPage++;
                    circleAnimIndicator.selectDot(currentPage-1);
                }
                break;
            case R.id.review_more_btn:
                if (shopInfoObject.reviewsObject.count > 0) {
                    intent = new Intent(getActivity(), ReviewActivity.class);
                    intent.putExtra("shopId", mShopId);
                    startActivity(intent);
                }
                break;
            case R.id.share_item_icon:
                sendShare();
                break;
            case R.id.favorite_item_icon:
                changeFavorite();
                break;
        }
    }

    public void sendEmail() {
        String uriText = "mailto:kownds.ken@gmail.com" +
                "?subject=" + Uri.encode("Hello, Leisure Korea...") +
                "&body=" + Uri.encode("to Leisure Korea,\n");
        Uri uri = Uri.parse(uriText);
        Intent intent2 = new Intent(Intent.ACTION_SENDTO);
        intent2.setData(uri);
        startActivity(Intent.createChooser(intent2, "Send email..."));
    }

    public void changeFavorite() {
        Log.e("heart in home","click");
        final FavorObject favorObject = new FavorObject();
        favorObject.setShopId(mShopId);
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
                            if(myFavoriteBtn.isSelected()) {
                                myFavoriteBtn.setImageResource(R.drawable.btn_heart_unpress);
                                myFavoriteBtn.setSelected(false);
                            }else{
                                myFavoriteBtn.setImageResource(R.drawable.btn_heart_press);
                                myFavoriteBtn.setSelected(true);
                            }
                        }
                    }
                });
            }
        }).start();
    }

    public class AsyncShopInfoJSONList
            extends AsyncTask<String, Integer, LKShopInfoObject> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Log.e("test","!1 ");
            dialog = ProgressDialog.show(owner, "", "Loading...", true);
        }

        @Override
        protected LKShopInfoObject doInBackground(String... params) {
            //Log.e("test","!2 ");
            return OkHttpAPIHelperHandler.shopInfoJSONALLSelect(mShopId);
        }
        @Override
        protected void onPostExecute(LKShopInfoObject result) {
            //Log.e("test","!3 ");
            dialog.dismiss();
            //Log.e("test","!3 ");
            if (result != null) {
                shopInfoObject = result;

                shopImagesSize = shopInfoObject.shopImages.size();
                initVariableIndicator(shopImagesSize);
                setShopImages();
                setShopActivities();
                setShopServies();
                setShopPrepareThings();

                setShopInfoText();
                setContentsText();
                setReviewerInfo(shopInfoObject.reviewsObject.count);
                Log.i("review count", ""+shopInfoObject.reviewsObject.count);

                interestGridView.setAdapter(new CategoryGridAdapter(getContext(), shopActivityTags));
                serviceGridView.setAdapter(new ServiceGridAdapter(getContext(), serviceValues));
                prepareGridView.setAdapter(new PrepareGridAdapter(getContext(), prepareValues));

                emailContactBtn.setImageResource(R.drawable.ic_email);
                callingBtn.setImageResource(R.drawable.ic_call);

                transportIcon.setImageResource(R.drawable.ic_publictransport);
                //transportIcon.setImageResource(TransportList.getSubwayResource().get(3));

                mapView.getMapAsync(ShopInfoFragment.this);
            }
            else
                Log.e("test","result = null ");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        LatLng shopLatLng = new LatLng(shopInfoObject.latitude,
                shopInfoObject.longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(shopLatLng);
        //markerOptions.anchor(0.5f, 1);
        markerOptions.title(shopInfoObject.address2 + ", " + shopInfoObject.address1);
        markerOptions.snippet(shopInfoObject.address3);

        final Marker marker = gMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(shopLatLng, 8);
        gMap.animateCamera(cameraUpdate, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() { marker.showInfoWindow(); }
            @Override
            public void onCancel() { }
        });
    }

    public void setShopImages() {
        int count = shopImagesSize;
        // 업체 샵 대표이미지
        switch (count) {
            case 1:
                Glide.with(LKApplication.getLKApplication())
                        .load(shopInfoObject.shopImages.get(0))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(shopImage1);
                break;

            case 2:
                Glide.with(LKApplication.getLKApplication())
                        .load(shopInfoObject.shopImages.get(0))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(shopImage1);
                Glide.with(LKApplication.getLKApplication())
                        .load(shopInfoObject.shopImages.get(1))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(shopImage2);
                break;
            case 3:
                Glide.with(LKApplication.getLKApplication())
                        .load(shopInfoObject.shopImages.get(0))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(shopImage1);
                Glide.with(LKApplication.getLKApplication())
                        .load(shopInfoObject.shopImages.get(1))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(shopImage2);
                Glide.with(LKApplication.getLKApplication())
                        .load(shopInfoObject.shopImages.get(2))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(shopImage3);
                break;
            case 4:
                Glide.with(LKApplication.getLKApplication())
                        .load(shopInfoObject.shopImages.get(0))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(shopImage1);
                Glide.with(LKApplication.getLKApplication())
                        .load(shopInfoObject.shopImages.get(1))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(shopImage2);
                Glide.with(LKApplication.getLKApplication())
                        .load(shopInfoObject.shopImages.get(2))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(shopImage3);
                Glide.with(LKApplication.getLKApplication())
                        .load(shopInfoObject.shopImages.get(3))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(shopImage4);
                break;
        }

        // 업체 로고 이미지
        Glide.with(LKApplication.getLKApplication())
                .load(shopInfoObject.image) // image URL
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(shopLogoImage);
    }

    public void setShopActivities() {

        for (int i = 0; i < shopInfoObject.shopActivityTag.size(); i++) {
            if (shopInfoObject.shopActivityTag.get(i).equals(Boolean.TRUE)) {
                /*Log.e("test",""+shopInfoObject.shopActivityTag.get(i));
                Log.e("test",""+i);*/
                shopActivityTags[i] = "1";
            }
        }
    }

    public void setShopServies() {
        if (shopInfoObject.isPickUp == true) {
            serviceValues[0] = "1";
        }
        if (shopInfoObject.isBasicEnglish == true) {
            serviceValues[1] = "1";
        }
        if (shopInfoObject.isBasicChinese == true) {
            serviceValues[2] = "1";
        }
        if (shopInfoObject.isLockerRoom == true) {
            serviceValues[3] = "1";
        }
        if (shopInfoObject.isShowerRoom == true) {
            serviceValues[4] = "1";
        }
        if (shopInfoObject.isParkingLot == true) {
            serviceValues[5] = "1";
        }
    }

    public void setShopPrepareThings() {
        if (shopInfoObject.isClothsForChange == true) {
            prepareValues[0] = "1";
        }
        if (shopInfoObject.isTowels == true) {
            prepareValues[1] = "1";
        }
        if (shopInfoObject.isSunBlock == true) {
            prepareValues[2] = "1";
        }
        if (shopInfoObject.isWashingKit == true) {
            prepareValues[3] = "1";
        }
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
            //shareIntent.setPackage(info.activityInfo.packageName);
            shareIntentList.add(shareIntent);
        }

        Intent chooserIntent = Intent.createChooser(shareIntentList.remove(0), "select");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, shareIntentList.toArray(new Parcelable[]{}));
        startActivity(chooserIntent);
    }
}
