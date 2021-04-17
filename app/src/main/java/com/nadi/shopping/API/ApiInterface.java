package com.nadi.shopping.API;

import com.nadi.shopping.Model.AmazingOfferModel;
import com.nadi.shopping.Model.BrandModel;
import com.nadi.shopping.Model.CategoryDetailBrandModel;
import com.nadi.shopping.Model.CategoryModel;
import com.nadi.shopping.Model.CategoryWithoutLimitModel;
import com.nadi.shopping.Model.IntroductionModel;
import com.nadi.shopping.Model.Item0AmazingOfferModel;
import com.nadi.shopping.Model.Item1WallAmazingOfferModel;
import com.nadi.shopping.Model.NewProductsModel;
import com.nadi.shopping.Model.OptionProductModel;
import com.nadi.shopping.Model.PagerModel;
import com.nadi.shopping.Model.PriceChartModel;
import com.nadi.shopping.Model.ResponseModel;
import com.nadi.shopping.Model.TitleModel;
import com.nadi.shopping.Model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    // pager model table
    @GET("getPagerModel.php")
    Call<List<PagerModel>> callPagerModel();

    @GET("getCategoryByLimit.php")
    Call<List<CategoryModel>> callCategoryByLimit();

//   @GET("getAmazingOfferProduct.php")
//   Call<List<Item0AmazingOfferModel>> callItem0AmazingOffer();

   @GET("getPagerModelMiddle.php")
    Call<List<PagerModel>> callPagerModelMiddle();


//    @GET("getAmazingOfferProduct.php")
//    Call<List<AmazingOfferModel>> callItem0AmazingOffer();
//
//    @GET("getWallAmazingOfferProduct.php")
//    Call<List<AmazingOfferModel>> callItem1wallAmazingOffer();
//@GET("getAmazingOfferProduct.php")
// Call<List<Item0AmazingOfferModel>> callItem0AmazingOffer();
//
// @GET("getWallAmazingOfferProduct.php")
// Call<List<Item1WallAmazingOfferModel>> callItem1wallAmazingOffer();

    @GET("getNewSpecialProductTitle.php")
     Call<List<TitleModel>> callTitleModelNewSpecialProduct();

    @GET("getNewProducts.php")
    Call<List<NewProductsModel>> callNewProduct();

    @GET("getBrand.php")
    Call<List<BrandModel>> callBrand();

    @GET("getAllCategory.php")
    Call<List<CategoryWithoutLimitModel>> callAllCategory();

    @POST("getDetailCategoryByID.php")
    Call<List<CategoryDetailBrandModel>> postId(@Query("id") String id);

    @POST("getSpecialOfferDetailCategoryByID.php")
    Call<List<Item0AmazingOfferModel>> postIdGetInfo(@Query("id") String id);

    @POST("getPopularDetailCategoryByID.php")
    Call<List<Item0AmazingOfferModel>> postIdGetPopular(@Query("id") String id);

    @POST("getDetailDetailCategory.php")
    Call<List<Item0AmazingOfferModel>> postIdGetDetailDetailCategory(@Query("id") String id);

    @POST("getViewPagerProductImgSHowDetail.php")
    Call<List<PagerModel>> postIdGetViewPagerShoeDetail(@Query("id") String id);

    @POST("getSimilarProductShowActivity.php")
    Call<List<NewProductsModel>> postCategoryIdGetSimilarFromProduct(@Query("catogory_id") String catogory_id);

    @POST("getProductOptionSHowDetail.php")
    Call<List<OptionProductModel>> postIdGetProductOption(@Query("id") String id);

    @POST("getProductPropertiesShowDetail.php")
    Call<List<OptionProductModel>> postIdGetProductProperties(@Query("id") String id);

    @POST("getProductIntroductionShowDetail.php")
    Call<List<IntroductionModel>> postIdGetIntroduction(@Query("id") String id);

    @POST("getPriceChart.php")
    Call<List<PriceChartModel>> postIdGetPriceChart(@Query("id") String id);

    @POST("getCompareProduct.php")
    Call<List<Item0AmazingOfferModel>> postCategoryIdGetComparableProduct(@Query("category_id") String category_id, @Query("id") String id);

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseModel> postRegister(@Field("name") String name, @Field("email") String email, @Field("phone") String phone, @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseModel> postLogin(@Field("email") String email, @Field("password") String password);

}




