package com.nadi.shopping.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Activity.AllNewProductActivity;
import com.nadi.shopping.Adapter.AmazingOfferAdapter;
import com.nadi.shopping.Adapter.BrandAdapter;
import com.nadi.shopping.Adapter.CategoryAdapter;
import com.nadi.shopping.Adapter.ConcatAdapter0SpecialProduct;
import com.nadi.shopping.Adapter.ConcatAdapter1Wall;
import com.nadi.shopping.Adapter.FakeAdapterAmazing;
import com.nadi.shopping.Adapter.FakeAdapterWall;
import com.nadi.shopping.Adapter.MiddleViewPagerAdapter;
import com.nadi.shopping.Adapter.NewProductsAdapter;
import com.nadi.shopping.Adapter.PagerClassAdapter;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.AmazingOfferModel;
import com.nadi.shopping.Model.BrandModel;
import com.nadi.shopping.Model.CategoryModel;
import com.nadi.shopping.Model.Item0AmazingOfferModel;
import com.nadi.shopping.Model.Item1WallAmazingOfferModel;
import com.nadi.shopping.Model.NewProductsModel;
import com.nadi.shopping.Model.PagerModel;
import com.nadi.shopping.Model.TitleModel;
import com.nadi.shopping.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    View view;
    // view pager header
    RtlViewPager viewPager_VP;
    TabLayout tabLayout_TL;
    List<PagerModel> listPagerModel = new ArrayList<>();
    PagerClassAdapter pagerAdapter;

    //api
    ApiInterface apiInterface;
    RequestQueue requestQueueVolley;

    // recycler category
    List<CategoryModel> categoryModelList = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    RecyclerView recyclerViewCategory;

//    // Amazing Offer
    List<AmazingOfferModel> amazingOfferModelList = new ArrayList<>();
    RecyclerView recyclerViewAmazingOffer;
    AmazingOfferAdapter amazingOfferAdapter;

///// fake adapter
//    List<Item0AmazingOfferModel> item0model = new ArrayList<>();
//    RecyclerView recyclerViewAmazingOffer;
//    FakeAdapterAmazing fakeAdapterAmazing;
/// fake adapter wall
//    List<Item1WallAmazingOfferModel> item1model = new ArrayList<>();
//    RecyclerView recyclerViewWall;
//    FakeAdapterWall fakeAdapterWall;


//// view pager Middle
    List<PagerModel> pagerModelMiddleList =new ArrayList<>();
    RecyclerView recyclerViewMiddleViewPager;
    MiddleViewPagerAdapter middleViewPagerAdapter;

  /////Title
    TextView newSpecialProduct_TV ;
     List<TitleModel> titleModelList = new ArrayList<>();

     ///////new product

    TextView moreNewProducts_TV;
    RecyclerView recyclerViewNewProduct;
    List<NewProductsModel> newProductsModelList = new ArrayList<>();
    NewProductsAdapter newProductsAdapter;
// brand
    RecyclerView recyclerViewBrand;
    List<BrandModel> brandModelList = new ArrayList<>();
    BrandAdapter brandAdapter;




    // special product
    RecyclerView recyclerViewSpecialProduct;
    ConcatAdapter0SpecialProduct concatAdapter0SpecialProduct;
    ConcatAdapter1Wall concatAdapter1Wall;
    ConcatAdapter concatAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {

        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);
        requestQueueVolley = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

        setRecyclerViewCategory(view);

        setViewPagerHeader(view);
        setAmazingOffer(view);

