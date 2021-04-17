package com.nadi.shopping.Model;

import android.util.Log;

import java.util.List;

public class Item0AmazingOfferModel {

    // this information are taken from product table
    private String id ;
    private String  catogory_id ;
    private String  name;
    private String  link_img;
    private String  price;
    private String  off_percentage;
    private String  brand;
    private String discount_price;
    private String stock;
    private boolean isSelected = false;

    public Item0AmazingOfferModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatogory_id() {
        return catogory_id;
    }

    public void setCatogory_id(String catogory_id) {
        this.catogory_id = catogory_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOff_percentage() {
        return off_percentage;
    }

    public void setOff_percentage(String off_percentage) {
        this.off_percentage = off_percentage;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
