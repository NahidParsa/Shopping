package com.nadi.shopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.Model.PagerModel;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MiddleViewPagerAdapter extends RecyclerView.Adapter<MiddleViewPagerAdapter.MyViewHolder> {

    List<PagerModel> data;
    Context context;

    public MiddleViewPagerAdapter(List<PagerModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_middle_pager_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PagerModel item = data.get(position);

        Picasso.get().load(item.getLink_img()).into(holder.imageView_IV);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_IV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_IV = itemView.findViewById(R.id.image_itemMiddlePager);
        }
    }
}
