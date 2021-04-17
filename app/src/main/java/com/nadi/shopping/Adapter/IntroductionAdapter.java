package com.nadi.shopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.Model.IntroductionModel;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class IntroductionAdapter extends RecyclerView.Adapter<IntroductionAdapter.MyViewHolder> {

    Context context;
    List<IntroductionModel> data;

    public IntroductionAdapter(Context context, List<IntroductionModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_introduction, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        IntroductionModel item = data.get(position);

        holder.header_TV.setText(item.getHeader());
        holder.footer_TV.setText(item.getFooter());
        Picasso.get().load(item.getLink_img()).into(holder.img_IV);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView header_TV;
        ImageView img_IV;
        TextView footer_TV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            header_TV = itemView.findViewById(R.id.header_productIntroductionActivity);
            img_IV = itemView.findViewById(R.id.image_productIntroductionActivity);
            footer_TV = itemView.findViewById(R.id.footer_productIntroductionActivity);


        }
    }
}
