package com.example.daycareapp.models;
public class ReviewListData {
    private String reviewerName;
    private String review;

    private int imgId;
    public ReviewListData(String reviewerName, String review, int imgId) {
        this.reviewerName = reviewerName;
        this.review = review;
        this.imgId = imgId;
    }
    public String getReviewerName() {
        return reviewerName;
    }
    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}