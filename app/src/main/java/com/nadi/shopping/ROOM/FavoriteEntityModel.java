package com.nadi.shopping.ROOM;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "favorite")
public class FavoriteEntityModel {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "catogory_id")
     private String catogory_id;

    @ColumnInfo(name = "product_id")
     private String product_id;

    @ColumnInfo(name = "name")
     private String name;

    @ColumnInfo(name = "link_img")
     private String link_img;

    @ColumnInfo(name = "price")
    private String  price;

    @ColumnInfo(name = "off_percentage")
    private String  off_percentage;

    @ColumnInfo(name = "brand")
    private String  brand;

    @ColumnInfo(name = "discount_price")
    private String discount_price;

    public FavoriteEntityModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatogory_id() {
        return catogory_id;
    }

    public void setCatogory_id(String catogory_id) {
        this.catogory_id = catogory_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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
}