//        setFakeAmazingOffer(view);
//        setFakeWall(view);

        setMiddleViewPager(view);

        setTitle(view);

        setNewProduct(view);

        setBrand(view);

        setSpecialProduct(view);

        onMoreNewProductsClicked(view);

    }

    private void onMoreNewProductsClicked(View view) {
        moreNewProducts_TV = view.findViewById(R.id.moreNewProducts_HomeFragment);

        moreNewProducts_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext() , AllNewProductActivity.class));
            }
        });

    }

    private void setSpecialProduct(View view) {

        recyclerViewSpecialProduct = view.findViewById(R.id.recyclerViewNewSpecialProduct_homeFragment);
        recyclerViewSpecialProduct.setHasFixedSize(true);
        recyclerViewSpecialProduct.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));


        ConcatAdapter concatenated = new ConcatAdapter();
  //

        apiInterface.callItem0AmazingOffer().enqueue(new Callback<List<Item0AmazingOfferModel>>() {
            @Override
            public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {
                concatAdapter0SpecialProduct = new ConcatAdapter0SpecialProduct(getContext(), response.body());
                concatenated.addAdapter(concatAdapter0SpecialProduct);

            }

            @Override
            public void onFailure(Call<List<Item0AmazingOfferModel>> call, Throwable t) {
                Log.d("TAG", "onFailure: concat amazing");
            }
        });

        apiInterface.callItem1wallAmazingOffer().enqueue(new Callback<List<Item1WallAmazingOfferModel>>() {
            @Override
            public void onResponse(Call<List<Item1WallAmazingOfferModel>> call, Response<List<Item1WallAmazingOfferModel>> response) {
                concatAdapter1Wall = new ConcatAdapter1Wall(getContext(), response.body());
                concatenated.addAdapter(concatAdapter1Wall);
            }

            @Override
            public void onFailure(Call<List<Item1WallAmazingOfferModel>> call, Throwable t) {
                Log.d("TAG", "onFailure: concat wall");

            }
        });

