package com.leisurekr.leisuresportskorea;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 5. 11..
 */

public class TabFragment2 extends android.support.v4.app.Fragment {
    static MainActivity owner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_fragment_2, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerview);

        owner = (MainActivity)getActivity();
        rv.setLayoutManager(new LinearLayoutManager(LKApplication.getLKApplication()));
        rv.setAdapter(new TabFragment2RVAdapter(TestArrayList.getArrayList())); // Test...

        return view;
    }

    public static class TabFragment2RVAdapter
            extends RecyclerView.Adapter<TabFragment2RVAdapter.ViewHolder> {

        private ArrayList<Integer> shopImages;

        private Animation slideInAnimation;
        public TabFragment2RVAdapter(ArrayList<Integer> resources) {
            shopImages = resources;
            slideInAnimation = AnimationUtils.loadAnimation(owner, android.R.anim.slide_in_left);
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final ImageView mShopMainImage;
            public final ImageView mShopCircleImage;
            public final TextView mShopName;
            public final TextView mShopLocation;
            public final TextView mShopRating;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mShopMainImage = (ImageView) view.findViewById(R.id.shop_main_image);
                mShopCircleImage = (ImageView) view.findViewById(R.id.shop_circle_image);
                mShopName = (TextView) view.findViewById(R.id.shop_name_text);
                mShopLocation = (TextView) view.findViewById(R.id.shop_location_text);
                mShopRating = (TextView) view.findViewById(R.id.shop_rating_text);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.tab_fragment_2_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TabFragment2RVAdapter.ViewHolder holder, int position) {
            Integer shopImageInfo = shopImages.get(position); // main image;
            Integer shopCircleImageInfo = shopImages.get(position); // circle image;
            holder.mShopName.setText("LK Shop");
            holder.mShopLocation.setText("Seoul hangang-ro 1234-5");
            holder.mShopRating.setText("4.0");

            holder.mShopMainImage.setImageResource(R.drawable.exo_all);
            holder.mShopCircleImage.setImageResource(shopCircleImageInfo.intValue());
            holder.mShopCircleImage.startAnimation(slideInAnimation);
        }

        @Override
        public int getItemCount() {
            return shopImages.size();
        }
    }
}
