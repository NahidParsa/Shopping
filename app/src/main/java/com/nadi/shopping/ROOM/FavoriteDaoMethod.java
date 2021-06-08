package com.nadi.shopping.ROOM;

import java.util.List;

import io.reactivex.Flowable;

public interface FavoriteDaoMethod {

    Flowable<List<FavoriteEntityModel>> getAllFavorite();

    int isFavorite( int selected_id);

    void deleteFavorite(FavoriteEntityModel favoriteEntityModel);

    void insertFavorite(FavoriteEntityModel favoriteEntityModel);

    ///
    void deleteByproductId(String product_id);


}
