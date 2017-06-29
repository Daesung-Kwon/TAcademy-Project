package com.leisurekr.leisuresportskorea.shop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopListObject;

/**
 * Created by mobile on 2017. 5. 29..
 */

public class MapCustomFragment extends Fragment {
    public ImageView mShopMainImage;
    public TextView mShopName;
    public TextView mShopLocation;
    public TextView mShopRating;
    public ImageView mLikes;
    public LinearLayout mFocus;

    public MapCustomFragment() { }
    public static MapCustomFragment newInstance(LKShopListObject data) {
        MapCustomFragment fragment2 = new MapCustomFragment();

        // ViewPager 페이지 단위별 객체 전달 Bundle setArguments
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", data);
        fragment2.setArguments(bundle);

        return fragment2;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment_item, container, false);

        LKShopListObject obj = getArguments().getParcelable("data");

        mShopMainImage = (ImageView) view.findViewById(R.id.map_shop_main_image);
        mShopName = (TextView) view.findViewById(R.id.map_shop_name_text1);
        mShopLocation = (TextView) view.findViewById(R.id.map_shop_location_text);
        mShopRating = (TextView) view.findViewById(R.id.map_shop_rating_text);
        mLikes = (ImageView) view.findViewById(R.id.likes_on_map_activity);
        mFocus = (LinearLayout) view.findViewById(R.id.focus_color);

        Glide.with(LKApplication.getLKApplication())
                .load(obj.shopImages)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //.override(360, 280)
                .into(mShopMainImage);
        mShopName.setText(obj.shopName);
        mShopLocation.setText(obj.shopAddress1);
        mShopRating.setText(Double.toString(obj.score));
        if (obj.likes == true) {
            mLikes.setImageResource(R.drawable.btn_heart_press);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
