package com.example.ultimateproject.RecyclerView;

import android.graphics.Bitmap;

public class RecyclerViewModel {
    private int id;
    private String name;
    private String posting;
    private String date;



//    public RecyclerViewModel(String titleTv, String subTitleTv) {
//        this.titleTv = titleTv;
//        this.subTitleTv = subTitleTv;
//    }


    public RecyclerViewModel(int id, String name, String posting, String date) {
        this.id = id;
        this.name = name;
        this.posting = posting;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosting() {
        return posting;
    }

    public void setPosting(String posting) {
        this.posting = posting;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
