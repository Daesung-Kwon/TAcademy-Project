package com.leisurekr.leisuresportskorea;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/**
 * Created by mobile on 2017. 5. 26..
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerDragListener, StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener{

    ActionBar actionBar;
    Toolbar toolbar;
    FrameLayout frameLayout;
    int btnFlag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        toolbar = (Toolbar) findViewById(R.id.map_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Shop Location on Map");

        /*actionBar = (ActionBar) findViewById(R.id.appbar);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Shop Info. On Map");*/

        Button btn = (Button) findViewById(R.id.btn);
        frameLayout = (FrameLayout) findViewById(R.id.frame_roadview);
        FragmentManager fm = getFragmentManager();
        MapFragment mFM = (MapFragment) fm.findFragmentById(R.id.map);
        mFM.getMapAsync(this);

        frameLayout.setVisibility(View.INVISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnFlag == 0) {
                    frameLayout.setVisibility(View.VISIBLE);
                    btnFlag = 0;
                }else {
                    frameLayout.setVisibility(View.INVISIBLE);
                    btnFlag = 1;
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
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
    public void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera streetViewPanoramaCamera) {

    }
}
