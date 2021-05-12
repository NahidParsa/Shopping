package com.nadi.shopping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nadi.shopping.Activity.ShowCommentsActivity;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Model.CommentsModel;
import com.nadi.shopping.R;

import java.util.List;

public class AdapterCommentsShow extends RecyclerView.Adapter<AdapterCommentsShow.MyViewHolder> {

    List<CommentsModel> data;
    Context context;

    public AdapterCommentsShow(List<CommentsModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_show_comments, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CommentsModel item = data.get(position);
        holder.title_TV.setText(item.getTitle());
        holder.date_TV.setText(item.getDate());
        holder.description_TV.setText(item.getDescription());
        holder.email_TV.setText(item.getUser_email());
        holder.like_TV.setText(item.getPositive());
        holder.dislike_TV.setText(item.getNegative());
        holder.rating_TV.setText(item.getRating());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rating_TV;

        TextView title_TV;
        TextView date_TV;

        TextView description_TV;
        TextView email_TV;
        TextView like_TV;
        TextView dislike_TV;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            rating_TV = itemView.findViewById(R.id.rating_itemShowComments);
            title_TV = itemView.findViewById(R.id.titleView_itemShowComments);
            date_TV = itemView.findViewById(R.id.date_itemShowComments);
            description_TV = itemView.findViewById(R.id.description_itemShowComments);
            email_TV = itemView.findViewById(R.id.email_itemShowComments);
            like_TV = itemView.findViewById(R.id.like_itemShowComments);
            dislike_TV = itemView.findViewById(R.id.dislike_itemShowComments);

        }
    }
}
