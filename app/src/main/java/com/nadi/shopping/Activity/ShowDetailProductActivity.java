package com.nadi.shopping.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Adapter.AdapterCommentsByLimit;
import com.nadi.shopping.Adapter.AdapterProductOption;
import com.nadi.shopping.Adapter.AdapterSimilarProduct;
import com.nadi.shopping.Adapter.PagerClassAdapter;
import com.nadi.shopping.BuildConfig;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.CommentsModel;
import com.nadi.shopping.Model.NewProductsModel;
import com.nadi.shopping.Model.OptionProductModel;
import com.nadi.shopping.Model.PagerModel;
import com.nadi.shopping.Other.MySharedPreferenceConfig;
import com.nadi.shopping.R;
import com.nadi.shopping.ROOM.FavoriteDatabase;
import com.nadi.shopping.ROOM.FavoriteEntityModel;
import com.nadi.shopping.ROOM.FavoriteRepository;
import com.nadi.shopping.ROOM.FavoriteRoomDBActivity;
import com.nadi.shopping.ROOM.ROOMDB;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.DELETE;

public class ShowDetailProductActivity extends AppCompatActivity {

   // api
    ApiInterface apiInterface;

    // bundle
    Bundle bundle;
    static String id;
    static String title;
    static String brand;
    static String categoryId;
    static String offPercentage;
    static String realPrice;
    static String offPrice;
    static String link_img;


    // general views
    TextView brandProduct_TV;
    TextView nameProduct_TV;
    ImageView shoppingCart_IV;
    ImageView favorite_IV;
    ImageView more_IV;
    TextView offPercentage_TV;
    TextView realPrice_TV;
    TextView offPrice_TV;
    TextView badgeNotification_TV;

    // pager
    ViewPager viewPager_VP;
    TabLayout tabLayout_TL;
    PagerClassAdapter pagerClassAdapter;
    List<PagerModel> pagerModelList = new ArrayList<>();

    // similar recycler view
    List<NewProductsModel> newProductsModelList = new ArrayList<>();
    AdapterSimilarProduct adapterSimilarProduct;
    RecyclerView recyclerViewSimilar;

    // recycler product option
    RecyclerView recyclerViewProductOption;
    List<OptionProductModel> optionProductModelList = new ArrayList<>();
    AdapterProductOption adapterProductOption;

    // relative layout
    RelativeLayout relativeProperties;
    RelativeLayout relativeProductPresentation;

    // recycler show comments
    TextView showAllComments_TV;
    RecyclerView recyclerViewCommentsByLimit;
    AdapterCommentsByLimit adapterCommentsByLimit;
    // room db
    public static ROOMDB roomdb;
    public static FavoriteRepository favoriteRepository;

    // baray to por ya khali bodan dar filter

//    public static  int IMG_FAVORITE = 1;
//    public static final int INSERT = 2;
//    public static final int DELETE = 1;

    int alreadyFavorite = 1 ;

