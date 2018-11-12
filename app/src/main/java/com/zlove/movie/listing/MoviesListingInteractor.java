package com.zlove.movie.listing;

import com.zlove.movie.model.Movie;

import java.util.List;

import io.reactivex.Observable;

public interface MoviesListingInteractor {

    boolean isPaginationSupported();
    Observable<List<Movie>> fetchMovies(int page);
    Observable<List<Movie>> searchMovie(String searchQuery);

}
