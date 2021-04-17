package com.nadi.shopping.Model;

public class PopularOfferModel {

    int type;
    Object object;

    public PopularOfferModel(int type, Object object) {
        this.type = type;
        this.object = object;
    }



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }


}
