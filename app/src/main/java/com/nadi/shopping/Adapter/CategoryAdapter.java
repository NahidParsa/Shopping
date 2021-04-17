package com.nadi.shopping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Activity.DetailCategoryActivity;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.CategoryDetailBrandModel;
import com.nadi.shopping.Model.CategoryModel;
import com.nadi.shopping.R;
import com.nadi.shopping.interfaces.MyClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    Context context;
    List<CategoryModel> categoryList;
    MyClickListener myClickListener;

    public CategoryAdapter(Context context, List<CategoryModel> categoryList) {
        this.context = context;
        this.categoryList = categoryList;

//        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_category_home_fragment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CategoryModel currentPosition = categoryList.get(position);

        holder.categoryTitle_TV.setText(currentPosition.getTitle());
        Picasso.get().load(currentPosition.getLink_img()).into(holder.categoryImage_CIV);

//        holder.itemView.setOnClickListener(currentPosition.getOnClickListener());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , DetailCategoryActivity.class);
                  intent.putExtra(KEY.title, categoryList.get(position).getTitle());
                  intent.putExtra(KEY.id, categoryList.get(position).getId());


                  context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle_TV;
        CircleImageView categoryImage_CIV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle_TV = itemView.findViewById(R.id.categoryTitle_categoryItem);
            categoryImage_CIV = itemView.findViewById(R.id.categoryImage_categoryItem);

        }
    }
}
