package com.nadi.shopping.Model;

import android.view.View;

public class CategoryDetailBrandModel {
    private String id;
    private String title;
    private String link_img;
    private String catogory_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

    public String getCatogory_id() {
        return catogory_id;
    }

    public void setCatogory_id(String catogory_id) {
        this.catogory_id = catogory_id;
    }

}
