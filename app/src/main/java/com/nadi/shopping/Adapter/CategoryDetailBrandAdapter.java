package com.nadi.shopping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.Activity.DetailCategoryActivity;
import com.nadi.shopping.Activity.DetailDetailCategoryActivity;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Model.CategoryDetailBrandModel;
import com.nadi.shopping.R;
import com.nadi.shopping.interfaces.MyClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryDetailBrandAdapter extends RecyclerView.Adapter<CategoryDetailBrandAdapter.MyViewHolder> {

    Context context;
    List<CategoryDetailBrandModel> data;
    MyClickListener myClickListener;

    public CategoryDetailBrandAdapter(Context context, List<CategoryDetailBrandModel> data) {
        this.context = context;
        this.data = data;
//        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_brand_detail_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CategoryDetailBrandModel currentPosition = data.get(position);

        holder.categoryDetailTitle_TV.setText(currentPosition.getTitle());
        Picasso.get().load(currentPosition.getLink_img()).into(holder.categoryDetailImage_IV);

//        holder.itemView.setOnClickListener(currentPosition.getOnClickListener());

//////////////////////
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , DetailDetailCategoryActivity.class );
                  intent.putExtra(KEY.titleDetailCategory, data.get(position).getTitle());
                  intent.putExtra(KEY.idDetailCategory, data.get(position).getId());
//                  intent.putExtra(KEY.idDetailCategory, data.get(position).getCatogory_id());
                Log.d("olol", "getCatogory_id" + data.get(position).getCatogory_id());
                Log.d("olol", "get_id" + data.get(position).getId());
                  context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView categoryDetailTitle_TV;
        ImageView categoryDetailImage_IV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryDetailTitle_TV = itemView.findViewById(R.id.brandTitle_itemBrandDetailCategory);
            categoryDetailImage_IV = itemView.findViewById(R.id.brandImg_itemBrandDetailCategory);

        }
    }
}
