package com.nadi.shopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.Model.Item1WallAmazingOfferModel;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ConcatAdapter1Wall extends RecyclerView.Adapter<ConcatAdapter1Wall.MyViewHolder> {

    Context context;
    List<Item1WallAmazingOfferModel> data;

    public ConcatAdapter1Wall(Context context, List<Item1WallAmazingOfferModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
      @Override
      public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(context).inflate(R.layout.item_1_special_wall_home_fragment, parent, false);

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


              title_TV = itemView.findViewById(R.id.txtTitleWall_item1SpecialHomeFragment);
               imgWall = itemView.findViewById(R.id.imgAmazingGoodsWall_item1SpecialHomeFragment);

          }
      }

}
