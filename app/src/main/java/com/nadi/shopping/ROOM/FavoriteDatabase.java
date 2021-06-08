package com.nadi.shopping.ROOM;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteDatabase implements FavoriteDaoMethod {

    private FavoriteDao favoriteDao;
    private static FavoriteDatabase instance;

    public FavoriteDatabase(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    public static FavoriteDatabase getInstance(FavoriteDao favoriteDao){

        if (instance == null){
            instance = new FavoriteDatabase(favoriteDao);
        }
        return instance;
    }

    @Override
    public Flowable<List<FavoriteEntityModel>> getAllFavorite() {
        return favoriteDao.getAllFavorite();
    }

    @Override
    public int isFavorite(int selected_id) {
        return favoriteDao.isFavorite(selected_id);
    }

    @Override
    public void deleteFavorite(FavoriteEntityModel favoriteEntityModel) {
        favoriteDao.deleteFavorite(favoriteEntityModel);
    }

    @Override
    public void insertFavorite(FavoriteEntityModel favoriteEntityModel) {
        favoriteDao.insertFavorite(favoriteEntityModel);
    }

    ///////////
    @Override
    public void deleteByproductId(String product_id) {
        favoriteDao.deleteByproductId(product_id);
    }

}
