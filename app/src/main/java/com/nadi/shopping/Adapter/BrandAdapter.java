package com.nadi.shopping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.Activity.ShowByBrandActivity;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Model.BrandModel;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.MyViewHolder> {

    Context context;
    List<BrandModel> data;

    public BrandAdapter(Context context, List<BrandModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_brand_home_fragment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        BrandModel item = data.get(position);

        holder.brandName_TV.setText(item.getName());
        Picasso.get().load(item.getLink_img()).into(holder.imgBrand_IV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowByBrandActivity.class);
                intent.putExtra(KEY.name, item.getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView brandName_TV;
        ImageView imgBrand_IV;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            brandName_TV = itemView.findViewById(R.id.titleBrand_itemBrandHomeFragment);
            imgBrand_IV = itemView.findViewById(R.id.brandImg_itemBrandHomeFragment);
        }
    }
}
