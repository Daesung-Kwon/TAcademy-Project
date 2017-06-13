package com.leisurekr.leisuresportskorea.shop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.MainActivity;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopInfoObject;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopListObject;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopProgramEntity;
import com.leisurekr.leisuresportskorea.shop_detail.ShopDetailActivity;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 5. 11..
 */

public class TabFragment2 extends android.support.v4.app.Fragment {
    static MainActivity owner;
    RecyclerView rv;
    private static TabFragment2RVAdapter rvAdapter;
    static TextView resultsCountTextView;
    static TextView filterTag1;
    static TextView filterTag2;
    static TextView filterTag3;
    static TextView filterTag4;
    static ArrayList<String> tagList = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_2, container, false);
        owner = (MainActivity)getActivity();

        resultsCountTextView = (TextView) view.findViewById(R.id.result_count_text_tab2);
        filterTag1 = (TextView) view.findViewById(R.id.selected_filter_text1);
        filterTag2 = (TextView) view.findViewById(R.id.selected_filter_text2);
        filterTag3 = (TextView) view.findViewById(R.id.selected_filter_text3);
        filterTag4 = (TextView) view.findViewById(R.id.selected_filter_text4);

        rv = (RecyclerView) view.findViewById(R.id.recyclerview);
        rvAdapter = new TabFragment2RVAdapter(new ArrayList<LKShopListObject>());
        rv.setLayoutManager(new LinearLayoutManager(LKApplication.getLKApplication()));
        rv.setAdapter(rvAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new AsyncShopInfoJSONList().execute();
    }

    public static class TabFragment2RVAdapter
            extends RecyclerView.Adapter<TabFragment2RVAdapter.ViewHolder> {

        private ArrayList<LKShopListObject> mResult;

        private Animation slideInAnimation;
        public TabFragment2RVAdapter(ArrayList<LKShopListObject> resources) {
            mResult = resources;
            slideInAnimation = AnimationUtils.loadAnimation(owner, android.R.anim.slide_in_left);
        }
        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final LinearLayout dim;

            public final ImageView mShopMainImage;
            public final TextView mFilterTag;
            public final ImageView mShopCircleImage;
            public final TextView mShopName;
            public final TextView mShopLocation;
            public final TextView mShopRating;
            public final TextView mShopPrice;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                dim = (LinearLayout)view.findViewById(R.id.shop_dim);

                mFilterTag = (TextView) view.findViewById(R.id.filtered_text_in_shop);
                mShopMainImage = (ImageView) view.findViewById(R.id.shop_main_image);
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
            int p = position;
            holder.dim.setAlpha(0.9f);

            LKShopListObject shopInfo = mResult.get(p);
            holder.mFilterTag.setText("#" + shopInfo.activityName);
            holder.mShopName.setText(shopInfo.shopName);
            holder.mShopLocation.setText(shopInfo.shopAddress2 + " " + shopInfo.shopAddress1);
            holder.mShopPrice.setText("$" + shopInfo.price);
            holder.mShopRating.setText(String.valueOf(shopInfo.score));
            Glide.with(LKApplication.getLKApplication())
                    .load(shopInfo.shopImages)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .animate(android.R.anim.slide_in_left)
                    //.override(360, 280)
                    .into(holder.mShopMainImage);
            holder.mShopMainImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
                    intent.putExtra("shopId", 2); // shopInfo.shopId
                    owner.startActivity(intent);
                }
            });

            Glide.with(LKApplication.getLKApplication())
                    .load(shopInfo.shopIcon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .animate(android.R.anim.slide_in_left)
                    .override(40, 40)
                    .into(holder.mShopCircleImage);
        }

        @Override
        public int getItemCount() {
            return mResult.size();
        }

        public void addAll(ArrayList<LKShopListObject> objects) {
            this.mResult.addAll(objects);
        }
    }

    public static class AsyncShopInfoJSONList
            extends AsyncTask<String, Integer, ArrayList<LKShopListObject>> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(owner, "", "Loading...", true);
        }

        @Override
        protected ArrayList<LKShopListObject> doInBackground(String... params) {
            return OkHttpAPIHelperHandler.shopListJSONAllSelect();
        }
        @Override
        protected void onPostExecute(ArrayList<LKShopListObject> result) {
            dialog.dismiss();
            if (result != null && result.size() > 0) {
                rvAdapter.addAll(result);
                rvAdapter.notifyDataSetChanged();

                resultsCountTextView.setText(result.size() + " Results");
                tagList = new ArrayList<>(); tagList.clear();
                for (int i = 0; i < result.size(); i++) {
                    String tagName = result.get(i).activityName;
                    if (tagList.isEmpty()) {
                        tagList.add(tagName);
                    } else {
                        if (!tagList.contains(tagName)) {
                            tagList.add(tagName);
                        }
                    }
                }
                switch (tagList.size()) {
                    case 1:
                        setResultTagOne(tagList);
                        break;
                    case 2:
                        setResultTagTwo(tagList);
                        break;
                    case 3:
                        setResultTagThree(tagList);
                        break;
                    case 4:
                        setResultTagFour(tagList);
                        break;
                }
            }else {
                resultsCountTextView.setText("There is No Results");
            }
        }
    }

    public static void setResultTagOne(ArrayList<String> tagResult) {
        filterTag1.setText("#"+tagResult.get(0));
    }
    public static void setResultTagTwo(ArrayList<String> tagResult) {
        filterTag1.setText("#"+tagResult.get(0));
        filterTag2.setText("#"+tagResult.get(1));
    }
    public static void setResultTagThree(ArrayList<String> tagResult) {
        filterTag1.setText("#"+tagResult.get(0));
        filterTag2.setText("#"+tagResult.get(1));
        filterTag3.setText("#"+tagResult.get(2));
    }
    public static void setResultTagFour(ArrayList<String> tagResult) {
        filterTag1.setText("#"+tagResult.get(0));
        filterTag2.setText("#"+tagResult.get(1));
        filterTag3.setText("#"+tagResult.get(2));
        filterTag4.setText("#"+tagResult.get(3));
    }
}
