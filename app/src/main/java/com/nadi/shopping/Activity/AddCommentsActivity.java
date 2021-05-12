 package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hedgehog.ratingbar.RatingBar;
import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.CommentsModel;
import com.nadi.shopping.Model.ResponseCommentsModel;
import com.nadi.shopping.Other.MySharedPreferenceConfig;
import com.nadi.shopping.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCommentsActivity extends AppCompatActivity {

    // bundle
    Bundle bundle;
    String id;
    String link_img;
    String title;

    // api
    ApiInterface apiInterface;

    // general view
    ImageView img_IV;
    TextView title_TV;
    TextView add_TV;
    EditText titleView_ET;
    EditText description_ET;
    EditText positive_ET;
    EditText negative_ET;
//
    RatingBar ratingBar_RB;
    static String rating;

    String date;

    MySharedPreferenceConfig mySharedPreferenceConfig;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comments_activity);

        apiInterface = ApiClient.getRetrofitApiGsonComments(Urls.BASE_URLS).create(ApiInterface.class);

       // apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);
        mySharedPreferenceConfig = new MySharedPreferenceConfig(AddCommentsActivity.this);
        initBundle();

    }

    private void initBundle() {

        img_IV = findViewById(R.id.productImg_addCommentsActivity);
        title_TV = findViewById(R.id.productName_addCommentsActivity);

        bundle = getIntent().getExtras();
        id = bundle.getString(KEY.id);
        link_img = bundle.getString(KEY.link_img);
        title = bundle.getString(KEY.title);

        Picasso.get().load(link_img).into(img_IV);
        title_TV.setText(title);

        ratingBar();
        initView(id);
//        mydummy(id);

    }

//    private void mydummy(String id) {
//
//        apiInterface.postdummy(id).enqueue(new Callback<ResponseCommentsModel>() {
//            @Override
//            public void onResponse(Call<ResponseCommentsModel> call, Response<ResponseCommentsModel> response) {
//
//                if (response.body().getMessage() == "Your View Registered Successfully") {
//                  Log.d("TAG", "onResponse: response" + response.body().getMessage());
//                  Toast.makeText(AddCommentsActivity.this, "You wrote your view", Toast.LENGTH_SHORT).show();
//                }else{
//                  Log.d("TAG", "onResponse: else" + response.body().getMessage());
//                  Log.d("TAG", "onResponse something went wrong");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseCommentsModel> call, Throwable t) {
//
//                Log.d("TAG", "onFailure: add comments " + t.getMessage());
//                Toast.makeText(AddCommentsActivity.this, "toast : "+ t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//    }

    private void initView(String id) {

         add_TV = findViewById(R.id.add_addCommentsActivity);
         titleView_ET = findViewById(R.id.titleViewET_addCommentsActivity);
         description_ET = findViewById(R.id.descriptionViewET_addCommentsActivity);
         negative_ET = findViewById(R.id.negativeViewET_addCommentsActivity);
         positive_ET = findViewById(R.id.positiveViewET_addCommentsActivity);

         myAddPressed(id);

    }

    private void myAddPressed(String id) {
        add_TV.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     verifyInput(id);
                 }
             });
    }

    private void verifyInput(String id_product){
       String title = titleView_ET.getText().toString().toLowerCase();
       String description = description_ET.getText().toString().trim();
       String negative = negative_ET.getText().toString().trim();
       String positive = positive_ET.getText().toString().trim();
       String user_email = mySharedPreferenceConfig.writeUserInfoPref().get(MySharedPreferenceConfig.EMAIL);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");//, Locale.getDefault()
         date = df.format(c);


        if (TextUtils.isEmpty(title) ||TextUtils.isEmpty(description)  ||
                TextUtils.isEmpty(negative) || TextUtils.isEmpty(positive)){

           Toast.makeText(this, "all the field are required", Toast.LENGTH_SHORT).show();
            Log.d("TAG1", "verifyInput on empty: " + id_product +" " + title + " "

            + description + " " + negative + " " + positive+ " " + user_email);


       }else {
            Log.d("TAG1", "verifyInput ok: " + id_product +" " + title + " "

                        + description + " " + negative + " " + positive+ " " + user_email);


            apiInterface.postNewComments(id_product, title, description, user_email
            , rating, date, positive, negative).enqueue(new Callback<ResponseCommentsModel>() {
                @Override
                public void onResponse(Call<ResponseCommentsModel> call, Response<ResponseCommentsModel> response) {

                    if (response.isSuccessful() && response.body().isStatus()) {
                        Log.d("TAG", "comment1: response" + response.body().getMessage());
                        Toast.makeText(AddCommentsActivity.this, "Thank you! Your comment has been successfully submitted", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.d("TAG", "comment 2: else" + response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ResponseCommentsModel> call, Throwable t) {
                    Log.d("TAG", "onFailure: add comments " + t.getMessage());
                    Toast.makeText(AddCommentsActivity.this, "toast : "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

       }
    }


    private void ratingBar() {
        ratingBar_RB = (RatingBar) findViewById(R.id.ratingBar_addCommentsActivity);
//        ratingBar_RB.setStarEmptyDrawable(getResources().getDrawable(R.mipmap.star_empty));
//        ratingBar_RB.setStarHalfDrawable(getResources().getDrawable(R.mipmap.star_half));
//        ratingBar_RB.setStarFillDrawable(getResources().getDrawable(R.mipmap.star_full));
//        ratingBar_RB.setStarCount(5);
//        ratingBar_RB.setStar(2.5f);
//        ratingBar_RB.halfStar(true);
//        ratingBar_RB.setmClickable(true);
//        ratingBar_RB.setStarImageWidth(120f);
//        ratingBar_RB.setStarImageHeight(60f);
//        ratingBar_RB.setImagePadding(35);
        ratingBar_RB.setOnRatingChangeListener(
                    new RatingBar.OnRatingChangeListener() {
                        @Override
                        public void onRatingChange(float RatingCount) {
                            rating = String.valueOf(RatingCount);
                        }
                    }
            );
    }


}
