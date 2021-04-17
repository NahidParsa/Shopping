package com.nadi.shopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.nadi.shopping.Model.PagerModel;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PagerClassAdapter extends androidx.viewpager.widget.PagerAdapter {

    List<PagerModel> listPagerModel ;
    Context context;

    public PagerClassAdapter(List<PagerModel> listPagerModel, Context context) {
        this.listPagerModel = listPagerModel;
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pager, container, false);
        ImageView imagePager = view.findViewById(R.id.image_itemPager);
        Picasso.get().load(listPagerModel.get(position).getLink_img()).into(imagePager);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return listPagerModel.size() ;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object );
    }
}
