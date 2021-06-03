package com.nadi.shopping.Other;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.nadi.shopping.Model.UserModel;
import com.nadi.shopping.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MySharedPreferenceConfig  {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public static final String LOGIN_STATUS = "LOGIN_STATUS";
    public static final String ID = "ID";
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";
    public static final String NAME = "NAME";
    public static final String ADDRESS = "ADDRESS";
    public static final ArrayList<String> SELECTED_CATEGORY = new ArrayList<>();

    public MySharedPreferenceConfig(Context context) {

        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();

    }

    public void readUserInfoPref(UserModel userModel){
        editor.putString(ID, userModel.getId());
        editor.putString(PHONE, userModel.getPhone());
        editor.putString(EMAIL, userModel.getEmail());
        editor.putString(NAME, userModel.getName());
        editor.putString(ADDRESS, userModel.getAddress());
        editor.putBoolean(LOGIN_STATUS, true);
        editor.commit();
    }

    public void logoutPref(){
        editor.clear();
        editor.commit();
    }

    public HashMap<String,String> writeUserInfoPref(){

        HashMap<String,String> writeUserData = new HashMap<>();
        writeUserData.put(ID, sharedPreferences.getString(ID, null));
        writeUserData.put(PHONE, sharedPreferences.getString(PHONE, null));
        writeUserData.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        writeUserData.put(NAME, sharedPreferences.getString(NAME, null));
        writeUserData.put(ADDRESS, sharedPreferences.getString(ADDRESS, null));

        return writeUserData;
    }

    public boolean loginStatus(){
        return sharedPreferences.getBoolean(LOGIN_STATUS, false);
    }


}
