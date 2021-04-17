package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.ResponseModel;
import com.nadi.shopping.Model.UserModel;
import com.nadi.shopping.Other.MySharedPreferenceConfig;
import com.nadi.shopping.Other.OnSwipeTouchListener;
import com.nadi.shopping.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText email_ET;
    EditText password_ET;
    TextView login_TV;
    TextView forgotPass_TV;
    LinearLayout swipeRight_LL;

    ApiInterface apiInterface;
    MySharedPreferenceConfig mySharedPreferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiInterface = ApiClient.getRetrofitApiGson(Urls.BASE_URLS).create(ApiInterface.class);

        mySharedPreferenceConfig = new MySharedPreferenceConfig(LoginActivity.this);
        if (mySharedPreferenceConfig.loginStatus()){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
        initGeneral();
    }

    private void initGeneral() {
        email_ET = findViewById(R.id.et_email_loginActivity);
        password_ET = findViewById(R.id.et_password_loginActivity);
        login_TV = findViewById(R.id.txt_login_loginActivity);
        forgotPass_TV = findViewById(R.id.txt_forgotPassword_loginActivity);
        swipeRight_LL = findViewById(R.id.LLSwipeRight_loginActivity);

        myTouch();

        myButton();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void myButton() {

        login_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfo();

            }
        });

    }

    private void takeInfo() {
        String email = email_ET.getText().toString().toLowerCase().trim();
        String password = password_ET.getText().toString().trim();

        apiInterface.postLogin(email, password).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                switch (response.body().getMessage()){

                    case "You Have NO Account":
                        Toast.makeText(LoginActivity.this, "you don't have an account", Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onResponse login : have no account");
                        break;

                    case "Login Successfully":
                        UserModel userModel = response.body().getUserModel();
                        mySharedPreferenceConfig.readUserInfoPref(userModel);
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        Log.d("TAG", "onResponse login : have an account");
                        finish();
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                Log.d("TAG", "onFailure: login");
            }
        });
    }

    private void myTouch() {

        swipeRight_LL.setOnTouchListener(new OnSwipeTouchListener(LoginActivity.this) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }

            public void onSwipeLeft() {
            }

            public void onSwipeBottom() {
            }

        });


    }


}
