package com.nadi.shopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.Model.NewProductsModel;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterSimilarProduct extends RecyclerView.Adapter<AdapterSimilarProduct.MyViewHolder> {
    Context context;
    List<NewProductsModel> data;

    public AdapterSimilarProduct(Context context, List<NewProductsModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_0_popular_detail_category_activity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        NewProductsModel item = data.get(position);

        holder.title_TV.setText(item.getName());

        DecimalFormat decimalFormat = new DecimalFormat("###,###");
         String decimalRealPrice = decimalFormat.format(Integer.valueOf(item.getPrice()));
        holder.price_TV.setText(decimalRealPrice);

        Picasso.get().load(item.getLink_img()).into(holder.productImg_IV);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView productImg_IV;
        TextView title_TV;
        TextView price_TV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImg_IV = itemView.findViewById(R.id.imgPopularGoods_item0PopularDetailCategoryActivity);
            title_TV = itemView.findViewById(R.id.txtNamePopularGoods_item0PopularDetailCategoryActivity);
            price_TV = itemView.findViewById(R.id.realPrice_item0PopularDetailCategoryActivity);

        }
    }
}
