package com.leisurekr.leisuresportskorea.shop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.R;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 5. 29..
 */

public class MapCustomFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMarkerDragListener, StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener {

    int btnFlag = 0;
    FrameLayout frameLayout;

    MapView mapView;
    GoogleMap gMap;

    @Override
    public void onDetach() { super.onDetach(); }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.map_fragment, container, false);
        MapsInitializer.initialize(this.getActivity());

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.map_recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(
                LKApplication.getLKApplication(), LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(new MapCustomFragmentRVAdapter(TestArrayList.getArrayList())); // Test...

        //Button btn = (Button) view.findViewById(R.id.btn);
        //frameLayout = (FrameLayout) view.findViewById(R.id.frame_roadview);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        /*frameLayout.setVisibility(View.INVISIBLE);
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
        });*/

        return view;
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

    public static class MapCustomFragmentRVAdapter
            extends RecyclerView.Adapter<MapCustomFragment.MapCustomFragmentRVAdapter.ViewHolder> {

        private ArrayList<Integer> shopImages;

        public MapCustomFragmentRVAdapter(ArrayList<Integer> resources) {
            shopImages = resources;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final ImageView mShopMainImage;
            public final TextView mShopName;
            public final TextView mShopLocation;
            public final TextView mShopRating;
            public final TextView mFocus;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mShopMainImage = (ImageView) view.findViewById(R.id.map_shop_main_image);
                mShopName = (TextView) view.findViewById(R.id.map_shop_name_text);
                mShopLocation = (TextView) view.findViewById(R.id.map_shop_location_text);
                mShopRating = (TextView) view.findViewById(R.id.map_shop_rating_text);
                mFocus = (TextView) view.findViewById(R.id.focus);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.map_fragment_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Integer shopImageInfo = shopImages.get(position); // main image;
            holder.mShopName.setText("LK Shop");
            holder.mShopLocation.setText("Seoul hangang-ro 1234-5");
            holder.mShopRating.setText("4.0");
            holder.mShopMainImage.setImageResource(R.drawable.exo_all);//.(shopImageInfo.intValue())

            if (position == 0) {
                holder.mFocus.setBackgroundColor(R.color.colorAccent);
            }
        }

        @Override
        public int getItemCount() {
            return shopImages.size();
        }
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
