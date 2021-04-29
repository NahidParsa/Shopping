package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Adapter.AdapterIntroductionPager;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.IntroductionModel;
import com.nadi.shopping.Model.IntroductionPagerModel;
import com.nadi.shopping.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroductionActivity extends AppCompatActivity {

    ViewPager viewPager_VP;
    TabLayout tabLayout_TL;
    TextView next_TV;
    TextView previous_TV;
    Button start_B;

    List<IntroductionPagerModel> introductionPagerModelList = new ArrayList<>();
    AdapterIntroductionPager adapterIntroductionPager;
    //
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // delete action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_introduction);

        makeApi();
        initGeneral();
    }

    private void initGeneral() {

        viewPager_VP = findViewById(R.id.viewPager_introductionActivity);
        tabLayout_TL = findViewById(R.id.tabLayout_introductionActivity);
        next_TV = findViewById(R.id.next_introductionActivity);
        previous_TV = findViewById(R.id.previous_introductionActivity);
        start_B = findViewById(R.id.start_introductionActivity);

        setPager();
        myNextPressed();
        myPreviousPressed();
        myStartPressed();
    }

    private void myStartPressed() {

        start_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroductionActivity.this , RegisterActivity.class));
                finish();
            }
        });

    }

    private void myPreviousPressed() {
        previous_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (viewPager_VP.getCurrentItem() < introductionPagerModelList.size() - 1 ){

                  viewPager_VP.setCurrentItem(viewPager_VP.getCurrentItem()-1);
             //      start_B.setVisibility(View.GONE);
                }
                else{
                  viewPager_VP.setCurrentItem(0);
                //  start_B.setVisibility(View.VISIBLE);

              }
            }
        });
    }


    private void myNextPressed() {
        next_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager_VP.getCurrentItem() < introductionPagerModelList.size() - 1 ){

                  viewPager_VP.setCurrentItem(viewPager_VP.getCurrentItem()+1);
                 // start_B.setVisibility(View.GONE);
                }else{
                  viewPager_VP.setCurrentItem(0);
               //     start_B.setVisibility(View.VISIBLE);
              }
            }
        });
    }

    private void setPager() {


        apiInterface.callItemIntroductionPager().enqueue(new Callback<List<IntroductionPagerModel>>() {
            @Override
            public void onResponse(Call<List<IntroductionPagerModel>> call, Response<List<IntroductionPagerModel>> response) {

                introductionPagerModelList = response.body();
              adapterIntroductionPager = new AdapterIntroductionPager(introductionPagerModelList, IntroductionActivity.this);
              viewPager_VP.setAdapter(adapterIntroductionPager);
              adapterIntroductionPager.notifyDataSetChanged();
              tabLayout_TL.setupWithViewPager(viewPager_VP, true);

            }

            @Override
            public void onFailure(Call<List<IntroductionPagerModel>> call, Throwable t) {

            }
        });


        tabLayout_TL.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    previous_TV.setVisibility(View.GONE);
                }
                else {
                    previous_TV.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void makeApi() {
        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);
    }




}