    MySharedPreferenceConfig mySharedPreferenceConfig;
    String user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_product);

        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);
        mySharedPreferenceConfig = new MySharedPreferenceConfig(this);
        init();

    }

    private void init() {

        initGeneralView();

        getMyBundle();
    }

    private void getMyBundle() {
        bundle = getIntent().getExtras();
        id = bundle.getString(KEY.id);
        title = bundle.getString(KEY.title);
        brand = bundle.getString(KEY.brand);
        categoryId = bundle.getString(KEY.CategoryId);
        offPercentage = bundle.getString(KEY.offPercentage);
        realPrice = bundle.getString(KEY.realPrice);
        offPrice = bundle.getString(KEY.offPrice);
        /////////////
        link_img = bundle.getString(KEY.link_img);

        user_email = mySharedPreferenceConfig.writeUserInfoPref().get(MySharedPreferenceConfig.EMAIL);

        setGeneralInfo(id, title, brand);
        initViewPager(id);
        initSimilar(categoryId);
        initRelativeProperties(id,title);
        initRelativePresentation(id,title);

        initRecyclerOptionProduct(id);
        myMorePressed(id, title, categoryId);

        initBottomViews(offPercentage,realPrice,offPrice);

        initRecyclerShowCommentsByLimit(id);

        myAllCommentsShow(id, title, link_img);

        initRoomDataBase();

        myFavoriteClicked(id, title,brand, categoryId,offPercentage, realPrice,offPrice, link_img);

        myCartClicked(user_email, id);

    }

    private void myCartClicked(String user_email, String id) {

        shoppingCart_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int count = badgeNotification_N.get
                sendProductToMyCart(user_email, id);

            }
        });


    }

    private void sendProductToMyCart(String user_email, String id) {


    


    }

    private void initRoomDataBase() {
        roomdb = ROOMDB.getInstance(this);
        favoriteRepository = FavoriteRepository.getInstance(FavoriteDatabase.getInstance(roomdb.favoriteDao()));
    }


    private void myFavoriteClicked(String id, String title, String brand, String categoryId,
                                   String offPercentage, String realPrice, String offPrice,String link_img) {

//        myCheckFavorite();


        favorite_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TAG", " myFavoriteClicked : alreadyFavorite " + alreadyFavorite);

                switch (alreadyFavorite) {
                    case 1 : {

                        favorite_IV.setImageResource(R.drawable.favorite);
                        FavoriteEntityModel favoriteEntityModel = new FavoriteEntityModel();

                        favoriteEntityModel.setProduct_id(id);
                        favoriteEntityModel.setBrand(brand);
                        favoriteEntityModel.setCatogory_id(categoryId);
                        favoriteEntityModel.setOff_percentage(offPercentage);
                        favoriteEntityModel.setPrice(realPrice);
                        favoriteEntityModel.setDiscount_price(offPrice);
                        favoriteEntityModel.setName(title);
                        favoriteEntityModel.setLink_img(link_img);

//                        IMG_FAVORITE = INSERT;
                        favorite_IV.setImageResource(R.drawable.favorite);

                        favoriteRepository.insertFavorite(favoriteEntityModel);

                        alreadyFavorite = 2;

                        Intent intent = new Intent(ShowDetailProductActivity.this, FavoriteRoomDBActivity.class);
                        startActivity(intent);

                        break;
                    }

                case 2:{

                    favorite_IV.setImageResource(R.drawable.empty_favorite);

                    FavoriteEntityModel favoriteEntityModel = new FavoriteEntityModel();

                    favoriteEntityModel.setProduct_id(id);
                    favoriteEntityModel.setBrand(brand);
                    favoriteEntityModel.setCatogory_id(categoryId);
                    favoriteEntityModel.setOff_percentage(offPercentage);
                    favoriteEntityModel.setPrice(realPrice);
                    favoriteEntityModel.setDiscount_price(offPrice);
                    favoriteEntityModel.setName(title);
                    favoriteEntityModel.setLink_img(link_img);

//                    IMG_FAVORITE = DELETE;
                    favorite_IV.setImageResource(R.drawable.empty_favorite);

                    alreadyFavorite = 1;

//                    favoriteRepository.deleteFavorite(favoriteEntityModel);

                    favoriteRepository.deleteByproductId(id);
                    break;
                  }

                }
            }
        });

    }

