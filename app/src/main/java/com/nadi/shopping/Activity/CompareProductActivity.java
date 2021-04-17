package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Adapter.AdapterCompareProduct;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.Item0AmazingOfferModel;
import com.nadi.shopping.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompareProductActivity extends AppCompatActivity {

    //Api
    ApiInterface apiInterface;

    // bundle
    Bundle bundle;
    String categoryId;
    String id;

    // recycler compare
    RecyclerView recyclerViewCompare;
    AdapterCompareProduct adapterCompareProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_product);

        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);

        initBundle();

    }

    private void initBundle() {

        bundle = getIntent().getExtras();
        categoryId = bundle.getString(KEY.CategoryId);
        id = bundle.getString(KEY.id);

        initRecyclerCompare(categoryId, id);

    }

    private void initRecyclerCompare(String categoryId, String id) {
        recyclerViewCompare = findViewById(R.id.recyclerComparable_compareProductActivity);
        recyclerViewCompare.setHasFixedSize(true);
        recyclerViewCompare.setLayoutManager(new LinearLayoutManager(CompareProductActivity.this, RecyclerView.VERTICAL , false));

        apiInterface.postCategoryIdGetComparableProduct(categoryId, id).enqueue(new Callback<List<Item0AmazingOfferModel>>() {
            @Override
            public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {
                adapterCompareProduct = new AdapterCompareProduct(CompareProductActivity.this, response.body());
                recyclerViewCompare.setAdapter(adapterCompareProduct);
                adapterCompareProduct.notifyDataSetChanged();
                Log.d("compare", "onResponse: compare");
            }

            @Override
            public void onFailure(Call<List<Item0AmazingOfferModel>> call, Throwable t) {
                Log.d("compare", "onFailure: compare");
            }
        });

    }
}
