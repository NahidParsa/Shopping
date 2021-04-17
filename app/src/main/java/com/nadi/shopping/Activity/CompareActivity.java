package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Adapter.AdapterProductOption;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.OptionProductModel;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompareActivity extends AppCompatActivity {

    ApiInterface apiInterface;

     String id_first;
     String title_first;
     String realPrice_first;
     String img_link_first;

     TextView realPriceFirst_TV;
     TextView titleFirst_TV;
     ImageView imgFirst_IV;

     RecyclerView recyclerViewFirst;
     AdapterProductOption adapterProductOptionFirst;

     Bundle bundle;

     String id_second;
     String title_second;
     String realPrice_second;
    String img_link_second;

    TextView realPriceSecond_TV;
    TextView titleSecond_TV;
    ImageView imgSecond_IV;

    RecyclerView recyclerViewSecond;
    AdapterProductOption adapterProductOptionSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);
        initGeneral();

    }

    private void initGeneral() {

        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        id_first = ShowDetailProductActivity.id;
        title_first = ShowDetailProductActivity.title;
        realPrice_first = ShowDetailProductActivity.realPrice;
        img_link_first = ShowDetailProductActivity.link_img;
        String decimalFormatRealPriceFirst = decimalFormat.format(Integer.valueOf(realPrice_first));

        initViewsFirst(id_first,title_first,decimalFormatRealPriceFirst,img_link_first);


        bundle = getIntent().getExtras();
        id_second = bundle.getString(KEY.id_second);
        title_second = bundle.getString(KEY.title_second);
        realPrice_second = bundle.getString(KEY.realPrice_second);
        img_link_second = bundle.getString(KEY.link_img_second);
        String decimalFormatRealPriceSecond = decimalFormat.format(Integer.valueOf(realPrice_second));

        initViewsSecond(id_second,title_second,decimalFormatRealPriceSecond,img_link_second);
    
        initRecyclerFirst(id_first);
        initRecyclerSecond(id_second);
    
    }

    private void initRecyclerSecond(String id_second) {

        recyclerViewSecond = findViewById(R.id.recyclerSecond_compareActivity);
        recyclerViewSecond.setHasFixedSize(true);
        recyclerViewSecond.setLayoutManager(new LinearLayoutManager(CompareActivity.this, RecyclerView.VERTICAL, false));

        apiInterface.postIdGetProductOption(id_second).enqueue(new Callback<List<OptionProductModel>>() {
            @Override
            public void onResponse(Call<List<OptionProductModel>> call, Response<List<OptionProductModel>> response) {

                adapterProductOptionSecond = new AdapterProductOption(CompareActivity.this, response.body());
                recyclerViewSecond.setAdapter(adapterProductOptionSecond);
                adapterProductOptionSecond.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OptionProductModel>> call, Throwable t) {

                Log.d("TAG", "onFailure: recycler second");
            }
        });
    }

    private void initRecyclerFirst(String id_first) {

         recyclerViewFirst = findViewById(R.id.recyclerFirst_compareActivity);
         recyclerViewFirst.setHasFixedSize(true);
         recyclerViewFirst.setLayoutManager(new LinearLayoutManager(CompareActivity.this, RecyclerView.VERTICAL, false));

         apiInterface.postIdGetProductOption(id_first).enqueue(new Callback<List<OptionProductModel>>() {
             @Override
             public void onResponse(Call<List<OptionProductModel>> call, Response<List<OptionProductModel>> response) {
                 adapterProductOptionFirst = new AdapterProductOption(CompareActivity.this, response.body());
                 recyclerViewFirst.setAdapter(adapterProductOptionFirst);
                 adapterProductOptionFirst.notifyDataSetChanged();
             }

             @Override
             public void onFailure(Call<List<OptionProductModel>> call, Throwable t) {

                 Log.d("TAG", "onFailure: recycler second");
             }
         });
    }

    private void initViewsSecond(String id_second, String title_second, String decimalFormatRealPriceSecond, String img_link_second) {

        realPriceSecond_TV =findViewById(R.id.realPriceSecond_compareActivity);
        titleSecond_TV =findViewById(R.id.titleSecond_compareActivity);
        imgSecond_IV =findViewById(R.id.imgSecond_compareActivity);

        realPriceSecond_TV.setText(decimalFormatRealPriceSecond);
        titleSecond_TV.setText(title_second);
        Picasso.get().load(img_link_second).into(imgSecond_IV);

    }

    private void initViewsFirst(String id_first, String title_first, String decimalFormatRealPriceFirst, String img_link_first) {

        realPriceFirst_TV =findViewById(R.id.realPriceFirst_compareActivity);
        titleFirst_TV =findViewById(R.id.titleFirst_compareActivity);
        imgFirst_IV =findViewById(R.id.imgFirst_compareActivity);

        realPriceFirst_TV.setText(decimalFormatRealPriceFirst);
        titleFirst_TV.setText(title_first);
        Picasso.get().load(img_link_first).into(imgFirst_IV);
    }
}
