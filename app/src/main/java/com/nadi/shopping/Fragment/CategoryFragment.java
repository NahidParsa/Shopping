package com.nadi.shopping.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Adapter.CategoryWithoutLimitAdapter;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Model.CategoryWithoutLimitModel;
import com.nadi.shopping.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    //api
    ApiInterface apiInterface;


    RecyclerView categoryRecyclerView;
    CategoryWithoutLimitAdapter categoryAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_category, container, false);
        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);

        initViews(view);


        return view;
    }

    private void initViews(View view) {
        setCategoryRecycler(view);
    }

    private void setCategoryRecycler(View view) {

        categoryRecyclerView = view.findViewById(R.id.recyclerViewCategory_categoryFragment);
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        categoryRecyclerView.setHasFixedSize(true);

        apiInterface.callAllCategory().enqueue(new Callback<List<CategoryWithoutLimitModel>>() {
            @Override
            public void onResponse(Call<List<CategoryWithoutLimitModel>> call, Response<List<CategoryWithoutLimitModel>> response) {

                categoryAdapter = new CategoryWithoutLimitAdapter(getContext(), response.body());
                categoryRecyclerView.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();
                Log.d("TAG", "onResponse: category fragment");
            }
            @Override
            public void onFailure(Call<List<CategoryWithoutLimitModel>> call, Throwable t) {
                Log.d("TAG", "onFailure: category fragment");

            }
        });

    }
}
