package com.nadi.shopping.Adapter;

import android.content.Context;
import android.util.Log;
import android.util.Log;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.Model.BrandModel;
import com.nadi.shopping.R;

import java.util.ArrayList;
import java.util.List;

public class FilterBrandAdapter extends RecyclerView.Adapter<FilterBrandAdapter.MyViewHolder> {

    Context context;
    List<BrandModel> data;

    private String selectedBrandIdList;


    public FilterBrandAdapter(Context context, List<BrandModel> data) {
        this.context = context;
        this.data = data;
//        selectedBrandIdList.add("99");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_fiter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        BrandModel item = data.get(position);

        holder.brandName_CHB.setText(item.getName());

        holder.brandName_CHB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(holder.brandName_CHB.isChecked()) {
                    selectedBrandIdList = item.getName();
                }
//                if (!holder.brandName_CHB.isChecked()){
//                   selectedBrandIdList = null;
//                    Log.d("TAG", "selected remove brand: " + selectedBrandIdList);
//
//               }


/*
               if(holder.brandName_CHB.isChecked()) {


                   String[] mStrings = new String[title.length];

                      for(int i=0;i<title.length;i++) {
                          mStrings[i] = urlbase + title[i].toLowerCase() + imgSel;

                          System.out.println(mStrings[i]);
                      }

                   selectedBrandIdList.add(String.valueOf(item.getName()));
                   Log.d("TAG", "onClick brand: " + item.getName());
                   Log.d("TAG", "selected add brand: " + selectedBrandIdList);

               }if (!holder.brandName_CHB.isChecked()){
                   selectedBrandIdList.remove(String.valueOf(item.getName()));
                    Log.d("TAG", "selected remove brand: " + selectedBrandIdList);

               }
  */

            }

        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public String getSelectedBrandIdList(){
//        if (selectedBrandIdList == null){
//           selectedBrandIdList.add("999");
            Log.d("TAG", "getSelectedBrandIdList: return 999");
//            return selectedBrandIdList;
//        }

        return selectedBrandIdList;
     }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RadioButton brandName_CHB;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            brandName_CHB = itemView.findViewById(R.id.checkBox_filterItem);
        }
    }


}
