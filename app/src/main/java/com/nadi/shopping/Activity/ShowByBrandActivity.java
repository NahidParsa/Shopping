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
import com.nadi.shopping.Adapter.AllNewProductAdapter;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.CommentsModel;
import com.nadi.shopping.Model.Item0AmazingOfferModel;
import com.nadi.shopping.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowByBrandActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    RecyclerView recyclerShowByBrand;
    AllNewProductAdapter adapterAllNewProduct;

    Bundle bundle;
    String name;

    TextView brand_TV;
    ImageView back_IV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_by_brand);

        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);

        generalView();

    }

    private void generalView() {
        bundle = getIntent().getExtras();
        name = bundle.getString(KEY.name);

        back_IV = findViewById(R.id.back_showByBrand);
        brand_TV = findViewById(R.id.title_showByBrand);
        brand_TV.setText(name);

        myBackPressed();
        setRecycler(name);

    }

    private void myBackPressed() {
        back_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void setRecycler(String name) {

        recyclerShowByBrand = findViewById(R.id.recycler_showByBrand);
        recyclerShowByBrand.setHasFixedSize(true);
        recyclerShowByBrand.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false ));


        apiInterface.callProductByBrand(name).enqueue(new Callback<List<Item0AmazingOfferModel>>() {
            @Override
            public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {

                adapterAllNewProduct = new AllNewProductAdapter(response.body(), getApplicationContext());
                recyclerShowByBrand.setAdapter(adapterAllNewProduct);
                adapterAllNewProduct.notifyDataSetChanged();

                Log.d("TAG", "onResponse: show by brand");

            }

            @Override
            public void onFailure(Call<List<Item0AmazingOfferModel>> call, Throwable t) {
                Log.d("TAG", "onFailure: show by brand");
            }
        });

    }
}
