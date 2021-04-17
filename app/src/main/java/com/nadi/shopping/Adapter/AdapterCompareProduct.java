package com.nadi.shopping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.Activity.CompareActivity;
import com.nadi.shopping.Activity.CompareProductActivity;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Model.Item0AmazingOfferModel;
import com.nadi.shopping.Model.NewProductsModel;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdapterCompareProduct extends RecyclerView.Adapter<AdapterCompareProduct.MyViewHolder> {
    Context context;
    List<Item0AmazingOfferModel> data;
    ArrayList<String> idd = new ArrayList<>();

    public AdapterCompareProduct(Context context, List<Item0AmazingOfferModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_compare_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item0AmazingOfferModel item = data.get(position);

        holder.title_TV.setText(item.getName());
        Picasso.get().load(item.getLink_img()).into(holder.productImg_IV);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String decimalRealPrice = decimalFormat.format(Integer.valueOf(item.getPrice()));
        String decimalFinalPrice = decimalFormat.format(Integer.valueOf(item.getDiscount_price()));

        Log.d("TAG", "onBindViewHolder: "+ item.getLink_img());

        if (Integer.parseInt(item.getOff_percentage()) == 0){
          holder.offPercentage_TV.setVisibility(View.GONE);
          holder.realPrice_TV.setVisibility(View.GONE);
          holder.finalPrice_TV.setText(decimalRealPrice);
        }
        else {
            SpannableString spannableString = new SpannableString(decimalRealPrice);
            spannableString.setSpan(new StrikethroughSpan(), 0, item.getPrice().length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

            holder.offPercentage_TV.setVisibility(View.VISIBLE);
            holder.realPrice_TV.setVisibility(View.VISIBLE);
            holder.realPrice_TV.setText(spannableString);
            holder.offPercentage_TV.setText(item.getOff_percentage());
            holder.finalPrice_TV.setText(decimalFinalPrice);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                item.setSelected(!item.isSelected());
//                holder.itemView.setBackgroundColor(item.isSelected() ? Color.BLACK : Color.blue(R.color.colorBlue));
//
//                idd.add(item.getId());
//
//
//                // remove repeated elements
//                Set<String> set = new HashSet<>(idd);
//                idd.clear();
//                idd.addAll(set);

                Intent intent = new Intent(context, CompareActivity.class);

                intent.putExtra(KEY.id_second, item.getId());
                intent.putExtra(KEY.title_second, item.getName());
                intent.putExtra(KEY.realPrice_second, item.getPrice());
                intent.putExtra(KEY.link_img_second, item.getLink_img());

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView productImg_IV;
        TextView title_TV;
        TextView realPrice_TV;
        TextView finalPrice_TV;
        TextView offPercentage_TV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImg_IV = itemView.findViewById(R.id.img_itemCompareProduct);
            title_TV = itemView.findViewById(R.id.title_itemCompareProduct);
            realPrice_TV = itemView.findViewById(R.id.realPrice_itemCompareProduct);
            finalPrice_TV = itemView.findViewById(R.id.finalPrice_itemCompareProduct);
            offPercentage_TV = itemView.findViewById(R.id.offPercentage_itemCompareProduct);


        }
    }
}
