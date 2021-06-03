package com.nadi.shopping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.Activity.DetailCategoryActivity;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Model.CategoryModel;
import com.nadi.shopping.R;
import com.nadi.shopping.interfaces.MyClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FilterCategoryAdapter extends RecyclerView.Adapter<FilterCategoryAdapter.MyViewHolder> {

    Context context;
    List<CategoryModel> data;

    String selectedCategoryIdList;


    public FilterCategoryAdapter(Context context, List<CategoryModel> data) {
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_fiter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CategoryModel item = data.get(position);

        holder.category_CHB.setText(item.getTitle());

        holder.category_CHB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if(holder.category_CHB.isChecked()) {

                   selectedCategoryIdList = item.getId();
               }


    /*
                   selectedCategoryIdList.add(String.valueOf(item.getId()));
                   Log.d("TAG", "onClick: " + item.getId());
                   Log.d("TAG", "selected add: " + selectedCategoryIdList);

               }if (!holder.category_CHB.isChecked()){
                   selectedCategoryIdList.remove(String.valueOf(item.getId()));
                    Log.d("TAG", "selected remove: " + selectedCategoryIdList);

               }


               */
            }
                });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public String getSelectedCategoryIdList(){
            return selectedCategoryIdList;
     }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RadioButton category_CHB;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            
            category_CHB = itemView.findViewById(R.id.checkBox_filterItem);
        }
    }

}
