package com.nadi.shopping.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nadi.shopping.Fragment.CategoryFragment;
import com.nadi.shopping.Fragment.HomeFragment;
import com.nadi.shopping.Fragment.ProfileFragment;
import com.nadi.shopping.Fragment.SearchFragment;
import com.nadi.shopping.R;

public class HomeActivity extends AppCompatActivity {

    Typeface font;
//    Toolbar toolbar_TB;
    BottomNavigationView bottomNavigationView_BT;
    FrameLayout containerFrameLayout_FL;
    ImageView shoppingCard_IV;
    TextView pageTitle_TV;

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFont(this);
        setContentView(R.layout.activity_home);
//        setToolbar(this);
        initViews(this);
        fragmentStart();
        navigationBottomSelected();

    }

    private void navigationBottomSelected() {
        bottomNavigationView_BT.setSelectedItemId(R.id.home_bottomNavigationMenu);
        bottomNavigationView_BT.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home_bottomNavigationMenu:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container_homeActivity, new HomeFragment());
                        pageTitle_TV.setText("Home");
                        fragmentTransaction.commit();

                        return true;
                    case  R.id.category_bottomNavigationMenu:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container_homeActivity, new CategoryFragment());
                        pageTitle_TV.setText("Category");
                        fragmentTransaction.commit();

                        return true;

                    case  R.id.profile_bottomNavigationMenu:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container_homeActivity, new ProfileFragment());
                        pageTitle_TV.setText("Profile");
                        fragmentTransaction.commit();
                        return true;

                    case  R.id.search_bottomNavigationMenu:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container_homeActivity, new SearchFragment());
                        pageTitle_TV.setText("Search");
                        fragmentTransaction.commit();

                        return true;

                    default:
                        return false;
                }
            }
        });

    }

    private void fragmentStart() {

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_homeActivity , new HomeFragment());
        fragmentTransaction.commit();
    }

    private void initViews(Activity activity) {

        bottomNavigationView_BT = activity.findViewById(R.id.bottomNavigationView_homeActivity);
        setFontOnNavigationBottomText(activity, bottomNavigationView_BT);

        containerFrameLayout_FL = activity.findViewById(R.id.container_homeActivity);
        shoppingCard_IV = activity.findViewById(R.id.shoppingCard_homeActivity);
        pageTitle_TV = activity.findViewById(R.id.pageTitle_homeActivity);

    }

//    private void setToolbar(Activity activity) {
////        toolbar_TB = activity.findViewById(R.id.toolbar_homeActivity);
//        setToolbar(activity);
//        setSupportActionBar(toolbar_TB);
////        toolbar_TB.setTitle("Shop");
//        toolbar_TB.setTitleTextColor(Color.parseColor("#ffffff"));
//        ActionBar actionBar = getSupportActionBar();
//    }

    // set font
    private void initFont(Activity activity) {
      font = Typeface.createFromAsset(activity.getAssets() , "iransansmob.ttf");
    }

    public void setFontOnNavigationBottomText (final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    setFontOnNavigationBottomText(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(font);
            }
        } catch (Exception e) {
            Log.d("TAG", "setFontOnNavigationBottomText: "+ e.getMessage());
        }
    }
}