//        ConcatAdapter concatenated = new ConcatAdapter(concatAdapter1Wall, concatAdapter0SpecialProduct);
        recyclerViewSpecialProduct.setAdapter(concatenated);
        concatenated.notifyDataSetChanged();
    }

    private void setBrand(View view) {
        recyclerViewBrand = view.findViewById(R.id.recyclerViewBrand_homeFragment);
        recyclerViewBrand.setHasFixedSize(true);
        recyclerViewBrand.setLayoutManager(new GridLayoutManager(getContext(), 3));

        apiInterface.callBrand().enqueue(new Callback<List<BrandModel>>() {
            @Override
            public void onResponse(Call<List<BrandModel>> call, Response<List<BrandModel>> response) {
                brandModelList = response.body();
                brandAdapter = new BrandAdapter(getContext(), brandModelList);
                recyclerViewBrand.setAdapter(brandAdapter);
                brandAdapter.notifyDataSetChanged();
                Log.d("TAG", "on Response: brand");
            }

            @Override
            public void onFailure(Call<List<BrandModel>> call, Throwable t) {

                Log.d("TAG", "onFailure: brand");
            }
        });
    }

    private void setNewProduct(View view) {

        recyclerViewNewProduct = view.findViewById(R.id.recyclerViewNewProduct_homeFragment);
        recyclerViewNewProduct.setHasFixedSize(true);
        recyclerViewNewProduct.setLayoutManager(new GridLayoutManager(getContext(),3));

        apiInterface.callNewProduct().enqueue(new Callback<List<NewProductsModel>>() {
            @Override
            public void onResponse(Call<List<NewProductsModel>> call, Response<List<NewProductsModel>> response) {
                newProductsModelList = response.body();
                newProductsAdapter = new NewProductsAdapter(getContext(), newProductsModelList);
                recyclerViewNewProduct.setAdapter(newProductsAdapter);
                newProductsAdapter.notifyDataSetChanged();
                Log.d("TAG", "on response: new product");

            }

            @Override
            public void onFailure(Call<List<NewProductsModel>> call, Throwable t) {
                Log.d("TAG", "onFailure: new product");
            }
        });


    }


    // set title from server
    private void setTitle(View view) {

        newSpecialProduct_TV = view.findViewById(R.id.txtNewSpecialTitle_HomeFragment);

        apiInterface.callTitleModelNewSpecialProduct().enqueue(new Callback<List<TitleModel>>() {
            @Override
            public void onResponse(Call<List<TitleModel>> call, Response<List<TitleModel>> response) {
                if (response.isSuccessful()) {

                    newSpecialProduct_TV.setText(response.body().get(0).getTitle_text());
                     Log.d("title", "title: " +response.body().get(0).getTitle_text());

                }
                Log.d("title", "is not successful: " +response.body().get(0).getTitle_text());
                }


            @Override
            public void onFailure(Call<List<TitleModel>> call, Throwable t) {
                Log.d("title", "failure: " +t.getMessage());

            }
        });


//      apiInterface.callTitleModelNewSpecialProduct().enqueue(new Callback<TitleModel>() {
//          @Override
//          public void onResponse(Call<TitleModel> call, Response<TitleModel> response) {
//
//              if (response.isSuccessful()) {
//                  newSpecialProduct_TV.setText(response.body().getTitle_text());
//
//                  Log.d("title", "title: " +response.body().getTitle_text());
//              }
//              Log.d("title", "title: is not " +response.body().getTitle_text());
//
//          }
//
//          @Override
//          public void onFailure(Call<TitleModel> call, Throwable t) {
//              Log.d("title", "title: error " + t.getMessage());
//          }
//      });

    }

    private void setMiddleViewPager(View view) {

        recyclerViewMiddleViewPager = view.findViewById(R.id.recyclerViewMiddleViewPager_homeFragment);
        recyclerViewMiddleViewPager.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewMiddleViewPager.setHasFixedSize(true);

        apiInterface.callPagerModelMiddle().enqueue(new Callback<List<PagerModel>>() {
            @Override
            public void onResponse(Call<List<PagerModel>> call, Response<List<PagerModel>> response) {
                pagerModelMiddleList = response.body();

                middleViewPagerAdapter = new MiddleViewPagerAdapter(pagerModelMiddleList, getContext());
                recyclerViewMiddleViewPager.setAdapter(middleViewPagerAdapter);
                middleViewPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PagerModel>> call, Throwable t) {

                Toast.makeText(getContext(), "something went wrong Middle Banner ", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure Middle Banner: "+ t.getMessage());

            }
        });



    }



//    private void setFakeWall(View view) {
//         recyclerViewWall = view.findViewById(R.id.recyclerViewAmazingOffers_homeFragment);
//         recyclerViewWall.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//         recyclerViewWall.setHasFixedSize(true);
//
//          apiInterface.callItem1wallAmazingOffer().enqueue(new Callback<List<Item1WallAmazingOfferModel>>() {
//              @Override
//              public void onResponse(Call<List<Item1WallAmazingOfferModel>> call, Response<List<Item1WallAmazingOfferModel>> response) {
//                  fakeAdapterWall = new FakeAdapterWall(response.body(), getContext());
//                  recyclerViewWall.setAdapter(fakeAdapterWall);
//                  fakeAdapterWall.notifyDataSetChanged();
//              }
//
//              @Override
//              public void onFailure(Call<List<Item1WallAmazingOfferModel>> call, Throwable t) {
//
//              }
//          });
//      }




//    private void setFakeAmazingOffer(View view) {
//       recyclerViewAmazingOffer = view.findViewById(R.id.recyclerViewAmazingOffers_homeFragment);
//       recyclerViewAmazingOffer.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//       recyclerViewAmazingOffer.setHasFixedSize(true);
//
//        apiInterface.callItem0AmazingOffer().enqueue(new Callback<List<Item0AmazingOfferModel>>() {
//               @Override
//               public void onResponse(Call<List<Item0AmazingOfferModel>> call, Response<List<Item0AmazingOfferModel>> response) {
//                    item0model = response.body();
//                   fakeAdapterAmazing = new FakeAdapterAmazing(item0model, getContext());
//                   recyclerViewAmazingOffer.setAdapter(fakeAdapterAmazing);
//                    fakeAdapterAmazing.notifyDataSetChanged();
//               }
//
//               @Override
//               public void onFailure(Call<List<Item0AmazingOfferModel>> call, Throwable t) {
//                   Toast.makeText(getContext(), "something went wrong Amazing Offer ", Toast.LENGTH_SHORT).show();
//                   Log.d("TAG", "onFailure Amazing Offer: "+ t.getMessage());
//
//               }
//           });
//
//    }

////////////////////////


    private void setAmazingOffer(View view) {

        recyclerViewAmazingOffer = view.findViewById(R.id.recyclerViewAmazingOffers_homeFragment);
        recyclerViewAmazingOffer.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerViewAmazingOffer.setHasFixedSize(true);


        com.android.volley.Response.Listener<JSONArray> listenerWall =
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Gson gson = new Gson();
                        Item1WallAmazingOfferModel[] item1WallAmazingOfferModelArray=
                                gson.fromJson(response.toString(), Item1WallAmazingOfferModel[].class);

                        for (int i = 0 ; i< item1WallAmazingOfferModelArray.length ; i++){

                            amazingOfferModelList.add(new AmazingOfferModel( 1 , item1WallAmazingOfferModelArray[i]));
//                            amazingOfferAdapter.notifyDataSetChanged(); //dont use data set changed because it may make a bug

                        }
                    }
                };
        com.android.volley.Response.ErrorListener errorListenerWall =
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), error.getMessage()+"", Toast.LENGTH_SHORT).show();
                        Log.d("volley", "onErrorResponse: volley get Wall");
                    }
                };

        amazingOfferAdapter = new AmazingOfferAdapter(amazingOfferModelList, getContext());
               recyclerViewAmazingOffer.setAdapter(amazingOfferAdapter);


        com.android.volley.Response.Listener<JSONArray> listenerProduct =
                   new com.android.volley.Response.Listener<JSONArray>() {
                       @Override
                       public void onResponse(JSONArray response) {

                           Gson gson = new Gson();
                           Item0AmazingOfferModel[] item0AmazingOfferModelsArray=
                                   gson.fromJson(response.toString(), Item0AmazingOfferModel[].class);

                           for (int i = 0 ; i< item0AmazingOfferModelsArray.length ; i++){

                               amazingOfferModelList.add(new AmazingOfferModel( 0 , item0AmazingOfferModelsArray[i]));
                               amazingOfferAdapter.notifyDataSetChanged();

                           }
                       }
                   };
           com.android.volley.Response.ErrorListener errorListenerProduct =
                   new com.android.volley.Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {

                           Toast.makeText(getContext(), error.getMessage()+"", Toast.LENGTH_SHORT).show();
                           Log.d("volley", "onErrorResponse: volley get Wall");
                       }
                   };



        JsonArrayRequest requestWall =
                new JsonArrayRequest(Request.Method.GET , Urls.WALL_AMAZING_VOLLEY, null , listenerWall, errorListenerWall);

        JsonArrayRequest requestProduct =
                 new JsonArrayRequest(Request.Method.GET , Urls.PRODUCT_AMAZING_VOLLEY, null , listenerProduct, errorListenerProduct);


        requestQueueVolley.add(requestWall);
        requestQueueVolley.add(requestProduct);


    }


