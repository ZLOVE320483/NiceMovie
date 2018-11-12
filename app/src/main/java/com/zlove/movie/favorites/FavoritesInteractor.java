package com.zlove.movie.favorites;

import com.zlove.movie.model.Movie;

import java.util.List;

public interface FavoritesInteractor {
    void setFavorite(Movie movie);
    boolean isFavorite(String id);
    List<Movie> getFavorites();
    void unFavorite(String id);
}
