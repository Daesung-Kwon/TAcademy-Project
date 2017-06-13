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

public class ShopInfoFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMarkerDragListener {

    static ShopDetailActivity owner;
    static LKShopInfoObject shopInfoObject;
    View view;

    ViewFlipper viewFlipper;
    GridView interestGridView;
    GridView serviceGridView;
    GridView prepareGridView;

    ImageView shareImageBtn;
    ImageView myFavoriteBtn;
    ImageView transportIcon;

    Button previousImageBtn;
    Button nextImageBtn;
    TextView reviewMoreBtn;

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
    static int mShopId = -1;

    static final String[] interestValues = new String[] {
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
        shopInfoObject = new LKShopInfoObject();

        return shopInfoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        owner = (ShopDetailActivity)getActivity();
        view = inflater.inflate(R.layout.include_shop_info_section1, container, false);
        new AsyncShopInfoJSONList().execute();

        circleAnimIndicator = (CircleAnimIndicator) view.findViewById(R.id.shop_info_flipper_indicator);

        mapView = (MapView) view.findViewById(R.id.map_on_shop_info);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        interestGridView = (GridView) view.findViewById(R.id.shop_info_grid_view1);
        interestGridView.setAdapter(new CategoryGridAdapter(getContext(), interestValues));
        serviceGridView = (GridView) view.findViewById(R.id.shop_info_grid_view2);
        serviceGridView.setAdapter(new ServiceGridAdapter(getContext(), serviceValues));
        prepareGridView = (GridView) view.findViewById(R.id.shop_info_grid_view3);
        prepareGridView.setAdapter(new PrepareGridAdapter(getContext(), prepareValues));

        ImageView shopCircleImage = (ImageView) view.findViewById(R.id.shop_detail_circle_image);
        shopCircleImage.setImageResource(R.drawable.girls_generation_tifany);
        ImageView reviewerCircleImage = (ImageView) view.findViewById(R.id.reviewer_circle_image);
        reviewerCircleImage.setImageResource(R.drawable.girls_generation_tifany);
        ImageView emailContactBtn = (ImageView) view.findViewById(R.id.email_btn);
        emailContactBtn.setImageResource(R.drawable.ic_email);
        emailContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteReviewActivity.class);
                startActivity(intent);
            }
        });
        ImageView callingBtn = (ImageView) view.findViewById(R.id.call_btn);
        callingBtn.setImageResource(R.drawable.ic_call);
        callingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-2056-7973"));
                startActivity(intent);
            }
        });

        transportIcon = (ImageView) view.findViewById(R.id.transport_icon);
        transportIcon.setImageResource(TransportList.getSubwayResource().get(3));

        slideInAnimation = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        shopCircleImage.startAnimation(slideInAnimation);
        reviewerCircleImage.startAnimation(slideInAnimation);

        viewFlipper = (ViewFlipper) view.findViewById(R.id.shop_info_image_flipper);
        ImageView imageView1 = (ImageView) view.findViewById(R.id.shop_image_flipper1);
        imageView1.setImageResource(R.drawable.girls_generation_all);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.shop_image_flipper2);
        imageView2.setImageResource(R.drawable.girls_generation_tifany);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.shop_image_flipper3);
        imageView3.setImageResource(R.drawable.girls_generation_all);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.shop_image_flipper4);
        imageView4.setImageResource(R.drawable.girls_generation_tifany);

        previousImageBtn = (Button) view.findViewById(R.id.shop_info_prevbtn);
        nextImageBtn = (Button) view.findViewById(R.id.shop_info_nextbtn);
        reviewMoreBtn = (TextView) view.findViewById(R.id.review_more_btn);

        initIndicator();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        previousImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPage > 1 && currentPage < 5) {
                    viewFlipper.showPrevious();
                    currentPage--;
                    circleAnimIndicator.selectDot(currentPage-1);
                }
            }
        });

        nextImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPage > 0 && currentPage < 4) {
                    viewFlipper.showNext();
                    currentPage++;
                    circleAnimIndicator.selectDot(currentPage-1);
                }
            }
        });

        reviewMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reviewIntent = new Intent(getActivity(), ReviewActivity.class);
                startActivity(reviewIntent);
            }
        });
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        shopLatLng = new LatLng(shopInfoObject.latitude, shopInfoObject.longitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(shopLatLng);
        markerOptions.title(shopInfoObject.address2 + ", " + shopInfoObject.address1);
        markerOptions.snippet(shopInfoObject.address3);

        Marker marker = gMap.addMarker(markerOptions);
        marker.showInfoWindow();

        int zoom = (int) gMap.getCameraPosition().zoom;
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(shopLatLng, zoom), 2000, null);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }
    @Override
    public void onMarkerDrag(Marker marker) {

    }
    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    private void initIndicator() {
        circleAnimIndicator.setItemMargin(15);
        circleAnimIndicator.setAnimDuration(300);
        circleAnimIndicator.createDotPanel(4, R.drawable.icon_navi_unpress, R.drawable.icon_navi_press);
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
                shopInfoObject = result;
            }
        }
    }
}
