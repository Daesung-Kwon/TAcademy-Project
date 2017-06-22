package com.leisurekr.leisuresportskorea.shop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leisurekr.leisuresportskorea.FavorObject;
import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.MainActivity;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.home.TabFragment1;
import com.leisurekr.leisuresportskorea.interfaces.ShopListSetListener;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopListObject;
import com.leisurekr.leisuresportskorea.shop_detail.ShopDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mobile on 2017. 5. 11..
 */

public class TabFragment2 extends android.support.v4.app.Fragment {
    public static TabFragment2 tabFragment2;
    private ShopListSetListener shopListSetListener;

    static MainActivity owner;
    RecyclerView rv;
    public static TabFragment2RVAdapter rvAdapter;
    TextView resultsCountTextView;
    TextView filterTag1;
    TextView filterTag2;
    TextView filterTag3;
    TextView filterTag4;
    ArrayList<String> tagList = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("TabFragment2","onAttach()");
        if (context instanceof ShopListSetListener) {
            shopListSetListener = (ShopListSetListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ShopListSetListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_2, container, false);
        Log.e("TabFragment2", "onCreateView()");
        tabFragment2 = this;
        owner = (MainActivity) getActivity();

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

    @Override
    public void onDetach() {
        super.onDetach();
        shopListSetListener = null;
    }

    public class TabFragment2RVAdapter
            extends RecyclerView.Adapter<TabFragment2RVAdapter.ViewHolder> {

        private ArrayList<LKShopListObject> mResult;

        public TabFragment2RVAdapter(ArrayList<LKShopListObject> resources) {
            mResult = resources;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public View mView;
            public LinearLayout dim;

            public ImageView mShopMainImage;
            public TextView mFilterTag;
            public ImageView mShopCircleImage;
            public TextView mShopName;
            public TextView mShopLocation;
            public TextView mShopRating;
            public TextView mShopPrice;
            public ImageView mLikes;
            public ImageView mShare;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                dim = (LinearLayout) view.findViewById(R.id.shop_dim);
                mFilterTag = (TextView) view.findViewById(R.id.filtered_text_in_shop);
                mShopMainImage = (ImageView) view.findViewById(R.id.shop_main_image);
                mShopCircleImage = (ImageView) view.findViewById(R.id.shop_circle_image);
                mShopName = (TextView) view.findViewById(R.id.shop_name_text);
                mShopLocation = (TextView) view.findViewById(R.id.shop_location_text);
                mShopRating = (TextView) view.findViewById(R.id.shop_rating_text);
                mShopPrice = (TextView) view.findViewById(R.id.shop_price_text);
                mLikes = (ImageView) view.findViewById(R.id.favorite_item_icon_in_shop);
                mShare = (ImageView) view.findViewById(R.id.share_item_icon_in_shop);

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

            final LKShopListObject shopInfo = mResult.get(p);
            holder.mFilterTag.setText("#" + shopInfo.activityName);
            holder.mShopName.setText(shopInfo.shopName);
            holder.mShopLocation.setText(shopInfo.shopAddress2 + " " + shopInfo.shopAddress1);
            holder.mShopPrice.setText("$" + shopInfo.price);
            holder.mShopRating.setText(String.valueOf(shopInfo.score));
            Glide.with(LKApplication.getLKApplication())
                    .load(shopInfo.shopImages)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    //.override(360, 280)
                    .into(holder.mShopMainImage);
            holder.mShopMainImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
                    intent.putExtra("shopId", shopInfo.shopId);
                    owner.startActivity(intent);
                }
            });

