package com.nadi.shopping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.Activity.DetailCategoryActivity;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Model.CategoryWithoutLimitModel;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryWithoutLimitAdapter extends RecyclerView.Adapter<CategoryWithoutLimitAdapter.MyViewHolder> {

    Context context;
    List<CategoryWithoutLimitModel> data;

    public CategoryWithoutLimitAdapter(Context context, List<CategoryWithoutLimitModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_category_fragment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CategoryWithoutLimitModel item = data.get(position);
        holder.categoryName_TV.setText(item.getTitle());
        Picasso.get().load(item.getLink_img()).into(holder.categoryImg_IV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , DetailCategoryActivity.class);
                  intent.putExtra(KEY.title, data.get(position).getTitle());
                  intent.putExtra(KEY.id, data.get(position).getId());
                  context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView categoryImg_IV;
        TextView categoryName_TV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImg_IV = itemView.findViewById(R.id.categoryImage_itemListCategoryFragment);
            categoryName_TV = itemView.findViewById(R.id.categoryTitle_itemListCategoryFragment);
        }
    }
}
