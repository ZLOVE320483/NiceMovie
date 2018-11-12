package com.zlove.movie.listing;

public interface MoviesListingPresenter {

    void firstPage();

    void nextPage();

    void setView(MoviesListingView view);

    void searchMovie(String searchText);

    void searchMovieBackPressed();

    void destroy();
}
