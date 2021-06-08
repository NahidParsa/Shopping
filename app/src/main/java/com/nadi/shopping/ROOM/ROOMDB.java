package com.nadi.shopping.ROOM;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavoriteEntityModel.class}, version = 1, exportSchema = false)
public abstract class ROOMDB extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();
    public static ROOMDB instance;

    public static ROOMDB getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context, ROOMDB.class, "ShoppingApp")
                        .allowMainThreadQueries().build();
        }
        return instance;
    }

    



}
