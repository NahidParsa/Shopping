package com.nadi.shopping.Model;

import android.view.View;

public class CategoryModel {
    private String id;
    private String title;
    private String link_img;

    View.OnClickListener onClickListener;

      public View.OnClickListener getOnClickListener() {
          return onClickListener;
      }

      public void setOnClickListener(View.OnClickListener onClickListener) {
          this.onClickListener = onClickListener;
      }


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
}
