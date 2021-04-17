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

import com.nadi.shopping.Model.Item0AmazingOfferModel;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterAmazingOfferDetailCategory extends RecyclerView.Adapter<AdapterAmazingOfferDetailCategory.MyViewHolder> {

     Context context;
     List<Item0AmazingOfferModel> data;

    public AdapterAmazingOfferDetailCategory(Context context, List<Item0AmazingOfferModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_amazing_offers_detail_category_activity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item0AmazingOfferModel item = data.get(position);
        holder.txtNameAmazingOffer_TV.setText(item.getName());

        ///// mikhahim 3 ta 3 ta joda konad
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String decimalOffPrice = decimalFormat.format(Integer.valueOf(item.getDiscount_price()));
        String decimalRealPrice = decimalFormat.format(Integer.valueOf(item.getPrice()));
        ///////khat keshidan rooye adad
        SpannableString spannableString = new SpannableString(decimalRealPrice);
        spannableString.setSpan(new StrikethroughSpan(), 0, item.getPrice().length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);


        holder.offPercentage_TV.setText(item.getOff_percentage()+ " %");
        holder.offPrice_TV.setText(decimalOffPrice);
        holder.realPrice_TV.setText(spannableString);
        holder.stock_TV.setText(item.getStock() + "   Stock");

        Picasso.get().load(item.getLink_img()).into(holder.imgAmazingOffer_IV);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameAmazingOffer_TV;
        ImageView imgAmazingOffer_IV;
        TextView  offPercentage_TV;
        TextView  realPrice_TV;
        TextView  offPrice_TV;
        TextView  stock_TV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

             txtNameAmazingOffer_TV = itemView.findViewById(R.id.txtNameAmazingGoods_itemAmazingOfferDetailCategoryActivity);
             imgAmazingOffer_IV = itemView.findViewById(R.id.imgAmazingGoods_itemAmazingOfferDetailCategoryActivity);
             offPercentage_TV = itemView.findViewById(R.id.offPercentage_itemAmazingOfferDetailCategoryActivity);
             offPrice_TV= itemView.findViewById(R.id.offPrice_itemAmazingOfferDetailCategoryActivity);
             realPrice_TV = itemView.findViewById(R.id.realPrice_itemAmazingOfferDetailCategoryActivity);
             stock_TV =  itemView.findViewById(R.id.txtStock_itemAmazingOfferDetailCategoryActivity);

        }
    }
}
