package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Adapter.AdapterAmazingOfferDetailCategory;
import com.nadi.shopping.Adapter.AdapterPopularDetailCategory;
import com.nadi.shopping.Adapter.CategoryDetailBrandAdapter;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.CategoryDetailBrandModel;
import com.nadi.shopping.Model.Item0AmazingOfferModel;
import com.nadi.shopping.Model.PopularOfferModel;
import com.nadi.shopping.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCategoryActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    RequestQueue requestQueueVolley;

    Bundle bundle;
    String title;
    String id;

    TextView title_TV;
    ImageView back_IV;

    // recycler view brand
    RecyclerView recyclerViewCategoryBrand;
    CategoryDetailBrandAdapter adapterCategoryDetailBrand;

    // amazing offer
    RecyclerView recyclerViewAmazingOffer;
    AdapterAmazingOfferDetailCategory adapterAmazingOfferDetailCategory;
    List<Item0AmazingOfferModel> amazingOfferDetailModelList = new ArrayList<>();

    //
    RecyclerView recyclerViewPopularOffer;
    AdapterPopularDetailCategory adapterPopularDetailCategory;
    List<PopularOfferModel> popularOfferModelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);
        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);
        requestQueueVolley = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));

        init();

    }

    private void init() {
        initViews();

    }

    private void initViews() {

        title_TV = findViewById(R.id.title_detailCategoryActivity);
        back_IV = findViewById(R.id.back_detailCategoryActivity);

        myGetBundle();
        myBackPressed();

    }

    private void myBackPressed() {
        back_IV.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
              onBackPressed();
              finish();
          }
        });
    }

    private void myGetBundle() {
        bundle = getIntent().getExtras();
        title = bundle.getString(KEY.title);
        id = bundle.getString(KEY.id);
        title_TV.setText(title);


        initBrand(id);
        initAmazingOffer(id);
        initPopularDetail(id);
    }

    private void initPopularDetail(String id) {

        recyclerViewPopularOffer = findViewById(R.id.recyclerViewPopular_detailCategoryActivity);
        recyclerViewPopularOffer.setHasFixedSize(true);
        recyclerViewPopularOffer.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));

        apiInterface.postIdGetPopular(id).enqueue(new Callback<List<Item0AmazingOfferModel>>() {
            @Override
            public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {

                adapterPopularDetailCategory = new AdapterPopularDetailCategory(response.body(), getApplicationContext());
                recyclerViewPopularOffer.setAdapter(adapterPopularDetailCategory);
                adapterPopularDetailCategory.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Item0AmazingOfferModel>> call, Throwable t) {

                Log.d("TAG", "onFailure: popular");
            }
        });


    }

    private void initAmazingOffer(String id) {

        recyclerViewAmazingOffer = findViewById(R.id.recyclerViewAmazingOffers_detailCategoryActivity);
          recyclerViewAmazingOffer.setHasFixedSize(true);
          recyclerViewAmazingOffer.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

          apiInterface.postIdGetInfo(id).enqueue(new Callback<List<Item0AmazingOfferModel>>() {
              @Override
              public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {
                  amazingOfferDetailModelList = response.body();
                  adapterAmazingOfferDetailCategory = new AdapterAmazingOfferDetailCategory(getApplicationContext(), amazingOfferDetailModelList);
                  recyclerViewAmazingOffer.setAdapter(adapterAmazingOfferDetailCategory);
                adapterAmazingOfferDetailCategory.notifyDataSetChanged();
              }

              @Override
              public void onFailure(Call<List<Item0AmazingOfferModel>> call, Throwable t) {
                  Log.d("TAG", "onFailure: amazing offer");
              }
          });


    }

    private void initBrand(String id) {

        recyclerViewCategoryBrand = findViewById(R.id.recyclerViewBrand_detailCategoryActivity);
        recyclerViewCategoryBrand.setHasFixedSize(true);
        recyclerViewCategoryBrand.setLayoutManager(new GridLayoutManager(DetailCategoryActivity.this, 2 ));

        Call<List<CategoryDetailBrandModel>> call = apiInterface.postId(id);

        call.enqueue(new Callback<List<CategoryDetailBrandModel>>() {
            @Override
            public void onResponse(Call<List<CategoryDetailBrandModel>> call, Response<List<CategoryDetailBrandModel>> response) {

                adapterCategoryDetailBrand = new CategoryDetailBrandAdapter(DetailCategoryActivity.this , response.body());
                recyclerViewCategoryBrand.setAdapter(adapterCategoryDetailBrand);
                adapterCategoryDetailBrand.notifyDataSetChanged();
                Log.d("TAG", "on response: call back");
            }

            @Override
            public void onFailure(Call<List<CategoryDetailBrandModel>> call, Throwable t) {
                Log.d("TAG", "on failure: call back");
            }
        });





    }

}
