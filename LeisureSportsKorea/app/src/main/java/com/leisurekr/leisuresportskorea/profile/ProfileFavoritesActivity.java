package com.leisurekr.leisuresportskorea.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leisurekr.leisuresportskorea.FavorObject;
import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.shop_detail.ShopDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ProfileFavoritesActivity extends AppCompatActivity {

    Toolbar toolbar;
    FavorAdapter rvAdapter;
    static ArrayList<String> tagList = null;

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
        rvAdapter = new FavorAdapter(new ArrayList<FavoritesObject>());
        rv.setAdapter(rvAdapter);
    }

    public class FavorAdapter
            extends RecyclerView.Adapter<FavorAdapter.ViewHolder> {

        private ArrayList<FavoritesObject> mResult;

        public FavorAdapter(ArrayList<FavoritesObject> resources) {
            mResult = resources;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final LinearLayout dim;

            public final ImageView mShopMainImage;
            public final TextView mFilterTag;
            public final TextView mFilterTag1;
            public final ImageView mShopCircleImage;
            public final TextView mShopName;
            public final TextView mShopLocation;
            public final TextView mShopRating;
            public final TextView mShopPrice;
            public final ImageView mLikes;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                dim = (LinearLayout) view.findViewById(R.id.shop_dim);

                mFilterTag = (TextView) view.findViewById(R.id.filtered_text_in_shop);
                mFilterTag1 = (TextView) view.findViewById(R.id.filtered_text_in_shop1);
                mShopMainImage = (ImageView) view.findViewById(R.id.shop_main_image);
                mShopCircleImage = (ImageView) view.findViewById(R.id.shop_circle_image);
                mShopName = (TextView) view.findViewById(R.id.shop_name_text);
                mShopLocation = (TextView) view.findViewById(R.id.shop_location_text);
                mShopRating = (TextView) view.findViewById(R.id.shop_rating_text);
                mShopPrice = (TextView) view.findViewById(R.id.shop_price_text);
                mLikes = (ImageView) view.findViewById(R.id.favorite_item_icon_in_shop);

            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.tab_fragment_2_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final FavorAdapter.ViewHolder holder, int position) {
            int p = position;
            holder.dim.setAlpha(0.9f);

            final FavoritesObject shopInfo = mResult.get(p);

            String interests=" ";
            String interests1=" ";
            int count = 0;
            HashMap<String, Boolean> tags = shopInfo.getActivities();
            Set<String> keys = tags.keySet();
            Iterator<String> iterator = keys.iterator();
            while(iterator.hasNext()){
                String s = iterator.next();
                if(tags.get(s)){
                    if(count<2)
                        interests = interests +"# "+ s+" ";
                    else
                        interests1 = interests1+"# "+ s+" ";
                    count++;
                }
            }
            holder.mFilterTag.setText(interests);
            holder.mFilterTag1.setText(interests1);

            holder.mShopName.setText(shopInfo.getShopName());
            holder.mShopLocation.setText(shopInfo.getAddress2() + " " + shopInfo.getAddress1());
            holder.mShopPrice.setText("$" + shopInfo.getPrice());
            holder.mShopRating.setText(String.valueOf(shopInfo.getScore()));
            Glide.with(LKApplication.getLKApplication())
                    .load(shopInfo.getShopImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    //.override(360, 280)
                    .into(holder.mShopMainImage);
            holder.mShopMainImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
                    intent.putExtra("shopId", shopInfo.getShopId());
                    startActivity(intent);
                }
            });

            Glide.with(LKApplication.getLKApplication())
                    .load(shopInfo.getLogoImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(40, 40)
                    .into(holder.mShopCircleImage);
            if (shopInfo.getLikes()) {
                holder.mLikes.setImageResource(R.drawable.btn_heart_press);
                holder.mLikes.setSelected(true);
            } else {
                holder.mLikes.setImageResource(R.drawable.btn_heart_unpress);
                holder.mLikes.setSelected(false);
            }
            holder.mLikes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("heart in home", "click");
                    final FavorObject favorObject = new FavorObject();
                    favorObject.setShopId(shopInfo.getShopId());
                    favorObject.setUserId(1);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final String result = OkHttpAPIHelperHandler.favorJSONInsert(favorObject);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("heart", result);
                                    if (result.equals("success")) {
                                        if (holder.mLikes.isSelected()) {
                                            holder.mLikes.setImageResource(R.drawable.btn_heart_unpress);
                                            holder.mLikes.setSelected(false);
                                        } else {
                                            holder.mLikes.setImageResource(R.drawable.btn_heart_press);
                                            holder.mLikes.setSelected(true);
                                        }
                                    }
                                }
                            });
                        }
                    }).start();

                }
            });
        }

        @Override
        public int getItemCount() {
            return mResult.size();
        }

        public void addAll(ArrayList<FavoritesObject> objects) {
            this.mResult.addAll(objects);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        new FavorJSONList().execute();
    }

    public class FavorJSONList
            extends AsyncTask<String, Integer, ArrayList<FavoritesObject>> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(ProfileFavoritesActivity.this, "", "Loading...", true);
        }

        @Override
        protected ArrayList<FavoritesObject> doInBackground(String... params) {
            return OkHttpAPIHelperHandler.favorListJSONAllSelect();
        }

        @Override
        protected void onPostExecute(ArrayList<FavoritesObject> result) {
            dialog.dismiss();
            if (result != null && result.size() > 0) {
                // MainActivity.class로 Object 전달
                // Adapter result 값 Add
                rvAdapter.addAll(result);
                rvAdapter.notifyDataSetChanged();
            }
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
