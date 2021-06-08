package com.nadi.shopping.ROOM;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface FavoriteDao {

    // favorite is the name of table n favorite entity model
    @Query("select * from favorite")
    Flowable<List<FavoriteEntityModel>> getAllFavorite();

    @Query("SELECT EXISTS ( SELECT 1 FROM favorite WHERE id =:selected_id)")
    int isFavorite( int selected_id);

    @Delete
    void deleteFavorite(FavoriteEntityModel favoriteEntityModel);

    @Insert
    void insertFavorite(FavoriteEntityModel favoriteEntityModel);

    @Query("DELETE FROM favorite WHERE product_id = :product_id")
     void deleteByproductId(String product_id);

}
