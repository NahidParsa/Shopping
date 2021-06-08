package com.nadi.shopping.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nadi.shopping.API.ApiClient;
import com.nadi.shopping.API.ApiInterface;
import com.nadi.shopping.Activity.FAQActivity;
import com.nadi.shopping.Activity.LoginActivity;
import com.nadi.shopping.Links.Urls;
import com.nadi.shopping.Other.MySharedPreferenceConfig;
import com.nadi.shopping.R;
import com.nadi.shopping.ROOM.FavoriteRoomDBActivity;

public class ProfileFragment extends Fragment {

    ImageView shoppingCart_IV;
    ImageView shoppingBag_IV;
    TextView email_TV;
    TextView name_TV;
    TextView phone_TV;
    TextView address_TV;
    RelativeLayout bagShop_RL;
    RelativeLayout favoriteList_RL;
    RelativeLayout orderList_RL;
    RelativeLayout faq_RL;
    RelativeLayout exit_RL;

    //
    ApiInterface apiInterface;
    MySharedPreferenceConfig mySharedPreferenceConfig;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_profile, container, false);

        apiInterface = ApiClient.getRetrofitApi(Urls.BASE_URLS).create(ApiInterface.class);
        mySharedPreferenceConfig = new MySharedPreferenceConfig(view.getContext());
        initViews(view);
        
        return view;

    }

    private void initViews(View view) {
//         shoppingCart_IV = view.findViewById(R.id.shoppingCard_profileFragment);
//         shoppingBag_IV = view.findViewById(R.id.shoppingBag_profileFragment);
         email_TV = view.findViewById(R.id.email_profileFragment);
         name_TV = view.findViewById(R.id.name_profileFragment);
         phone_TV = view.findViewById(R.id.phone_profileFragment);
         address_TV = view.findViewById(R.id.address_profileFragment);
         bagShop_RL = view.findViewById(R.id.RLBagShop_profileFragment);
         favoriteList_RL = view.findViewById(R.id.RLFavoriteList_profileFragment);
         orderList_RL = view.findViewById(R.id.RLOrderList_profileFragment);
         faq_RL = view.findViewById(R.id.RLFAQ_profileFragment);
         exit_RL = view.findViewById(R.id.RLExit_profileFragment);

         setGeneralInfo();
         myExitClicked(view);
         setFaqClicked(view);
         favoriteListClicked(view);

    }

    private void favoriteListClicked(View view) {
        favoriteList_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(view.getContext() , FavoriteRoomDBActivity.class);
                startActivity(intent);

            }
        });
    }

    private void setFaqClicked(View view) {
        faq_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FAQActivity.class));
            }
        });

    }

    private void myExitClicked(View view) {
        exit_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySharedPreferenceConfig.logoutPref();
                startActivity(new Intent(view.getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }

    private void setGeneralInfo() {

        email_TV.setText(mySharedPreferenceConfig.writeUserInfoPref().get(MySharedPreferenceConfig.EMAIL));
        phone_TV.setText(mySharedPreferenceConfig.writeUserInfoPref().get(MySharedPreferenceConfig.PHONE));
        address_TV.setText(mySharedPreferenceConfig.writeUserInfoPref().get(MySharedPreferenceConfig.ADDRESS));
        name_TV.setText(mySharedPreferenceConfig.writeUserInfoPref().get(MySharedPreferenceConfig.NAME));
    }

}
