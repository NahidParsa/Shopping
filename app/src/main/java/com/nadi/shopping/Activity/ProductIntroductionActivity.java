package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Adapter.IntroductionAdapter;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.IntroductionModel;
import com.nadi.shopping.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductIntroductionActivity extends AppCompatActivity {
// general views
    ImageView back_IV;
    TextView title_TV;

    // bundle
    Bundle bundle;
    String id;
    String title;

    // api
    ApiInterface apiInterface;

    // recycler Introduction
    RecyclerView recyclerViewIntroduction;
    List<IntroductionModel> introductionModelList = new ArrayList<>();
    IntroductionAdapter introductionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_introduction);

        init();

    }

    private void init() {

        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);

        back_IV = findViewById(R.id.back_productIntroductionActivity);
        title_TV = findViewById(R.id.title_productIntroductionActivity);

        myBackPressed();
        myBundle();
    }

    private void myBackPressed() {
        back_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void myBundle() {
        bundle = getIntent().getExtras();
        id= bundle.getString(KEY.id);
        title = bundle.getString(KEY.title);

        title_TV.setText(title);

        initRecyclerIntroduction(id);

    }

    private void initRecyclerIntroduction(String id) {

        recyclerViewIntroduction = findViewById(R.id.recyclerIntroduction_productIntroductionActivity);
        recyclerViewIntroduction.setHasFixedSize(true);
        recyclerViewIntroduction.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));

        apiInterface.postIdGetIntroduction(id).enqueue(new Callback<List<IntroductionModel>>() {
            @Override
            public void onResponse(Call<List<IntroductionModel>> call, Response<List<IntroductionModel>> response) {
                introductionModelList = response.body();
                introductionAdapter = new IntroductionAdapter(getApplicationContext(), introductionModelList);
                recyclerViewIntroduction.setAdapter(introductionAdapter);
                introductionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<IntroductionModel>> call, Throwable t) {
                Log.d("TAG", "onFailure: introduction");

            }
        });

    }

}
