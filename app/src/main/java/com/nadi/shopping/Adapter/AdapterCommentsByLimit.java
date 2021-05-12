package com.nadi.shopping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.nadi.shopping.Activity.CompareActivity;
import com.nadi.shopping.Activity.ShowCommentsActivity;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Model.CommentsModel;
import com.nadi.shopping.Model.FAQModel;
import com.nadi.shopping.R;

import java.util.List;

public class AdapterCommentsByLimit extends RecyclerView.Adapter<AdapterCommentsByLimit.MyViewHolder> {

    List<CommentsModel> data;
    Context context;

    public AdapterCommentsByLimit(List<CommentsModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cooments_limited, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CommentsModel item = data.get(position);
        holder.title_TV.setText(item.getTitle());
        holder.date_TV.setText(item.getDate());
        holder.description_TV.setText(item.getDescription());
        holder.email_TV.setText(item.getUser_email());

        if (Integer.parseInt(item.getConfirmation()) == 0){
            holder.like_IV.setVisibility(View.GONE);
            holder.dislike_IV.setVisibility(View.VISIBLE);
            holder.positive_negative_TV.setText("I Do not Suggest This Production");
        }else{
            holder.like_IV.setVisibility(View.VISIBLE);
            holder.dislike_IV.setVisibility(View.GONE);
            holder.positive_negative_TV.setText("I Suggest This Production");
        }

        holder.cardView_CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowCommentsActivity.class);
                intent.putExtra(KEY.id_product, item.getId_product());
               context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title_TV;
        TextView date_TV;
        TextView description_TV;
        TextView email_TV;
        ImageView like_IV;
        ImageView dislike_IV;
        TextView positive_negative_TV;
        CardView cardView_CV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title_TV = itemView.findViewById(R.id.titleView_itemCommentsLimited);
            date_TV = itemView.findViewById(R.id.date_itemCommentsLimited);
            description_TV = itemView.findViewById(R.id.description_itemCommentsLimited);
            email_TV = itemView.findViewById(R.id.email_itemCommentsLimited);
            like_IV = itemView.findViewById(R.id.likeImg_itemCommentsLimited);
            dislike_IV = itemView.findViewById(R.id.dislikeImg_itemCommentsLimited);
            positive_negative_TV = itemView.findViewById(R.id.positive_itemCommentsLimited);
            cardView_CV = itemView.findViewById(R.id.cardView_itemCommentsLimited);

        }
    }
}
