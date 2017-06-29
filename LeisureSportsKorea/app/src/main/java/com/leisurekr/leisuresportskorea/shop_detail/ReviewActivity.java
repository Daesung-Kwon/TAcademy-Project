package com.leisurekr.leisuresportskorea.shop_detail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.shop.TabFragment2;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 6. 1..
 */

public class ReviewActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv;
    ReviewRVAdapter rvAdapter;

    static ReviewActivity owner;
    private RatingBar ratingBar;
    private TextView ratingTotalCount;

    double totalRatingValue;

    ArrayList<LKShopReviewsObject> reviewsObjects = null;

    int shopId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        owner = this;
        Intent intent = getIntent();
        shopId = intent.getIntExtra("shopId", -1);
        Log.i("test", "intent "+shopId);

        if (shopId != -1) {
            // Toolbar
            toolbar             = (Toolbar) findViewById(R.id.review_toolbar);
            ratingTotalCount    = (TextView) findViewById(R.id.rating_total_count);
            ratingBar           = (RatingBar) findViewById(R.id.review_rating_bar);
            rv                  = (RecyclerView) findViewById(R.id.review_recyclerview);

            toolbar.setTitle("Review List");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            ArrayList<LKShopReviewsObject> reviewsData = new ArrayList<>();

            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            rvAdapter = new ReviewRVAdapter(reviewsData);
            rv.setAdapter(rvAdapter);

            totalRatingValue = 0.0; // 초기화
            ratingBar.setRating((float)totalRatingValue);

            new AsyncShopReviewJSONList().execute();
        }
    }

    public static class ReviewRVAdapter
            extends RecyclerView.Adapter<ReviewRVAdapter.ViewHolder> {
        private ArrayList<LKShopReviewsObject> mReviewsData;

        public ReviewRVAdapter(ArrayList<LKShopReviewsObject> reviewsData) {
            mReviewsData = reviewsData;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final ImageView mReviewerImage;
            public final ImageView mAttachedReviewImage;
            public final TextView mReviewerName;
            public final TextView mReviewText;
            public final TextView mReviewDate;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mReviewerImage = (ImageView) view.findViewById(R.id.reviewer_circle_image);
                mAttachedReviewImage = (ImageView) view.findViewById(R.id.review_attached_image);
                mReviewerName = (TextView) view.findViewById(R.id.individual_reviewer_name);
                mReviewText = (TextView) view.findViewById(R.id.individual_review_text);
                mReviewDate = (TextView) view.findViewById(R.id.individaul_review_date_text);
            }
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.review_fragment_item, parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(ReviewRVAdapter.ViewHolder holder, int position) {

            holder.mReviewDate.setText(mReviewsData.get(position).date);
            holder.mReviewText.setText(mReviewsData.get(position).review);
            holder.mReviewerName.setText(mReviewsData.get(position).userName);

            switch (mReviewsData.get(position).sex) {
                case "Male":
                    holder.mReviewerImage.setImageResource(R.drawable.ic_ma);
                    break;
                case "Female":
                    holder.mReviewerImage.setImageResource(R.drawable.ic_fe);
                    break;
                default:
                    holder.mReviewerImage.setImageResource(R.drawable.ic_ne);
                    break;
            }
            if (mReviewsData.get(position).attachedImage != null) {
                Glide.with(LKApplication.getLKApplication())
                        .load(mReviewsData.get(position).attachedImage)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        //.override(360, 280)
                        .into(holder.mAttachedReviewImage);
            }
        }

        @Override
        public int getItemCount() {
            return mReviewsData.size();
        }

        public void addAll(ArrayList<LKShopReviewsObject> data) {
            mReviewsData.addAll(data);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public class AsyncShopReviewJSONList
            extends AsyncTask<String, Integer, ArrayList<LKShopReviewsObject>> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("test", "onPreExecute started");
            //dialog = ProgressDialog.show(owner, "", "Loading...", true);
        }

        @Override
        protected ArrayList<LKShopReviewsObject> doInBackground(String... params) {
            Log.i("test", "doInBackground started");
            return OkHttpAPIHelperHandler.shopReviewJSONAllSelect(shopId);
        }
        @Override
        protected void onPostExecute(ArrayList<LKShopReviewsObject> result) {
            //dialog.dismiss();
            Log.i("test", "onPostExecute started");
            reviewsObjects = result;
            Log.i("test", ""+reviewsObjects.get(0).sex);
            if (result != null && reviewsObjects.size() > 0) {
                reviewsObjects = result;
                // MainActivity.class로 Object 전달
                // Adapter result 값 Add
                rvAdapter.addAll(reviewsObjects);
                rvAdapter.notifyDataSetChanged();

                ratingBar.setRating(Float.parseFloat(reviewsObjects.get(0).score));
                ratingTotalCount.setText(""+reviewsObjects.get(0).count);

            }else {
                ratingTotalCount.setText("0");
                Log.i("Test", "reviews result is NULL");
            }
        }
    }
}
