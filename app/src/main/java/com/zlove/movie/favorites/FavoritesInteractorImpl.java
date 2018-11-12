package com.zlove.movie.favorites;

import com.zlove.movie.model.Movie;

import java.util.List;

public class FavoritesInteractorImpl implements FavoritesInteractor {

    private FavoritesStore favoritesStore;

    FavoritesInteractorImpl(FavoritesStore store) {
        this.favoritesStore = store;
    }

    @Override
    public void setFavorite(Movie movie) {
        favoritesStore.setFavorite(movie);
    }

    @Override
    public boolean isFavorite(String id) {
        return favoritesStore.isFavorite(id);
    }

    @Override
    public List<Movie> getFavorites() {
        return favoritesStore.getFavorites();
    }

    @Override
    public void unFavorite(String id) {
        favoritesStore.unFavorite(id);
    }
}
