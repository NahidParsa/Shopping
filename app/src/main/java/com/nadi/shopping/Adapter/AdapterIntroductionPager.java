package com.nadi.shopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.nadi.shopping.Model.IntroductionModel;
import com.nadi.shopping.Model.IntroductionPagerModel;
import com.nadi.shopping.R;

import java.util.List;

public class AdapterIntroductionPager extends PagerAdapter {

    List<IntroductionPagerModel> data;
    Context context;

    public AdapterIntroductionPager(List<IntroductionPagerModel> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_pager_introduction, container, false);
        TextView description_TV = view.findViewById(R.id.description_itemPagerIntroduction);
        LottieAnimationView lottieAnimate = view.findViewById(R.id.lottieAnimation_itemPagerIntroduction);

        description_TV.setText(data.get(position).getDescription());
        lottieAnimate.setAnimationFromUrl(data.get(position).getLink_animate());

//        view.setRotationY(-180);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object );
    }
}
