package com.leisurekr.leisuresportskorea.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopListObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mobile on 2017. 6. 12..
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, ViewPager.OnPageChangeListener, View.OnClickListener {
    ArrayList<LKShopListObject> shopListObjects;
    private ViewPager viewPager;
    MapView mapView;
    GoogleMap gMap;
    Marker currentMarker;
    CameraUpdate cameraUpdate;
    ImageView cancelBtn;
    int viewPagerSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        // Fragment2 -> MainActivity -> MapActivity 로 넘겨진 Shop List 객체 가져오기
        Intent intent = getIntent();
        shopListObjects = intent.getParcelableArrayListExtra("shopInfoList");

        MapsInitializer.initialize(this);

        mapView = (MapView) findViewById(R.id.map2);
        viewPager = (ViewPager) findViewById(R.id.map_viewpager2);
        cancelBtn = (ImageView) findViewById(R.id.cancel_btn_on_map);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.home_activity_imageMarginRight));
        setupViewPager(viewPager);

        viewPager.addOnPageChangeListener(this);
        cancelBtn.setOnClickListener(this);
    }
    private void setupViewPager(ViewPager viewPager) {
        MapPagerAdapter mapPagerAdapter = new MapPagerAdapter(getSupportFragmentManager());

        viewPagerSize = shopListObjects.size();
        for (int i = 0; i < viewPagerSize; i++) {
            mapPagerAdapter.addFragment(MapCustomFragment.newInstance(shopListObjects.get(i)));
        }
        viewPager.setAdapter(mapPagerAdapter);
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

    Map<Integer, Marker> markersMap = new HashMap<>();
    Map<Marker, Integer> viewPagerMap = new HashMap<>();
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        for (int i = 0; i < viewPagerSize; i++) {
            LatLng shopLatLng = new LatLng(shopListObjects.get(i).latitude,
                                           shopListObjects.get(i).longitude);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(shopLatLng);
            //markerOptions.anchor(0.5f, 1);
            markerOptions.title(shopListObjects.get(i).shopAddress1);
            markerOptions.snippet(shopListObjects.get(i).shopAddress2);

            currentMarker = gMap.addMarker(markerOptions);
            markersMap.put(i, currentMarker);
            viewPagerMap.put(currentMarker, i);

            cameraUpdate = CameraUpdateFactory.newLatLngZoom(shopLatLng, 8);
            if (i == 0) {
                gMap.animateCamera(cameraUpdate, new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() { currentMarker.showInfoWindow(); }
                    @Override
                    public void onCancel() { }
                });
            }

            gMap.setOnMarkerClickListener(this);
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        final Marker mMarker = marker;

        viewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(viewPagerMap.get(mMarker), true);
            }
        }, 2000);
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentMarker = markersMap.get(position);
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentMarker.getPosition(), 15);
        gMap.animateCamera(cameraUpdate, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() { currentMarker.showInfoWindow(); }
            @Override
            public void onCancel() { }
        });
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_btn_on_map:
                onBackPressed();
                break;
        }
    }
}
