package com.leisurekr.leisuresportskorea.shop_detail;

import android.support.annotation.Nullable;

/**
 * Created by mobile on 2017. 6. 2..
 */

public class ReviewData {
    private Integer reviewerImage;
    private Integer attachedReviewImage;
    private String reviewerName;
    private String reviewText;
    private String reviewDate;

    public ReviewData(){}

    public ReviewData(@Nullable Integer reviewerImage, @Nullable Integer attachedReviewImage,
                      String reviewerName, String reviewText, String reviewDate){
        this.reviewerImage = reviewerImage;
        this.attachedReviewImage = attachedReviewImage;
        this.reviewerName = reviewerName;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }


    public Integer getReviewerImage() {
        return reviewerImage;
    }

    public void setReviewerImage(Integer reviewerImage) {
        this.reviewerImage = reviewerImage;
    }

    public Integer getAttachedReviewImage() {
        return attachedReviewImage;
    }

    public void setAttachedReivewImage(Integer attachedReviewImage) {
        this.attachedReviewImage = attachedReviewImage;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}
