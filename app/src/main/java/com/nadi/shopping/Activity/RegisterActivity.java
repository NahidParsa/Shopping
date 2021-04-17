package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText name_ET;
    EditText phone_ET;
    EditText email_ET;
    EditText password_ET;
    EditText rePassword_ET;
    TextView register_TV;
    LinearLayout swipeLeft_LL;

    // api
    ApiInterface apiInterface;
    public MySharedPreferenceConfig mySharedPreferenceConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        apiInterface = ApiClient.getRetrofitApiGson(Urls.BASE_URLS).create(ApiInterface.class);
        mySharedPreferenceConfig = new MySharedPreferenceConfig(RegisterActivity.this);

        if (mySharedPreferenceConfig.loginStatus()){
            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            finish();
        }

        initGeneral();

    }
        private void initGeneral () {

            email_ET = findViewById(R.id.et_email_registerActivity);
            phone_ET = findViewById(R.id.et_phone_registerActivity);
            name_ET = findViewById(R.id.et_name_registerActivity);
            password_ET = findViewById(R.id.et_password_registerActivity);
            rePassword_ET = findViewById(R.id.et_repassword_registerActivity);
            register_TV = findViewById(R.id.txt_register_registerActivity);
            swipeLeft_LL = findViewById(R.id.LLSwipeLeft_registerActivity);

            myTouch();
            myButtonClicked();
        }

    private void myButtonClicked() {
        register_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyInput();
            }
        });
    }

    private void myTouch(){
            swipeLeft_LL.setOnTouchListener(new OnSwipeTouchListener(RegisterActivity.this) {
                public void onSwipeTop() {
                }
                public void onSwipeRight() {
                }

                public void onSwipeLeft() {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

                public void onSwipeBottom() {
                }
            });


        }


    private void verifyInput(){
       String name = name_ET.getText().toString().toLowerCase();
       String email = email_ET.getText().toString().trim();
       String password = password_ET.getText().toString().trim();
       String repassword = rePassword_ET.getText().toString().trim();
       String phone= phone_ET.getText().toString();
       boolean usernameboolean = false, passwordboolean = false, phoneboolean  = false, emailboolean = false;

           if (name.length() >= 5){
               usernameboolean = true;

           }else{

               Toast.makeText(RegisterActivity.this, "name Should be more than 5 character", Toast.LENGTH_LONG).show();
           }

          if (phone.length() == 11 && (phone.startsWith("0"))) {
              phoneboolean = true;
           }
          else {
              Toast.makeText(RegisterActivity.this, "phone number is required", Toast.LENGTH_LONG).show();

          }

            if (Patterns.EMAIL_ADDRESS.matcher(email).matches() ) {
                emailboolean = true;
           }
            else{
                Toast.makeText(RegisterActivity.this, "email  is invalid", Toast.LENGTH_LONG).show();

            }

            if (password.length() >= 8 && password.equals(repassword) && isValidPassword(password)) {

                passwordboolean = true;
            }
            else {
               Toast.makeText(RegisterActivity.this, "password should be more than 7 character and include sign and big/small character", Toast.LENGTH_LONG).show();
           }

         if (passwordboolean && usernameboolean && phoneboolean && emailboolean){

             myApi(name, email, phone, password);

         }
    }

    private void myApi(String name, String email, String phone, String password) {

         name_ET.setText("");
         email_ET.setText("");
         password_ET.setText("");
         rePassword_ET.setText("");
         phone_ET.setText("");

     apiInterface.postRegister(name, email, phone, password).enqueue(new Callback<ResponseModel>() {
         @Override
         public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
             switch (response.body().getMessage()){
                 case "You Have an Account":
                     Toast.makeText(RegisterActivity.this, "Your Phone number Already Registered", Toast.LENGTH_SHORT).show();
                     Log.d("TAG", "onResponse register: have an account");
                     break;
                     
                 case "Registered Successfully":
                     UserModel userModel = response.body().getUserModel();
                     mySharedPreferenceConfig.readUserInfoPref(userModel);
                     Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                     startActivity(intent);
                     Log.d("TAG", "onResponse register: register successfully");
                     finish();
                     break;

                 default:
                     break;
             }

             Log.d("TAG", "onResponse: register " + response.body().toString());
         }

         @Override
         public void onFailure(Call<ResponseModel> call, Throwable t) {


             Log.d("TAG", "onFailure: register");
             Log.d("TAG", "onFailure: register" + t.getMessage());
         }
     });



    }


    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }




    }

