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
import com.nadi.shopping.Model.Item1WallAmazingOfferModel;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class FakeAdapterWall extends RecyclerView.Adapter<FakeAdapterWall.MyViewHolder> {

    List<Item1WallAmazingOfferModel> data;
    Context context;

    public FakeAdapterWall(List<Item1WallAmazingOfferModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_1_amazing_offers_wall_home_fragment, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item1WallAmazingOfferModel item = data.get(position);

        holder.title_TV.setText(item.getTitle());
        Picasso.get().load(item.getLink_img()).into(holder.imgWall);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title_TV;
        ImageView imgWall;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            title_TV = itemView.findViewById(R.id.txtTitleWall_item1AmazingOfferHomeFragment);
             imgWall = itemView.findViewById(R.id.imgAmazingGoodsWall_item1AmazingOfferHomeFragment);

        }
    }
}
