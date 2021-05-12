package com.nadi.shopping.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Adapter.AdapterCommentsShow;
import com.nadi.shopping.Links.KEY;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.CommentsModel;
import com.nadi.shopping.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowCommentsActivity extends AppCompatActivity {


    // general view
    ImageView back_IV;
    TextView newComment_TV;

    // api
    ApiInterface apiInterface;

    // bundle
    Bundle bundle;
    String id;
    String title;
    String link_img;

    //
    RecyclerView recyclerShowComments;
    AdapterCommentsShow adapterCommentsShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_comments);

        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);

        initBundle();



    }

    private void initBundle() {
        back_IV = findViewById(R.id.back_commentsActivity);
        newComment_TV = findViewById(R.id.newComment_showCommentsActivity);

        bundle = getIntent().getExtras();
        id = bundle.getString("ID_PRODUCT");
        title = bundle.getString("TITLE");
        link_img = bundle.getString("LINK_IMG");

        myAddNewComment(id, title, link_img);
        setRecyclerShowComments(id);
        Toast.makeText(this, "id: " + id, Toast.LENGTH_SHORT).show();
    }

    private void setRecyclerShowComments(String id) {

        recyclerShowComments = findViewById(R.id.recycler_showCommentsActivity);
        recyclerShowComments.setHasFixedSize(true);
        recyclerShowComments.setLayoutManager(new LinearLayoutManager(ShowCommentsActivity.this, RecyclerView.VERTICAL,false));

        apiInterface.callCommentsPostIdProduct(id).enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(Call<List<CommentsModel>> call, Response<List<CommentsModel>> response) {
                adapterCommentsShow = new AdapterCommentsShow(response.body(), ShowCommentsActivity.this);
                recyclerShowComments.setAdapter(adapterCommentsShow);
                adapterCommentsShow.notifyDataSetChanged();
                Log.d("TAG", "onResponse: show comment " + response.body());
            }

            @Override
            public void onFailure(Call<List<CommentsModel>> call, Throwable t) {

                Log.d("TAG", "onFailure: show comments");

            }
        });
    }

    private void myAddNewComment(String id, String title, String link_img) {
        newComment_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowCommentsActivity.this, AddCommentsActivity.class);
                intent.putExtra(KEY.id, id);
                intent.putExtra(KEY.title, title);
                intent.putExtra(KEY.link_img, link_img);
                startActivity(intent);
            }
        });
    }


}
