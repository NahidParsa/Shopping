package com.nadi.shopping.ROOM;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteRepository implements FavoriteDaoMethod{

    private FavoriteDaoMethod favoriteDaoMethod;
    private static FavoriteRepository instance;

    public FavoriteRepository(FavoriteDaoMethod favoriteDaoMethod) {
        this.favoriteDaoMethod = favoriteDaoMethod;
    }

    public static FavoriteRepository getInstance(FavoriteDaoMethod favoriteDaoMethod){

        if (instance == null){
            instance = new FavoriteRepository(favoriteDaoMethod);
        }
        return instance;
    }

    @Override
    public Flowable<List<FavoriteEntityModel>> getAllFavorite() {
        return favoriteDaoMethod.getAllFavorite();
    }

    @Override
    public int isFavorite(int selected_id) {
        return favoriteDaoMethod.isFavorite(selected_id);
    }

    @Override
    public void deleteFavorite(FavoriteEntityModel favoriteEntityModel) {
        favoriteDaoMethod.deleteFavorite(favoriteEntityModel);
    }

    @Override
    public void insertFavorite(FavoriteEntityModel favoriteEntityModel) {
        favoriteDaoMethod.insertFavorite(favoriteEntityModel);
    }

    ///////
    @Override
    public void deleteByproductId(String product_id) {
        favoriteDaoMethod.deleteByproductId(product_id);
    }
}
