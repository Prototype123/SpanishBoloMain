package com.rainbow.spanishbolomain.Models;

public class Course {
    private String image;
    private String thumb;
    private String title;
    private long rating;
    private long money;
    private String uid;

    public Course(String image, String thumb, String title, long rating, long money, String uid) {
        this.image = image;
        this.thumb = thumb;
        this.title = title;
        this.rating = rating;
        this.money = money;
        this.uid = uid;
    }

    public Course(){}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

