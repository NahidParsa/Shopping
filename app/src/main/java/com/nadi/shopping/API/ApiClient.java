package com.nadi.shopping.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nadi.shopping.Links.Urls;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofitApi(String url){

        if (retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;

    }

    public static Retrofit getRetrofitApiGson(String url){


        if (retrofit == null){

            Gson gson = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return retrofit;

    }


    public static Retrofit getRetrofitApiGsonComments(String url){


        if (retrofit == null) {

            Gson gson = new GsonBuilder().setLenient().create();

            OkHttpClient client = new OkHttpClient();

            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        }
        return retrofit;
    }

}
