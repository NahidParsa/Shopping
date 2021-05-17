package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Adapter.AllNewProductAdapter;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.Item0AmazingOfferModel;
import com.nadi.shopping.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllNewProductActivity extends AppCompatActivity {

    RecyclerView recyclerAllProduct;
    AllNewProductAdapter allNewProductAdapter;

    // api
    ApiInterface apiInterface;

    // general view
    LinearLayout sort_LL;
    LinearLayout filter_LL;

    //
    RadioButton newest_RB;
    RadioButton lowToHigh_RB;
    RadioButton highToLow_RB;
    RadioButton popular_RB;
    RadioButton bestselling_RB;

    List<Item0AmazingOfferModel> data = new ArrayList<>();

    //
    BottomSheetDialog bottomSheetDialog;

    public static int SET_CHECKOUT_DEFAULT = 1;
    public static final int SET_CHECKOUT_NEWEST = 2;
    public static final int SET_CHECKOUT_POPULAR = 3;
    public static final int SET_CHECKOUT_LOW_TO_HIGH = 4;
    public static final int SET_CHECKOUT_HIGH_TO_LOW = 5;
    public static final int SET_CHECKOUT_BESTSELLING = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_new_product);

        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);

        initGeneralView();



    }

    private void initGeneralView() {
        recyclerAllProduct = findViewById(R.id.recycler_allNewProductActivity);
        recyclerAllProduct.setHasFixedSize(true);
        recyclerAllProduct.setLayoutManager(new LinearLayoutManager(AllNewProductActivity.this, RecyclerView.VERTICAL, false));
        setRecyclerAllNewProduct();

        sort_LL = findViewById(R.id.LLSort_allNewProductActivity);
        filter_LL = findViewById(R.id.LLFilter_allNewProductActivity);



        mySortClicked();

    }

    private void mySortClicked() {

        sort_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog = new BottomSheetDialog(AllNewProductActivity.this);

                View view = LayoutInflater.from(AllNewProductActivity.this)
                        .inflate(R.layout.sort_bottom_sheet,findViewById(R.id.parent_sortBottomSheet));

                newest_RB = view.findViewById(R.id.newest_sortBottomSheet);
                lowToHigh_RB = view.findViewById(R.id.priceLowToHigh_sortBottomSheet);
                highToLow_RB = view.findViewById(R.id.priceHighToLow_sortBottomSheet);
                popular_RB = view.findViewById(R.id.MostPopular_sortBottomSheet);
                bestselling_RB = view.findViewById(R.id.Bestselling_sortBottomSheet);

//                bottomSheetDialog.setCancelable(true);

                switch (SET_CHECKOUT_DEFAULT){

                    case SET_CHECKOUT_BESTSELLING:
                        bestselling_RB.setChecked(true);
                        break;
                    case SET_CHECKOUT_HIGH_TO_LOW :
                        highToLow_RB.setChecked(true);
                        break;
                    case SET_CHECKOUT_LOW_TO_HIGH:
                        lowToHigh_RB.setChecked(true);
                        break;
                    case SET_CHECKOUT_POPULAR :
                        popular_RB.setChecked(true);
                        break;
                    case SET_CHECKOUT_NEWEST :
                        newest_RB.setChecked(true);
                        break;

                }


                onMyRadioBottomClicked();

                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();


            }
        });


    }

    private void onMyRadioBottomClicked() {

        myNewestClicked();
        myLowestClicked();
        myHighestClicked();
        myPopularClicked();
        myBestsellingClicked();

    }
    private void myBestsellingClicked() {

//        data.clear();
        bestselling_RB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apiInterface.callGetBestsellingProduct().enqueue(new Callback<List<Item0AmazingOfferModel>>() {
                    @Override
                    public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {
                        data = response.body();
                        allNewProductAdapter = new AllNewProductAdapter(data, AllNewProductActivity.this);
                        recyclerAllProduct.setAdapter(allNewProductAdapter);
                        allNewProductAdapter.notifyDataSetChanged();
                        Log.d("TAG", "onResponse: bestselling");
                    }

                    @Override
                    public void onFailure(Call<List<Item0AmazingOfferModel>> call, Throwable t) {
                        Log.d("TAG", "onFailure: bestselling");
                    }
                });


            }
        });
        bottomSheetDialog.dismiss();
        SET_CHECKOUT_DEFAULT = SET_CHECKOUT_BESTSELLING;
    }

    private void myPopularClicked() {
//        data.clear();

        popular_RB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface.callGetPopularProduct().enqueue(new Callback<List<Item0AmazingOfferModel>>() {
                    @Override
                    public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {
                        data = response.body();
                        allNewProductAdapter = new AllNewProductAdapter(data, AllNewProductActivity.this);
                        recyclerAllProduct.setAdapter(allNewProductAdapter);
                        allNewProductAdapter.notifyDataSetChanged();
                        Log.d("TAG", "onResponse: popular");

                    }

                    @Override
                    public void onFailure(Call<List<Item0AmazingOfferModel>> call, Throwable t) {
                        Log.d("TAG", "onResponse: popular");
                    }
                });
            }
        });
        bottomSheetDialog.dismiss();
        SET_CHECKOUT_DEFAULT = SET_CHECKOUT_POPULAR;
    }

    private void myHighestClicked() {
//        data.clear();

        highToLow_RB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface.callGetHighToLowProduct().enqueue(new Callback<List<Item0AmazingOfferModel>>() {
                    @Override
                    public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {
                        data = response.body();
                        allNewProductAdapter = new AllNewProductAdapter(data, AllNewProductActivity.this);
                        recyclerAllProduct.setAdapter(allNewProductAdapter);
                        allNewProductAdapter.notifyDataSetChanged();
                        Log.d("TAG", "onResponse: high to low");
                    }

                    @Override
                    public void onFailure(Call<List<Item0AmazingOfferModel>> call, Throwable t) {
                        Log.d("TAG", "onResponse: high to low");
                    }
                });
            }
        });
        bottomSheetDialog.dismiss();
        SET_CHECKOUT_DEFAULT = SET_CHECKOUT_HIGH_TO_LOW;
    }

    private void myLowestClicked() {
//        data.clear();
        lowToHigh_RB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface.callGetLowToHighProduct().enqueue(new Callback<List<Item0AmazingOfferModel>>() {
                    @Override
                    public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {
                        data = response.body();
                        allNewProductAdapter = new AllNewProductAdapter(data , AllNewProductActivity.this);
                        recyclerAllProduct.setAdapter(allNewProductAdapter);
                        allNewProductAdapter.notifyDataSetChanged();
                        Log.d("TAG", "onResponse: low to high");
                    }

                    @Override
                    public void onFailure(Call<List<Item0AmazingOfferModel>> call, Throwable t) {
                        Log.d("TAG", "onResponse: low to high");
                    }
                });
            }
        });
        bottomSheetDialog.dismiss();
        SET_CHECKOUT_DEFAULT = SET_CHECKOUT_LOW_TO_HIGH;
    }

    private void myNewestClicked() {
//        data.clear();
        newest_RB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apiInterface.callGetNewestProduct().enqueue(new Callback<List<Item0AmazingOfferModel>>() {
                @Override
                public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {
                    data = response.body();
                    allNewProductAdapter = new AllNewProductAdapter(data, AllNewProductActivity.this);
                    recyclerAllProduct.setAdapter(allNewProductAdapter);
                    allNewProductAdapter.notifyDataSetChanged();
                    Log.d("TAG", "onResponse: newest");
                }

                @Override
                public void onFailure(Call<List<Item0AmazingOfferModel>> call, Throwable t) {
                    Log.d("TAG", "onFailure: newest");
                }
            });
            }
        });
        bottomSheetDialog.dismiss();
        SET_CHECKOUT_DEFAULT = SET_CHECKOUT_NEWEST;
    }

    private void setRecyclerAllNewProduct() {

        apiInterface.callGetAllProduct().enqueue(new Callback<List<Item0AmazingOfferModel>>() {
            @Override
            public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {
                data = response.body();
                allNewProductAdapter = new AllNewProductAdapter(data, AllNewProductActivity.this);
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
