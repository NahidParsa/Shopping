package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Adapter.AdapterFAQ;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.FAQModel;
import com.nadi.shopping.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAQActivity extends AppCompatActivity {

    RecyclerView recyclerViewFaq;
    AdapterFAQ adapterFAQ;
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q);

        setApi();
        setRecyclerFaq();
    }

    private void setRecyclerFaq() {
        recyclerViewFaq = findViewById(R.id.recycler_faqActivity);
        recyclerViewFaq.setHasFixedSize(true);
        recyclerViewFaq.setLayoutManager(new LinearLayoutManager(FAQActivity.this, RecyclerView.VERTICAL,false));

        apiInterface.callAFQ().enqueue(new Callback<List<FAQModel>>() {
            @Override
            public void onResponse(Call<List<FAQModel>> call, Response<List<FAQModel>> response) {
                adapterFAQ = new AdapterFAQ(response.body(), FAQActivity.this);
                recyclerViewFaq.setAdapter(adapterFAQ);
                adapterFAQ.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FAQModel>> call, Throwable t) {

            }
        });


    }

    private void setApi() {
        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);
    }
}
