package com.zlove.movie.listing;

import com.zlove.movie.favorites.FavoritesInteractor;
import com.zlove.movie.listing.sorting.SortType;
import com.zlove.movie.listing.sorting.SortingOptionStore;
import com.zlove.movie.model.Movie;
import com.zlove.movie.model.MoviesWrapper;
import com.zlove.movie.network.TmdbWebService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MoviesListingInteractorImpl implements MoviesListingInteractor {

    private FavoritesInteractor favoritesInteractor;
    private TmdbWebService tmdbWebService;
    private SortingOptionStore sortingOptionStore;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final int NEWEST_MIN_VOTE_COUNT = 50;

    public MoviesListingInteractorImpl(FavoritesInteractor favoritesInteractor, TmdbWebService tmdbWebService, SortingOptionStore sortingOptionStore) {
        this.favoritesInteractor = favoritesInteractor;
        this.tmdbWebService = tmdbWebService;
        this.sortingOptionStore = sortingOptionStore;
    }

    @Override
    public boolean isPaginationSupported() {
        int selectedOption = sortingOptionStore.getSelectedOption();
        return selectedOption != SortType.FAVORITES.getValue();
    }

    @Override
    public Observable<List<Movie>> fetchMovies(int page) {
        int selectedOption = sortingOptionStore.getSelectedOption();
        if (selectedOption == SortType.MOST_POPULAR.getValue()) {
            return tmdbWebService.popularMovies(page).map(new Function<MoviesWrapper, List<Movie>>() {
                @Override
                public List<Movie> apply(MoviesWrapper moviesWrapper) throws Exception {
                    return moviesWrapper.getMovieList();
                }
            });
        } else if (selectedOption == SortType.HIGHEST_RATED.getValue()) {
            return tmdbWebService.highestRatedMovies(page).map(new Function<MoviesWrapper, List<Movie>>() {
                @Override
                public List<Movie> apply(MoviesWrapper moviesWrapper) throws Exception {
                    return moviesWrapper.getMovieList();
                }
            });
        } else if (selectedOption == SortType.NEWEST.getValue()) {
            Calendar cal = Calendar.getInstance();
            String maxReleaseDate = dateFormat.format(cal.getTime());
            return tmdbWebService.newestMovies(maxReleaseDate, NEWEST_MIN_VOTE_COUNT).map(new Function<MoviesWrapper, List<Movie>>() {
                @Override
                public List<Movie> apply(MoviesWrapper moviesWrapper) throws Exception {
                    return moviesWrapper.getMovieList();
                }
            });
        } else {
            return Observable.just(favoritesInteractor.getFavorites());
        }
    }

    @Override
    public Observable<List<Movie>> searchMovie(String searchQuery) {
        return tmdbWebService.searchMovies(searchQuery).map(new Function<MoviesWrapper, List<Movie>>() {
            @Override
            public List<Movie> apply(MoviesWrapper moviesWrapper) throws Exception {
                return moviesWrapper.getMovieList();
            }
        });
    }
}
