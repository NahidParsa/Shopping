package com.nadi.shopping.ROOM;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.KeyCycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.nadi.shopping.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FavoriteRoomDBActivity extends AppCompatActivity {

    FavoriteAdapter favoriteAdapter;
    List<FavoriteEntityModel> favoriteEntityModelList = new ArrayList<>();
    CompositeDisposable compositeDisposable;
    RecyclerView recyclerFavorite;
    ImageView back_IV;

    // init room db
    public static ROOMDB roomdb;
    public static FavoriteRepository favoriteRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_room_dbactivity);

        generalInit();

    }

    private void generalInit() {

        compositeDisposable = new CompositeDisposable();

        initRoomDataBase();

        recyclerFavorite = findViewById(R.id.recyclerFavorite_favoriteRoomDBActivity);
        recyclerFavorite.setHasFixedSize(true);
        recyclerFavorite.setLayoutManager(new LinearLayoutManager(FavoriteRoomDBActivity.this,RecyclerView.VERTICAL,false));


        compositeDisposable.add(favoriteRepository.getAllFavorite()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<List<FavoriteEntityModel>>() {
                        @Override
                        public void accept(List<FavoriteEntityModel> favoriteEntityModels) throws Exception {

                            favoriteEntityModelList = favoriteEntityModels;

                            favoriteAdapter = new FavoriteAdapter(FavoriteRoomDBActivity.this, favoriteEntityModelList);
                            recyclerFavorite.setAdapter(favoriteAdapter);
                            favoriteAdapter.notifyDataSetChanged();

                            Log.d("TAG", "accept: favorite");
                        }
                    })
        );

    }

    // init kardan db ra az activity show detail product bardashtam
    private void initRoomDataBase() {
        roomdb = ROOMDB.getInstance(this);
        favoriteRepository = FavoriteRepository.getInstance(FavoriteDatabase.getInstance(roomdb.favoriteDao()));
    }


}
