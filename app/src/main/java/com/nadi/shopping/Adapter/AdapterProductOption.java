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

import com.nadi.shopping.Activity.DetailCategoryActivity;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Model.CategoryModel;
import com.nadi.shopping.Model.OptionProductModel;
import com.nadi.shopping.R;
import com.nadi.shopping.interfaces.MyClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterProductOption extends RecyclerView.Adapter<AdapterProductOption.MyViewHolder> {

    Context context;
    List<OptionProductModel> data;
    MyClickListener myClickListener;

    public AdapterProductOption(Context context, List<OptionProductModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_option_product_show_detail_activity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        OptionProductModel item = data.get(position);

        holder.description_TV.setText(item.getDescription());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView description_TV;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            description_TV = itemView.findViewById(R.id.optionProduct_itemOptionShowDetail);
        }
    }
}
