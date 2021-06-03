package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Adapter.FilterBrandAdapter;
import com.nadi.shopping.Adapter.FilterCategoryAdapter;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.BrandModel;
import com.nadi.shopping.Model.CategoryModel;
import com.nadi.shopping.Model.CategoryWithoutLimitModel;
import com.nadi.shopping.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterActivity extends AppCompatActivity  {

    // api
    ApiInterface apiInterface;

    // recycler category
    RecyclerView recyclerCategory;
    FilterCategoryAdapter filterCategoryAdapter;

    //
    ImageView plusCategory_IV;
    ImageView plusBrand_IV;
    ImageView back_IV;
    TextView search_TV;

    // recycler brand
    private RecyclerView recyclerBrand;
    FilterBrandAdapter filterBrandAdapter;

    String selectedCategoryIdList;
    String selectedBrandIdList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);

        initViews();
    }

    private void initViews() {
        back_IV = findViewById(R.id.back_filterActivity);

        plusBrand_IV = findViewById(R.id.plusBrand_filterActivity);
        plusCategory_IV = findViewById(R.id.plusCategory_filterActivity);

        recyclerCategory = findViewById(R.id.recyclerCategory_filterActivity);
        recyclerCategory.setHasFixedSize(true);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

        recyclerBrand = findViewById(R.id.recyclerBrand_filterActivity);
        recyclerBrand.setHasFixedSize(true);
        recyclerBrand.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

        search_TV = findViewById(R.id.search_filterActivity);

        myBackPressed();
        myPlusCategoryPressed();
        myPlusBrandPressed();
        setBrandRecycler();
        mySearchPressed();
        setCategoryRecycler();

    }

    private void mySearchPressed() {
        search_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    selectedCategoryIdList = filterCategoryAdapter.getSelectedCategoryIdList();

                    Log.d("TAG", "search category: " + selectedCategoryIdList);

                    selectedBrandIdList = filterBrandAdapter.getSelectedBrandIdList();
                    Log.d("TAG", "search Brand: " + selectedBrandIdList);

                Intent intent = new Intent(FilterActivity.this, AllNewProductActivity.class);
                intent.putExtra(KEY.SelectedCategory, selectedCategoryIdList);
                intent.putExtra(KEY.SelectedBrand, selectedBrandIdList);
                setResult(Activity.RESULT_OK,intent);
                finish();

                }
        });



    }

    private void myPlusBrandPressed() {
        plusBrand_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerBrand.getVisibility() == View.GONE) {
                    recyclerBrand.setVisibility(View.VISIBLE);
                    plusBrand_IV.setImageResource(R.drawable.clear);
                    //                    setBrandRecycler();
                }
                else {
                    recyclerBrand.setVisibility(View.GONE);
                    plusBrand_IV.setImageResource(R.drawable.add);
                }
                TransitionManager.beginDelayedTransition(recyclerBrand, new AutoTransition());
            }
        });
    }

    private void setBrandRecycler() {

        apiInterface.callBrand().enqueue(new Callback<List<BrandModel>>() {
            @Override
            public void onResponse(Call<List<BrandModel>> call, Response<List<BrandModel>> response) {
                filterBrandAdapter = new FilterBrandAdapter(getApplicationContext(), response.body());
                recyclerBrand.setAdapter(filterBrandAdapter);
                filterBrandAdapter.notifyDataSetChanged();
                Log.d("TAG", "onResponse: brand");
            }

            @Override
            public void onFailure(Call<List<BrandModel>> call, Throwable t) {
                Log.d("TAG", "onFailure: brand");

            }
        });

    }

    private void myPlusCategoryPressed() {

        plusCategory_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerCategory.getVisibility() == View.GONE) {
                    recyclerCategory.setVisibility(View.VISIBLE);
                    plusCategory_IV.setImageResource(R.drawable.clear);
                    //                    setCategoryRecycler();
                }
                else {
                    recyclerCategory.setVisibility(View.GONE);
                    plusCategory_IV.setImageResource(R.drawable.add);
                }
                TransitionManager.beginDelayedTransition(recyclerCategory, new AutoTransition());
            }
        });

    }

    private void setCategoryRecycler() {
        apiInterface.callAllCategoryFilter().enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                filterCategoryAdapter = new FilterCategoryAdapter(getApplicationContext(), response.body());
                recyclerCategory.setAdapter(filterCategoryAdapter);
                filterCategoryAdapter.notifyDataSetChanged();
                Log.d("TAG", "onResponse: category");
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                Log.d("TAG", "onFailure: category");
            }
        });
    }

    private void myBackPressed() {
        back_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCategoryIdList = null;
                selectedBrandIdList = null;
                onBackPressed();
            }
        });
    }



}
