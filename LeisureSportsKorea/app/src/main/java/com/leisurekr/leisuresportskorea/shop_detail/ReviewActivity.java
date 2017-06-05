package com.leisurekr.leisuresportskorea.shop_detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.R;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 6. 1..
 */

public class ReviewActivity extends AppCompatActivity {
    Toolbar toolbar;

    static ReviewActivity owner;
    private RatingBar ratingBar;
    private TextView ratingTotalCount;

    Float totalRatingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        owner = this;

        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.review_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ArrayList<ReviewData> reviewDatas = new ArrayList<>();
        /* 테스트 데이터 */
        ReviewData rData = new ReviewData(R.drawable.girls_generation_tifany,
                R.drawable.girls_generation_all,
                "Daesung Kwon",
                "It's so cool! was really nice and fun. so I'll go and see Girls Generation again",
                "17-05-05");
        reviewDatas.add(rData);
        ReviewData rData2 = new ReviewData(R.drawable.girls_generation_tifany,
                null,
                "Daesung Kwon",
                "not satisfied... want more trial I did",
                "17-05-05");
        reviewDatas.add(rData2);
        reviewDatas.add(rData);

        RecyclerView rv = (RecyclerView) findViewById(R.id.review_recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(new ReviewRVAdapter(reviewDatas));

        ratingBar = (RatingBar) findViewById(R.id.review_rating_bar);
        totalRatingValue = (float)3.5;
        ratingBar.setRating(totalRatingValue);
    }

    public static class ReviewRVAdapter
            extends RecyclerView.Adapter<ReviewRVAdapter.ViewHolder> {
        private ArrayList<ReviewData> mReviewDatas;
        private Animation slideInAnimation;

        public ReviewRVAdapter(ArrayList<ReviewData> reviewDatas) {
            mReviewDatas = reviewDatas;
            slideInAnimation = AnimationUtils.loadAnimation(owner, android.R.anim.slide_in_left);
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final ImageView mReviewerImage;
            public final ImageView mAttachedReviewImage;
            public final TextView mReviewerName;
            public final TextView mReviewText;
            public final TextView mReviewData;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mReviewerImage = (ImageView) view.findViewById(R.id.reviewer_circle_image);
                mAttachedReviewImage = (ImageView) view.findViewById(R.id.review_attached_image);
                mReviewerName = (TextView) view.findViewById(R.id.individual_reviewer_name);
                mReviewText = (TextView) view.findViewById(R.id.individual_review_text);
                mReviewData = (TextView) view.findViewById(R.id.individaul_review_date_text);
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
            Integer reviewerImage = mReviewDatas.get(position).getReviewerImage(); // circle image;
            Integer attachedReviewImage = mReviewDatas.get(position).getAttachedReviewImage();
            String reviewerName = mReviewDatas.get(position).getReviewerName();
            String reviewText = mReviewDatas.get(position).getReviewText();
            String reviewDate = mReviewDatas.get(position).getReviewDate();

            holder.mReviewData.setText(reviewDate);
            holder.mReviewText.setText(reviewText);
            holder.mReviewerName.setText(reviewerName);

            if (attachedReviewImage != null) {
                holder.mAttachedReviewImage.setImageResource(attachedReviewImage);
            }
            holder.mReviewerImage.setImageResource(reviewerImage);
            holder.mReviewerImage.startAnimation(slideInAnimation);
        }

        @Override
        public int getItemCount() {
            return mReviewDatas.size();
        }
    }
}
