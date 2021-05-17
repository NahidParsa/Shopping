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

public class AllNewProductAdapter extends RecyclerView.Adapter<AllNewProductAdapter.MyViewHolder> {

    List<Item0AmazingOfferModel> data;
    Context context;

    public AllNewProductAdapter(List<Item0AmazingOfferModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_new_product_activity, parent, false);

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


        if (Integer.parseInt(item.getOff_percentage()) == 0){
            holder.offPercentage_TV.setVisibility(View.GONE);
            holder.offPrice_TV.setText(decimalRealPrice);// vase inke betar dide beshe
            holder.realPrice_TV.setVisibility(View.GONE);
            holder.dollarSign_TV.setVisibility(View.GONE);

        }
        else {
            holder.offPercentage_TV.setVisibility(View.VISIBLE);
            holder.realPrice_TV.setVisibility(View.VISIBLE);
            holder.dollarSign_TV.setVisibility(View.VISIBLE);

            holder.offPercentage_TV.setText(item.getOff_percentage() + " %");
            holder.offPrice_TV.setText(decimalOffPrice);
            holder.realPrice_TV.setText(spannableString);

        }

       holder.titleProduct_TV.setText(item.getName());
       Picasso.get().load(item.getLink_img()).into(holder.imgProduct_IV);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleProduct_TV;
        ImageView imgProduct_IV;
        TextView  offPrice_TV;
        TextView  offPercentage_TV;
        TextView  realPrice_TV;
        TextView  dollarSign_TV;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleProduct_TV = itemView.findViewById(R.id.txtNameProduct_itemAllNewProductActivity);
            imgProduct_IV = itemView.findViewById(R.id.imgProduct_itemAllNewProductActivity);
            offPercentage_TV = itemView.findViewById(R.id.offPercentage_itemAllNewProductActivity);
            offPrice_TV= itemView.findViewById(R.id.offPrice_itemAllNewProductActivity);
            realPrice_TV = itemView.findViewById(R.id.realPrice_itemAllNewProductActivity);
            dollarSign_TV = itemView.findViewById(R.id.dollarSign_itemAllNewProductActivity);
        }
    }
}
