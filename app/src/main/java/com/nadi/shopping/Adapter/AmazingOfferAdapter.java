package com.nadi.shopping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.Activity.ShowDetailProductActivity;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Model.AmazingOfferModel;
import com.nadi.shopping.Model.Item0AmazingOfferModel;
import com.nadi.shopping.Model.Item1WallAmazingOfferModel;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class AmazingOfferAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<AmazingOfferModel> amazingOfferModelList ;
    Context context;


    public AmazingOfferAdapter(List<AmazingOfferModel> amazingOfferModelList, Context context) {
        this.amazingOfferModelList = amazingOfferModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_0_amazing_offers_home_fragment, parent, false);
            Log.d("sss", "making view: product");
            return new Item0AmazingOfferViewHolder(view);
        }
        else {
           View view = LayoutInflater.from(context).inflate(R.layout.item_1_amazing_offers_wall_home_fragment, parent, false);
           Log.d("sss", "making view: wall");
           return new Item1WallAmazingOfferViewHolder(view);
       }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("sss", "onBindViewHolder: == i dont know");


        if (getItemViewType(position) == 0){
            Log.d("sss", "onBindViewHolder: == 0");
            Item0AmazingOfferModel item0AmazingOfferModel =((Item0AmazingOfferModel) (amazingOfferModelList.get(position).getObject()));
            ((Item0AmazingOfferViewHolder)holder).setBindHolderItem0AmazingOffer(item0AmazingOfferModel);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShowDetailProductActivity.class);
                    intent.putExtra(KEY.id, ((Item0AmazingOfferModel) amazingOfferModelList.get(position).getObject()).getId());
                    intent.putExtra(KEY.title, ((Item0AmazingOfferModel) amazingOfferModelList.get(position).getObject()).getName());

                    intent.putExtra(KEY.brand, ((Item0AmazingOfferModel) amazingOfferModelList.get(position).getObject()).getBrand());
                    intent.putExtra(KEY.CategoryId, ((Item0AmazingOfferModel) amazingOfferModelList.get(position).getObject()).getCatogory_id());

                    intent.putExtra(KEY.realPrice, ((Item0AmazingOfferModel) amazingOfferModelList.get(position).getObject()).getPrice());
                    intent.putExtra(KEY.offPercentage, ((Item0AmazingOfferModel) amazingOfferModelList.get(position).getObject()).getOff_percentage());
                    intent.putExtra(KEY.offPrice, ((Item0AmazingOfferModel) amazingOfferModelList.get(position).getObject()).getDiscount_price());

                    intent.putExtra(KEY.link_img, ((Item0AmazingOfferModel) amazingOfferModelList.get(position).getObject()).getLink_img());

                    context.startActivity(intent);
                }
            });
        }
        else if (getItemViewType(position) == 1){
            Log.d("sss", "onBindViewHolder: == 1");

            Item1WallAmazingOfferModel item1WallAmazingOfferModel = ((Item1WallAmazingOfferModel) (amazingOfferModelList.get(position).getObject()));
            ((Item1WallAmazingOfferViewHolder)holder).setBindViewHolderItem1AmazingOffer(item1WallAmazingOfferModel);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return amazingOfferModelList.get(position).getType();


    }

    @Override
    public int getItemCount() {

//        Log.d("list", "list "+ amazingOfferModelList.size());
        return amazingOfferModelList.size();
    }


    public static class Item0AmazingOfferViewHolder extends RecyclerView.ViewHolder{

         TextView txtNameAmazingGoods_TV;
         ImageView imgAmazingGoods_IV;
         TextView  offPrice_TV;
         TextView  offPercentage_TV;
         TextView  realPrice_TV;

        public Item0AmazingOfferViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameAmazingGoods_TV = itemView.findViewById(R.id.txtNameAmazingGoods_item0AmazingOfferHomeFragment);
             imgAmazingGoods_IV = itemView.findViewById(R.id.imgAmazingGoods_item0AmazingOfferHomeFragment);
            offPercentage_TV = itemView.findViewById(R.id.offPercentage_item0AmazingOfferHomeFragment);
             offPrice_TV= itemView.findViewById(R.id.offPrice_item0AmazingOfferHomeFragment);
             realPrice_TV = itemView.findViewById(R.id.realPrice_item0AmazingOfferHomeFragment);

        }

        public void setBindHolderItem0AmazingOffer(Item0AmazingOfferModel item0AmazingOffer){

            ///// mikhahim 3 ta 3 ta joda konad
            DecimalFormat decimalFormat = new DecimalFormat("###,###");
            String decimalOffPrice = decimalFormat.format(Integer.valueOf(item0AmazingOffer.getDiscount_price()));
            String decimalRealPrice = decimalFormat.format(Integer.valueOf(item0AmazingOffer.getPrice()));
//    ///////         khat keshidan rooye adad
            SpannableString spannableString = new SpannableString(decimalRealPrice);
            spannableString.setSpan(new StrikethroughSpan(), 0, item0AmazingOffer.getPrice().length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

            txtNameAmazingGoods_TV.setText(item0AmazingOffer.getName());
            offPercentage_TV.setText(item0AmazingOffer.getOff_percentage()+ " %");
            offPrice_TV.setText(decimalOffPrice);
            realPrice_TV.setText(spannableString);

            Picasso.get().load(item0AmazingOffer.getLink_img()).into(imgAmazingGoods_IV);

        }
    }

    public static class Item1WallAmazingOfferViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAmazingGoodsWall_IV;
        TextView txtTitleWall_TV;

        public Item1WallAmazingOfferViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitleWall_TV = itemView.findViewById(R.id.txtTitleWall_item1AmazingOfferHomeFragment);
            imgAmazingGoodsWall_IV = itemView.findViewById(R.id.imgAmazingGoodsWall_item1AmazingOfferHomeFragment);
        }

        public void setBindViewHolderItem1AmazingOffer(Item1WallAmazingOfferModel item1WallAmazingOfferModel){

            txtTitleWall_TV.setText(item1WallAmazingOfferModel.getTitle());
            Picasso.get().load(item1WallAmazingOfferModel.getLink_img()).into(imgAmazingGoodsWall_IV);


        }
    }

}