            Glide.with(LKApplication.getLKApplication())
                    .load(shopInfo.shopIcon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(40, 40)
                    .into(holder.mShopCircleImage);
            Log.e("test22", "" + shopInfo.likes);
            if (shopInfo.likes) {
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
                    favorObject.setShopId(shopInfo.shopId);
                    favorObject.setUserId(1);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final String result = OkHttpAPIHelperHandler.favorJSONInsert(favorObject);
                            owner.runOnUiThread(new Runnable() {
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
                                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                                        ft.detach(TabFragment1.tabFragment1)
                                                .attach(TabFragment1.tabFragment1).commit();
                                        for(int i=0;i<mResult.size();i++){
                                            if(mResult.get(i).shopName
                                                    .equals(holder.mShopName.getText().toString())){
                                                if(mResult.get(i).likes==true){
                                                    mResult.get(i).likes=false;
                                                }else{
                                                    mResult.get(i).likes=true;
                                                }
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }).start();

                }
            });
            holder.mShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendShare();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mResult.size();
        }

        public void addAll(ArrayList<LKShopListObject> objects) {
            //this.mResult.clear();
            //this.mResult.addAll(objects);
            this.mResult=objects;

            resultsCountTextView.setText(mResult.size() + " Results");
            tagList = new ArrayList<>();
            tagList.clear();
            for (int i = 0; i < mResult.size(); i++) {
                String tagName = mResult.get(i).activityName;
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
            if (mResult.size() < 1) {
                resultsCountTextView.setText("There is No Results");
            }
        }
    }

    public class AsyncShopInfoJSONList
            extends AsyncTask<String, Integer, ArrayList<LKShopListObject>> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dialog = ProgressDialog.show(owner, "", "Loading...", true);
        }

        @Override
        protected ArrayList<LKShopListObject> doInBackground(String... params) {
            return OkHttpAPIHelperHandler.shopListJSONAllSelect();
        }

        @Override
        protected void onPostExecute(ArrayList<LKShopListObject> result) {
            //dialog.dismiss();
            if (result != null && result.size() > 0) {
                // MainActivity.class로 Object 전달
                shopListSetListener.shopListSetData(result);
                // Adapter result 값 Add
                rvAdapter.addAll(result);
                rvAdapter.notifyDataSetChanged();

            } else {
            }
        }
    }

    public void setResultTagOne(ArrayList<String> tagResult) {
        filterTag1.setText("#" + tagResult.get(0));
        filterTag2.setText("");
        filterTag3.setText("");
        filterTag4.setText("");
    }

    public void setResultTagTwo(ArrayList<String> tagResult) {
        filterTag1.setText("#" + tagResult.get(0));
        filterTag2.setText("#" + tagResult.get(1));
        filterTag3.setText("");
        filterTag4.setText("");
    }

    public void setResultTagThree(ArrayList<String> tagResult) {
        filterTag1.setText("#" + tagResult.get(0));
        filterTag2.setText("#" + tagResult.get(1));
        filterTag3.setText("#" + tagResult.get(2));
        filterTag4.setText("");
    }

    public void setResultTagFour(ArrayList<String> tagResult) {
        filterTag1.setText("#" + tagResult.get(0));
        filterTag2.setText("#" + tagResult.get(1));
        filterTag3.setText("#" + tagResult.get(2));
        filterTag4.setText("#" + tagResult.get(3));
    }

    private void sendShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");

        List<ResolveInfo> resInfo = owner.getPackageManager().queryIntentActivities(intent, 0);
        if (resInfo.isEmpty()) {
            return;
        }

        List<Intent> shareIntentList = new ArrayList<Intent>();

        for (ResolveInfo info : resInfo) {
            Intent shareIntent = (Intent) intent.clone();

            if (info.activityInfo.packageName.toLowerCase().equals("com.facebook.katana")) {
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http:/leisurekr.com");

            } else if (info.activityInfo.packageName.toLowerCase().equals("com.kakao.talk")) {

                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http:/leisurekr.com");

            } else {
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http:/leisurekr.com");
            }
            shareIntent.setPackage(info.activityInfo.packageName);
            shareIntentList.add(shareIntent);
        }

        Intent chooserIntent = Intent.createChooser(shareIntentList.remove(0), "select");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, shareIntentList.toArray(new Parcelable[]{}));
        startActivity(chooserIntent);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TabFragment2","onPause()");
    }
}
