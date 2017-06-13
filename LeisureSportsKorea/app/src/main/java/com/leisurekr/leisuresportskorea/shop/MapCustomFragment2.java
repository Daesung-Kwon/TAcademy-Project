package com.leisurekr.leisuresportskorea.shop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.List;

/**
 * Created by mobile on 2017. 5. 29..
 */

public class MapCustomFragment2 extends Fragment {
    static ShopInfoOnMapObject entity = new ShopInfoOnMapObject();

    public ImageView mShopMainImage;
    public TextView mShopName;
    public TextView mShopLocation;
    public TextView mShopRating;
    public LinearLayout mFocus;

    public MapCustomFragment2() { }
    public static MapCustomFragment2 newInstance() {
        MapCustomFragment2 fragment2 = new MapCustomFragment2();

        entity.shopName = "Hangan Seve Watersprots Comp";
        entity.shopLocation = "Han river";
        entity.shopRating = (double) 4.8;

        return fragment2;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment_item, container, false);

        mShopMainImage = (ImageView) view.findViewById(R.id.map_shop_main_image);
        mShopName = (TextView) view.findViewById(R.id.map_shop_name_text1);
        mShopLocation = (TextView) view.findViewById(R.id.map_shop_location_text);
        mShopRating = (TextView) view.findViewById(R.id.map_shop_rating_text);
        mFocus = (LinearLayout) view.findViewById(R.id.focus_color);

        mShopName.setText(entity.shopName);
        mShopLocation.setText(entity.shopLocation);
        mShopRating.setText(Double.toString(entity.shopRating));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
