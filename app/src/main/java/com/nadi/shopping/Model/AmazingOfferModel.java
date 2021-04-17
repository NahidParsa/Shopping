package com.nadi.shopping.Model;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class AmazingOfferModel {

    int type;
    Object object;

    public AmazingOfferModel(int type, Object object) {
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
