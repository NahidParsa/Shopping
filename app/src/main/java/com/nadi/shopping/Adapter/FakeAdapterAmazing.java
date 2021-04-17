package com.nadi.shopping.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
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

public class FakeAdapterAmazing extends RecyclerView.Adapter<FakeAdapterAmazing.MyViewHolder> {

    List<Item0AmazingOfferModel> data;
    Context context;

    public FakeAdapterAmazing(List<Item0AmazingOfferModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_0_amazing_offers_home_fragment, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item0AmazingOfferModel item = data.get(position);

        DecimalFormat decimalFormat = new DecimalFormat("###,###");
         String decimalOffPrice = decimalFormat.format(Integer.valueOf(item.getDiscount_price()));
         String decimalRealPrice = decimalFormat.format(Integer.valueOf(item.getPrice()));
        SpannableString spannableString = new SpannableString(decimalRealPrice);
         spannableString.setSpan( new StrikethroughSpan(), 0, item.getPrice().length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
//         spannableString.setSpan( new ForegroundColorSpan(Color.parseColor("#ff0000")), 0, item.getPrice().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


        holder.offPercentage_TV.setText(item.getOff_percentage() + " %");
               holder.offPrice_TV.setText(decimalOffPrice);
               holder.realPrice_TV.setText(spannableString);
               holder.txtNameAmazingGoods_TV.setText(item.getName());

//        holder.offPercentage_TV.setText(item.getOff_percentage() + " %");
//        holder.offPrice_TV.setText(item.getDiscount_price());
//        holder.realPrice_TV.setText(item.getPrice());
//        holder.txtNameAmazingGoods_TV.setText(item.getName());
        Picasso.get().load(item.getLink_img()).into(holder.imgAmazingGoods_IV);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameAmazingGoods_TV;
        ImageView imgAmazingGoods_IV;
        TextView  offPrice_TV;
        TextView  offPercentage_TV;
        TextView  realPrice_TV;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            txtNameAmazingGoods_TV = itemView.findViewById(R.id.txtNameAmazingGoods_item0AmazingOfferHomeFragment);
             imgAmazingGoods_IV = itemView.findViewById(R.id.imgAmazingGoods_item0AmazingOfferHomeFragment);
            offPercentage_TV = itemView.findViewById(R.id.offPercentage_item0AmazingOfferHomeFragment);
             offPrice_TV= itemView.findViewById(R.id.offPrice_item0AmazingOfferHomeFragment);
             realPrice_TV = itemView.findViewById(R.id.realPrice_item0AmazingOfferHomeFragment);
        }
    }
}
