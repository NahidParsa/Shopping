package com.nadi.shopping.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Response;

public class Item1WallAmazingOfferModel {

//    // this information are taken from product table

//    @SerializedName("id")
    private String id;
//    @SerializedName("type")
    private String type;
//    @SerializedName("link_img")
    private String link_img;
//    @SerializedName("title")
    private String title;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
