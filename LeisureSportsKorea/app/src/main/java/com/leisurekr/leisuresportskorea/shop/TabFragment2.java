package com.leisurekr.leisuresportskorea.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.MainActivity;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.shop_detail.ShopDetailActivity;

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
            public final LinearLayout mShopMainImage;
            public final LinearLayout dim;
            //public final ImageView mShopMainImage;
            public final ImageView mShopCircleImage;
            public final TextView mShopName;
            public final TextView mShopLocation;
            public final TextView mShopRating;
            public final TextView mShopPrice;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mShopMainImage = (LinearLayout) view.findViewById(R.id.shop_main_image);
                dim = (LinearLayout)view.findViewById(R.id.shop_dim);
                //mShopMainImage = (ImageView) view.findViewById(R.id.shop_main_image);
                mShopCircleImage = (ImageView) view.findViewById(R.id.shop_circle_image);
                mShopName = (TextView) view.findViewById(R.id.shop_name_text);
                mShopLocation = (TextView) view.findViewById(R.id.shop_location_text);
                mShopRating = (TextView) view.findViewById(R.id.shop_rating_text);
                mShopPrice = (TextView) view.findViewById(R.id.shop_price_text);
            }
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.tab_fragment_2_item, parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(final TabFragment2RVAdapter.ViewHolder holder, int position) {
            Integer shopCircleImageInfo = shopImages.get(position); // circle image;
            holder.mShopName.setText("Costa leisure sport");
            holder.mShopLocation.setText("Han River");
            holder.mShopRating.setText("4.8");
            holder.mShopPrice.setText("$54");

            holder.mShopMainImage.setBackgroundResource(R.drawable.pic_shop);
            holder.dim.setAlpha(0.3f);

            holder.mShopMainImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
                    owner.startActivity(intent);
                }
            });

            holder.mShopCircleImage.setImageResource(R.drawable.pic_shop1);
            holder.mShopCircleImage.startAnimation(slideInAnimation);
        }

        @Override
        public int getItemCount() {
            return shopImages.size();
        }
    }
}
