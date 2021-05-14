package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Adapter.AllNewProductAdapter;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.Item0AmazingOfferModel;
import com.nadi.shopping.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllNewProductActivity extends AppCompatActivity {

    RecyclerView recyclerAllProduct;
    AllNewProductAdapter allNewProductAdapter;

    // api
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_new_product);

        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);
        
        setRecyclerAllNewProduct();

    }

    private void setRecyclerAllNewProduct() {
        recyclerAllProduct = findViewById(R.id.recycler_allNewProductActivity);
        recyclerAllProduct.setHasFixedSize(true);
        recyclerAllProduct.setLayoutManager(new LinearLayoutManager(AllNewProductActivity.this, RecyclerView.VERTICAL, false));
        
        apiInterface.callGetAllProduct().enqueue(new Callback<List<Item0AmazingOfferModel>>() {
            @Override
            public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {
                allNewProductAdapter = new AllNewProductAdapter(response.body(), AllNewProductActivity.this);
                recyclerAllProduct.setAdapter(allNewProductAdapter);
                allNewProductAdapter.notifyDataSetChanged();
                Log.d("TAG", "onResponse: all new product");
            }

            @Override
            public void onFailure(Call<List<Item0AmazingOfferModel>> call, Throwable t) {

                Log.d("TAG", "onFailure: new product");
            }
        });
        
    }
}
