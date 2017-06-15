package com.leisurekr.leisuresportskorea.shop_detail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.home.CircleAnimIndicator;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.shop.ShopInfoOnMapObject;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 5. 31..
 */

public class ShopInfoFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    static ShopDetailActivity owner;
    static LKShopInfoObject shopInfoObject;
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
    ImageView shopCircleImage;
    ImageView reviewerCircleImage;

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

    String shopName;
    Float shopTotalRating;
    String shopLocation;
    LatLng shopLatLng;
    String aboutShop;
    Boolean FAVORITE_TAG;

    int currentPage = 1;
    static int indicatorCount = 0;
    static int mShopId = -1;

    static final String[] shopActivityTags = new String[] {
            "1", "0", "0", "0",
            "1", "0", "1", "0",
            "1", "0", "0", "0"
    };
    static final String[] serviceValues = new String[] {
            "0", "1", "0",
            "0", "0", "0"
    };
    static final String[] prepareValues = new String[] {
            "0", "0", "1", "0",
    };

    private Animation slideInAnimation;

    public ShopInfoFragment() {}
    public static ShopInfoFragment newInstance(int shopId) {
        ShopInfoFragment shopInfoFragment = new ShopInfoFragment();
        mShopId = shopId;

        return shopInfoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        owner = (ShopDetailActivity)getActivity();

        view                = inflater.inflate(R.layout.include_shop_info_section1, container, false);
        circleAnimIndicator = (CircleAnimIndicator) view.findViewById(R.id.shop_info_flipper_indicator);
        mapView             = (MapView) view.findViewById(R.id.map_on_shop_info);

        viewFlipper         = (ViewFlipper) view.findViewById(R.id.shop_info_image_flipper);
        interestGridView    = (GridView) view.findViewById(R.id.shop_info_grid_view1);
        serviceGridView     = (GridView) view.findViewById(R.id.shop_info_grid_view2);
        prepareGridView     = (GridView) view.findViewById(R.id.shop_info_grid_view3);
        shopCircleImage     = (ImageView) view.findViewById(R.id.shop_detail_circle_image);
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
        reviewerCircleImage = (ImageView) view.findViewById(R.id.reviewer_circle_image);

        mapView.onCreate(savedInstanceState);


        interestGridView.setAdapter(new CategoryGridAdapter(getContext(), shopActivityTags));
        serviceGridView.setAdapter(new ServiceGridAdapter(getContext(), serviceValues));
        prepareGridView.setAdapter(new PrepareGridAdapter(getContext(), prepareValues));

        shopImage1.setImageResource(R.drawable.girls_generation_all);
        shopImage2.setImageResource(R.drawable.girls_generation_tifany);
        shopImage3.setImageResource(R.drawable.girls_generation_all);
        shopImage4.setImageResource(R.drawable.girls_generation_tifany);
        shopCircleImage.setImageResource(R.drawable.girls_generation_tifany);
        reviewerCircleImage.setImageResource(R.drawable.girls_generation_tifany);
        emailContactBtn.setImageResource(R.drawable.ic_email);
        callingBtn.setImageResource(R.drawable.ic_call);
        transportIcon.setImageResource(TransportList.getSubwayResource().get(3));

        slideInAnimation = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        shopCircleImage.startAnimation(slideInAnimation);
        reviewerCircleImage.startAnimation(slideInAnimation);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new AsyncShopInfoJSONList().execute();
        initVariableIndicator(indicatorCount);
        mapView.getMapAsync(this);

        callingBtn.setOnClickListener(this);
        emailContactBtn.setOnClickListener(this);
        previousImageBtn.setOnClickListener(this);
        nextImageBtn.setOnClickListener(this);
        reviewMoreBtn.setOnClickListener(this);
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
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-2056-7973"));
                startActivity(intent);
                break;
            case R.id.email_btn:
                intent = new Intent(getActivity(), WriteReviewActivity.class);
                startActivity(intent);
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
                intent = new Intent(getActivity(), ReviewActivity.class);
                startActivity(intent);
                break;
        }
    }

    public static class AsyncShopInfoJSONList
            extends AsyncTask<String, Integer, LKShopInfoObject> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(owner, "", "Loading...", true);
        }

        @Override
        protected LKShopInfoObject doInBackground(String... params) {
            return OkHttpAPIHelperHandler.shopInfoJSONALLSelect(mShopId);
        }
        @Override
        protected void onPostExecute(LKShopInfoObject result) {
            dialog.dismiss();
            if (result != null) {
                shopInfoObject = new LKShopInfoObject();
                shopInfoObject = result;
                Log.i("shopInfoObject1", ""+shopInfoObject.shopImages.size());
                indicatorCount = shopInfoObject.shopImages.size();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        /*shopLatLng = new LatLng(shopInfoObject.latitude, shopInfoObject.longitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(shopLatLng);
        markerOptions.title(shopInfoObject.address2 + ", " + shopInfoObject.address1);
        markerOptions.snippet(shopInfoObject.address3);

        Marker marker = gMap.addMarker(markerOptions);
        marker.showInfoWindow();

        int zoom = (int) gMap.getCameraPosition().zoom;
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(shopLatLng, zoom), 2000, null);*/
    }
}
