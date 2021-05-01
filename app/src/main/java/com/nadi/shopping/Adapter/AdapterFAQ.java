package com.nadi.shopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.nadi.shopping.Model.FAQModel;
import com.nadi.shopping.R;

import java.util.List;
import java.util.zip.Inflater;

public class AdapterFAQ extends RecyclerView.Adapter<AdapterFAQ.MyViewHolder> {

    List<FAQModel> data;
    Context context;

    public AdapterFAQ(List<FAQModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_faq, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FAQModel item = data.get(position);
        holder.question_TV.setText(item.getQuestion());
        holder.answer_TV.setText(item.getAnswer());

        holder.plus_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.answer_TV.getVisibility() == View.GONE){

                    holder.answer_TV.setVisibility(View.VISIBLE);
                    holder.plus_IV.setImageResource(R.drawable.clear);
                    TransitionManager.beginDelayedTransition(holder.question_LL, new AutoTransition());
                }else{
                    holder.answer_TV.setVisibility(View.GONE);
                    holder.plus_IV.setImageResource(R.drawable.add);
                    TransitionManager.beginDelayedTransition(holder.question_LL, new AutoTransition());
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView answer_TV;
        TextView question_TV;
        ImageView plus_IV;
        LinearLayout question_LL;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            answer_TV = itemView.findViewById(R.id.answer_faqItem);
            question_TV = itemView.findViewById(R.id.question_faqItem);
            plus_IV = itemView.findViewById(R.id.plus_faqItem);
            question_LL = itemView.findViewById(R.id.llQuestion_faqItem);

        }
    }
}