//    private void myCheckFavorite() {
//
//        switch (IMG_FAVORITE) {
//            case INSERT:
//                favorite_IV.setImageResource(R.drawable.favorite);
//                break;
//            case DELETE:
//                favorite_IV.setImageResource(R.drawable.empty_favorite);
//                break;
//            default:
//                throw new IllegalStateException("unexpected value" + IMG_FAVORITE);
//        }
//    }

    private void myAllCommentsShow(String id, String title, String link_img) {

        showAllComments_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowDetailProductActivity.this , ShowCommentsActivity.class);
                intent.putExtra("ID_PRODUCT", id);
                intent.putExtra("TITLE", title);
                intent.putExtra("LINK_IMG", link_img);
                intent.putExtra("Email", link_img);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerShowCommentsByLimit(String id) {

        recyclerViewCommentsByLimit = findViewById(R.id.recyclerShowCommentByLimit_showDetailActivity);
        recyclerViewCommentsByLimit.setHasFixedSize(true);
        recyclerViewCommentsByLimit.setLayoutManager(new LinearLayoutManager(ShowDetailProductActivity.this, RecyclerView.HORIZONTAL, false));

        apiInterface.callCommentsPostIdProduct(id).enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(Call<List<CommentsModel>> call, Response<List<CommentsModel>> response) {
                adapterCommentsByLimit = new AdapterCommentsByLimit(response.body(), ShowDetailProductActivity.this);
                recyclerViewCommentsByLimit.setAdapter(adapterCommentsByLimit);
                adapterCommentsByLimit.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CommentsModel>> call, Throwable t) {

            }
        });

    }

    private void initBottomViews(String offPercentage, String realPrice, String offPrice) {

        offPercentage_TV = findViewById(R.id.offPercentage_showDetailProduct);
        offPrice_TV = findViewById(R.id.offPrice_showDetailProduct);
        realPrice_TV = findViewById(R.id.realPrice_showDetailProduct) ;

        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String decimalRealPrice = decimalFormat.format(Integer.valueOf(realPrice));
        String decimalOffPrice = decimalFormat.format(Integer.valueOf(offPrice));

        SpannableString spannableStringRealPrice = new SpannableString(decimalRealPrice);
        spannableStringRealPrice.setSpan(new StrikethroughSpan(), 0, realPrice.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        if (Integer.parseInt(offPercentage)  == 0) {

            offPercentage_TV.setVisibility(View.GONE);
            offPrice_TV.setVisibility(View.GONE);
            realPrice_TV.setText(decimalRealPrice + " $");

        }else if (Integer.parseInt(offPercentage) != 0){

            offPercentage_TV.setVisibility(View.VISIBLE);
            offPrice_TV.setVisibility(View.VISIBLE);

            offPercentage_TV.setText(offPercentage + " %");
            offPrice_TV.setText(decimalOffPrice+ " $");
            realPrice_TV.setText(spannableStringRealPrice + " $");

        }
    }

    private void initRecyclerOptionProduct(String id) {

        recyclerViewProductOption = findViewById(R.id.productOptionsRecyclerView_showDetailActivity);
        recyclerViewProductOption.setHasFixedSize(true);
        recyclerViewProductOption.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

        apiInterface.postIdGetProductOption(id).enqueue(new Callback<List<OptionProductModel>>() {
            @Override
            public void onResponse(Call<List<OptionProductModel>> call, Response<List<OptionProductModel>> response) {
                optionProductModelList = response.body();
                adapterProductOption = new AdapterProductOption(getApplicationContext(), optionProductModelList);
                recyclerViewProductOption.setAdapter(adapterProductOption);
                adapterProductOption.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OptionProductModel>> call, Throwable t) {

            }
        });
    }

    private void initRelativePresentation(String id, String title) {
    relativeProductPresentation = findViewById(R.id.relativeProductPresentation_showDetailActivity);
    relativeProductPresentation.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

           Intent intent = new Intent(ShowDetailProductActivity.this , ProductIntroductionActivity.class);
           intent.putExtra(KEY.id, id);
           intent.putExtra(KEY.title, title);

           startActivity(intent);

        }
    });
    }

    private void initRelativeProperties(String id,String title) {
        relativeProperties = findViewById(R.id.relativeProperties_showDetailActivity);
        relativeProperties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShowDetailProductActivity.this , ProductPropertiesActivity.class);

                intent.putExtra(KEY.id, id);
                intent.putExtra(KEY.title, title);

                startActivity(intent);
            }
        });
    }

    private void initSimilar(String categoryId) {

        recyclerViewSimilar = findViewById(R.id.similarProductRecyclerView_showDetailActivity);
        recyclerViewSimilar.setHasFixedSize(true);
        recyclerViewSimilar.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));

        apiInterface.postCategoryIdGetSimilarFromProduct(categoryId).enqueue(new Callback<List<NewProductsModel>>() {
            @Override
            public void onResponse(Call<List<NewProductsModel>> call, Response<List<NewProductsModel>> response) {
                newProductsModelList = response.body();
                adapterSimilarProduct = new AdapterSimilarProduct(getApplicationContext(), newProductsModelList);
                recyclerViewSimilar.setAdapter(adapterSimilarProduct);
                adapterSimilarProduct.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<NewProductsModel>> call, Throwable t) {

            }
        });


    }

    private void initViewPager(String id) {

        viewPager_VP = findViewById(R.id.viewPager_showDetailProductActivity);
        tabLayout_TL = findViewById(R.id.tabLayout_showDetailProductActivity);

        apiInterface.postIdGetViewPagerShoeDetail(id).enqueue(new Callback<List<PagerModel>>() {
            @Override
            public void onResponse(Call<List<PagerModel>> call, Response<List<PagerModel>> response) {
                pagerModelList = response.body();
                pagerClassAdapter = new PagerClassAdapter(pagerModelList, getApplicationContext());
                viewPager_VP.setAdapter(pagerClassAdapter);
                tabLayout_TL.setupWithViewPager(viewPager_VP, true);


            }

            @Override
            public void onFailure(Call<List<PagerModel>> call, Throwable t) {

            }
        });

    }

    private void setGeneralInfo(String id, String title, String brand) {

        nameProduct_TV.setText(title);
        brandProduct_TV.setText(brand);

    }

    private void initGeneralView() {

        brandProduct_TV = findViewById(R.id.productBrand_showDetailProductActivity);
        nameProduct_TV = findViewById(R.id.productName_showDetailProductActivity);
        shoppingCart_IV = findViewById(R.id.shoppingCard_showDetailActivity);
        favorite_IV = findViewById(R.id.favorite_showDetailActivity);
        more_IV = findViewById(R.id.more_showDetailActivity);
        showAllComments_TV = findViewById(R.id.showAllComments_showDetailActivity);
        badgeNotification_TV = findViewById(R.id.badge_showDetailActivity);

    }

    private void myMorePressed(String id, String title, String categoryId) {

        more_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ShowDetailProductActivity.this);

                View view = LayoutInflater.from(ShowDetailProductActivity.this)
                        .inflate(R.layout.more_bottom_sheet, findViewById(R.id.parent_moreBottomSheet));

                LinearLayout linearLayoutCompare = view.findViewById(R.id.LLCompare_moreBottomSheet);
                LinearLayout linearLayoutShare = view.findViewById(R.id.LLShare_moreBottomSheet);
                LinearLayout linearLayoutPriceChart = view.findViewById(R.id.LLPriceChart_moreBottomSheet);


                linearLayoutShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_TEXT,
                            "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, title);

                        startActivity(Intent.createChooser(shareIntent, title));
                    }
                });

                linearLayoutPriceChart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ShowDetailProductActivity.this , ChartPriceActivity.class);
                        intent.putExtra(KEY.id, id);
                        startActivity(intent);

                    }
                });

                linearLayoutCompare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ShowDetailProductActivity.this, CompareProductActivity.class);

                        intent.putExtra(KEY.id, id);
                        intent.putExtra(KEY.CategoryId, categoryId);
                        intent.putExtra(KEY.title, title);

                        startActivity(intent);
                    }
                });

                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();
            }
        });

    }

}