//    private void setAmazingOffer(View view) {
//
//        recyclerViewAmazingOffer = view.findViewById(R.id.recyclerViewAmazingOffers_homeFragment);
//        recyclerViewAmazingOffer.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//        recyclerViewAmazingOffer.setHasFixedSize(true);

//        apiInterface.callItem1wallAmazingOffer().enqueue(new Callback<List<AmazingOfferModel>>() {
//            @Override
//            public void onResponse(Call<List<AmazingOfferModel>> call, Response<List<AmazingOfferModel>> response) {
//
//                Gson gson = new Gson();
//                Item1WallAmazingOfferModel[] item1WallAmazingOfferModels;
//                item1WallAmazingOfferModels = gson.fromJson(response.body().toString(), Item1WallAmazingOfferModel[].class);
//
//
//                for (int i = 0 ; i <item1WallAmazingOfferModels.length; i++){
//
//                    amazingOfferModelList.add(new AmazingOfferModel(1,  item1WallAmazingOfferModels[i]));
////                    recyclerViewAmazingOffer.setAdapter(amazingOfferAdapter);
//                    amazingOfferAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<AmazingOfferModel>> call, Throwable t) {
//                Toast.makeText(getContext(), "something went wrong wall amazing ", Toast.LENGTH_SHORT).show();
//                Log.d("TAG", "onFailure wall product amazing offer: ");
//
//            }
//        });

//        apiInterface.callItem0AmazingOffer().enqueue(new Callback<List<AmazingOfferModel>>() {
//            @Override
//            public void onResponse(Call<List<AmazingOfferModel>> call, Response<List<AmazingOfferModel>> response) {
//
//                Gson gson = new Gson();
//
//                Item0AmazingOfferModel[] item0AmazingOfferModels = new Item0AmazingOfferModel[0];
////                        =gson.fromJson(response.body(), Item0AmazingOfferModel[].class);
//
//
//
////                                Item0AmazingOfferModel[] item0AmazingOfferModels = new Item0AmazingOfferModel[0];
////
////                try {
////                    JSONObject jsonObject = new JSONObject(response.body().toString());
////
////                    for (int i = 0 ; i < jsonObject.length(); i++){
////
////                     amazingOfferModelList.add(new AmazingOfferModel(0,  item0AmazingOfferModels[i]));
////                     amazingOfferAdapter.notifyDataSetChanged();
////                          }
////
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
//
//                if (response.isSuccessful()){
//
//                for (int i = 0 ; i < response.body().size(); i++){
//
//                    amazingOfferModelList.add(new AmazingOfferModel(0,  item0AmazingOfferModels[i]));
////                    recyclerViewAmazingOffer.setAdapter(amazingOfferAdapter);
////
////                    amazingOfferAdapter.notifyDataSetChanged();
//                }
//                }
//                    recyclerViewAmazingOffer.setAdapter(amazingOfferAdapter);
//
//                    amazingOfferAdapter.notifyDataSetChanged();
//
//                }
//
//            @Override
//            public void onFailure(Call<List<AmazingOfferModel>> call, Throwable t) {
//                Toast.makeText(getContext(), "something went wrong item amazing ", Toast.LENGTH_SHORT).show();
//                Log.d("TAG", "onFailure item product amazing offer: ");
//
//            }
//        });
//        amazingOfferAdapter = new AmazingOfferAdapter(amazingOfferModelList, getContext());
//        recyclerViewAmazingOffer.setAdapter(amazingOfferAdapter);
//        amazingOfferAdapter.notifyDataSetChanged();

