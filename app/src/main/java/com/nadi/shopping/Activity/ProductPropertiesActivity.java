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
import com.nadi.shopping.Adapter.AdapterProductOption;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.OptionProductModel;
import com.nadi.shopping.R;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPropertiesActivity extends AppCompatActivity {

    // bundle
    Bundle bundle;
    String id;
    String title;

    // general views
    TextView title_TV;
    ImageView back_IV;

    // api
    ApiInterface apiInterface;

    // recycler properties
    RecyclerView recyclerViewProperties;
    List<OptionProductModel> propertiesModelList = new ArrayList<>();
    AdapterProductOption adapterProductOption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_properties);

        initBundle();
    }

    private void initBundle() {

        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);

        title_TV = findViewById(R.id.titleProduct_productPropertiesActivity);
        back_IV = findViewById(R.id.back_productPropertiesActivity);

        bundle = getIntent().getExtras();
        id = bundle.getString(KEY.id);
        title = bundle.getString(KEY.title);

        title_TV.setText(title);

        myBackPressed();

        initRecyclerProperties(id);

    }

    private void initRecyclerProperties(String id) {

        recyclerViewProperties = findViewById(R.id.recyclerItemProduct_productPropertiesActivity);
        recyclerViewProperties.setHasFixedSize(true);
        recyclerViewProperties.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));

        apiInterface.postIdGetProductProperties(id).enqueue(new Callback<List<OptionProductModel>>() {
            @Override
            public void onResponse(Call<List<OptionProductModel>> call, Response<List<OptionProductModel>> response) {
                propertiesModelList = response.body();
                adapterProductOption = new AdapterProductOption(getApplicationContext(), propertiesModelList);
                recyclerViewProperties.setAdapter(adapterProductOption);
                adapterProductOption.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OptionProductModel>> call, Throwable t) {

                Log.d("TAG", "onFailure: properties");
            }
        });
    }

    private void myBackPressed() {

        back_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
