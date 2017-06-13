package com.leisurekr.leisuresportskorea.shop;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.leisurekr.leisuresportskorea.R;

/**
 * Created by mobile on 2017. 6. 12..
 */

public class MapActivity2 extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerDragListener {
    private ViewPager viewPager;
    MapView mapView;
    GoogleMap gMap;
    ImageView cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        MapsInitializer.initialize(this);

        mapView = (MapView) findViewById(R.id.map2);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        /*mMapFragment = MapFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map2, mMapFragment);
        fragmentTransaction.commit();

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);*/

        // View Pager
        viewPager = (ViewPager) findViewById(R.id.map_viewpager2);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.home_activity_imageMarginRight));
        setupViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        cancelBtn = (ImageView) findViewById(R.id.cancel_btn_on_map);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void setupViewPager(ViewPager viewPager) {
        MapPagerAdapter2 mapPagerAdapter = new MapPagerAdapter2(getSupportFragmentManager());

        int count = 5;
        for (int i = 0; i < count; i++) {
            mapPagerAdapter.addFragment(MapCustomFragment2.newInstance());
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

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        gMap.addMarker(markerOptions);

        gMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        gMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }
}