//    }



    private void setRecyclerViewCategory(View view) {

        recyclerViewCategory = view.findViewById(R.id.recyclerViewCategory_homeFragment);
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        apiInterface.callCategoryByLimit().enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
//                categoryModelList.clear();

                categoryModelList = response.body();

                categoryAdapter = new CategoryAdapter(getContext(), categoryModelList);
                recyclerViewCategory.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();
                Log.d("TAG", "onResponse: recycler"+ response.body().toString());
                Log.d("TAG", "onResponse: recycler size"+ categoryModelList.get(0));

            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                Toast.makeText(getContext(), "something went wrong Category ", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure category: "+ t.getMessage());
            }
        });


}

    private void setViewPagerHeader(View view) {

        viewPager_VP = view.findViewById(R.id.viewPager_homeFragment);
        tabLayout_TL = view.findViewById(R.id.tabLayout_homeFragment);
        makeViewPagerAnimate();

        apiInterface.callPagerModel().enqueue(new Callback<List<PagerModel>>() {
            @Override
            public void onResponse(Call<List<PagerModel>> call, Response<List<PagerModel>> response) {

                    listPagerModel = response.body();
                    pagerAdapter = new PagerClassAdapter(listPagerModel, getContext());
                    viewPager_VP.setAdapter(pagerAdapter);
                    tabLayout_TL.setupWithViewPager(viewPager_VP, true);
                Log.d("TAG", "onResponse: pager "+ response.body().toString());


            }

            @Override
            public void onFailure(Call<List<PagerModel>> call, Throwable t) {

                Toast.makeText(getContext(), "something went wrong View Pager Header", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure viewpager: "+ t.getMessage());

            }
        });


        // baese taghire harkate slider ha be sorat khod be khod mishavad
        makeViewpagerChangeSlider();
    }

    private void makeViewpagerChangeSlider() {

         final boolean running = true;


        Thread thread = new Thread(){

            @Override
            public void run() {
                super.run();
                while (running){

                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "something went wrong with thread", Toast.LENGTH_SHORT).show();
                    }

                    if (getActivity() == null)
                    return;

                 getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (viewPager_VP.getCurrentItem() < listPagerModel.size() - 1 ){

                                    viewPager_VP.setCurrentItem(viewPager_VP.getCurrentItem()+1);
                                }else{
                                    viewPager_VP.setCurrentItem(0);

                                }
                            }
                        });
             }

            }
        };
        thread.start();
    }

    private void makeViewPagerAnimate() {

        final int paddingPx = 120;
        final float MIN_SCALE = 0.8f;
        final float MAX_SCALE = 1f;

        viewPager_VP.setClipToPadding(false);
         viewPager_VP.setPadding(paddingPx, 0, paddingPx, 0);


        ViewPager.PageTransformer transformer = new ViewPager.PageTransformer() {
                @Override
                public void transformPage(View page, float position) {
                    float pagerWidthPx = ((ViewPager) page.getParent()).getWidth();
                    float pageWidthPx = pagerWidthPx - 2 * paddingPx;

                    float maxVisiblePages = pagerWidthPx / pageWidthPx;
                    float center = maxVisiblePages / 2f;

                    float scale;
                    if (position + 0.5f < center - 0.5f || position > center) {
                        scale = MIN_SCALE;
                    } else {
                        float coef;
                        if (position + 0.5f < center) {
                            coef = (position + 1 - center) / 0.5f;
                        } else {
                            coef = (center - position) / 0.5f;
                        }
                        scale = coef * (MAX_SCALE - MIN_SCALE) + MIN_SCALE;
                    }
                    page.setScaleX(scale);
                    page.setScaleY(scale);
                }
            };
     viewPager_VP.setPageTransformer(false, transformer);


    }
}
