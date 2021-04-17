package com.nadi.shopping.Adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.Model.AmazingOfferModel;
import com.nadi.shopping.Model.Item0AmazingOfferModel;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterPopularDetailCategory extends RecyclerView.Adapter<AdapterPopularDetailCategory.MyViewHolder> {

    List<Item0AmazingOfferModel> data;
    Context context;

    public AdapterPopularDetailCategory(List<Item0AmazingOfferModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_0_popular_detail_category_activity, parent, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item0AmazingOfferModel item = data.get(position);

        DecimalFormat decimalFormat = new DecimalFormat("###,###");
         String decimalRealPrice = decimalFormat.format(Integer.valueOf(item.getPrice()));
           holder.realPrice_TV.setText(decimalRealPrice);
           holder.txtNameAmazingGoods_TV.setText(item.getName());

        Picasso.get().load(item.getLink_img()).into(holder.imgAmazingGoods_IV);



    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameAmazingGoods_TV;
         ImageView imgAmazingGoods_IV;
         TextView  realPrice_TV;


         public MyViewHolder(@NonNull View itemView) {
             super(itemView);

             txtNameAmazingGoods_TV = itemView.findViewById(R.id.txtNamePopularGoods_item0PopularDetailCategoryActivity);
              imgAmazingGoods_IV = itemView.findViewById(R.id.imgPopularGoods_item0PopularDetailCategoryActivity);
              realPrice_TV = itemView.findViewById(R.id.realPrice_item0PopularDetailCategoryActivity);
        }
    }
}
