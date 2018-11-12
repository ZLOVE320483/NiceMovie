package com.zlove.movie.listing;

import com.zlove.movie.model.Movie;

import java.util.List;

public interface MoviesListingView {
    void showMovies(List<Movie> movies);
    void loadingStarted();
    void loadingFailed(String errorMessage);
    void onMovieClicked(Movie movie);
}
