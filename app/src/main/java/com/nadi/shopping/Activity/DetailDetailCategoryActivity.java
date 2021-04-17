package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Adapter.AdapterAllDetailDetailCategory;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.Item0AmazingOfferModel;
import com.nadi.shopping.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDetailCategoryActivity extends AppCompatActivity {


    Bundle bundle;
    String titleDetailCategory;
    String idDetailCategory;

// api
    ApiInterface apiInterface;

    // general
    TextView title_TV;
    ImageView back_IV;

    //
    RecyclerView recyclerViewDetail_detailDetailCategoryActivity;
    AdapterAllDetailDetailCategory adapterAllDetailDetailCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_detail_category);

        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);

        initViews();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initViews() {
        title_TV = findViewById(R.id.title_detailDetailCategoryActivity);
        back_IV = findViewById(R.id.back_detailDetailCategoryActivity);

        getMyBundle();
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

    private void getMyBundle() {
        bundle = getIntent().getExtras();
        titleDetailCategory = bundle.getString(KEY.titleDetailCategory);
        idDetailCategory = bundle.getString(KEY.idDetailCategory);

        title_TV.setText(titleDetailCategory);
        Toast.makeText(DetailDetailCategoryActivity.this, "this is id : " + idDetailCategory, Toast.LENGTH_SHORT).show();

        Log.d("nnn", "getMyBundle: " + idDetailCategory);

        setAllDetail(idDetailCategory);

    }

    private void setAllDetail(String idDetailCategory) {
        recyclerViewDetail_detailDetailCategoryActivity = findViewById(R.id.recyclerViewDetail_detailDetailCategoryActivity);
        recyclerViewDetail_detailDetailCategoryActivity.setHasFixedSize(true);
        recyclerViewDetail_detailDetailCategoryActivity.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        apiInterface.postIdGetDetailDetailCategory(idDetailCategory).enqueue(new Callback<List<Item0AmazingOfferModel>>() {
            @Override
            public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {
                adapterAllDetailDetailCategory = new AdapterAllDetailDetailCategory(getApplicationContext(), response.body());
                recyclerViewDetail_detailDetailCategoryActivity.setAdapter(adapterAllDetailDetailCategory);
                adapterAllDetailDetailCategory.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Item0AmazingOfferModel>> call, Throwable t) {

            }
        });


    }


}
