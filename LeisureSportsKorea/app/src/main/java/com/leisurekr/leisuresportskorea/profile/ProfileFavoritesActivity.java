package com.leisurekr.leisuresportskorea.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.BookActivity;
import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.TestArrayList;

import java.util.ArrayList;

public class ProfileFavoritesActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_favorites_activity);

        toolbar = (Toolbar) findViewById(R.id.profile_favor_toolbar);
        toolbar.setTitle("Favorites");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView rv = (RecyclerView) findViewById(R.id.profile_favor_recyclerview);

        rv.setLayoutManager(new LinearLayoutManager(LKApplication.getLKApplication()));
        rv.setAdapter(new TabFragment2RVAdapter(TestArrayList.getArrayList()));
    }

    public class TabFragment2RVAdapter
            extends RecyclerView.Adapter<TabFragment2RVAdapter.ViewHolder> {

        private ArrayList<Integer> shopImages;

        private Animation slideInAnimation;
        public TabFragment2RVAdapter(ArrayList<Integer> resources) {
            shopImages = resources;
            slideInAnimation = AnimationUtils
                    .loadAnimation(ProfileFavoritesActivity.this, android.R.anim.slide_in_left);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final LinearLayout mShopMainImage;
            public final LinearLayout dim;
            //public final ImageView mShopMainImage;
            public final ImageView mShopCircleImage;
            public final TextView mShopName;
            public final TextView mShopLocation;
            public final TextView mShopRating;

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

            holder.mShopMainImage.setBackgroundResource(R.drawable.exo_all);
            holder.dim.setAlpha(0.3f);
            //holder.mShopMainImage.setImageResource(R.drawable.exo_all);
            holder.mShopMainImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),BookActivity.class);
                    startActivity(intent);
                }
            });
            holder.mShopCircleImage.setImageResource(shopCircleImageInfo.intValue());
            holder.mShopCircleImage.startAnimation(slideInAnimation);
        }

        @Override
        public int getItemCount() {
            return shopImages.size();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
