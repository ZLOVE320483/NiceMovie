package com.zlove.movie.listing;

import android.support.annotation.NonNull;

import com.zlove.movie.model.Movie;
import com.zlove.movie.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MoviesListingPresenterImpl implements MoviesListingPresenter {

    private MoviesListingView view;
    private MoviesListingInteractor moviesListingInteractor;
    private Disposable fetchSubscription;
    private Disposable movieSearchSubscription;
    private int currentPage = 1;
    private List<Movie> loadedMovies = new ArrayList<>();
    private boolean showingSearchResult = false;

    public MoviesListingPresenterImpl(MoviesListingInteractor interactor) {
        this.moviesListingInteractor = interactor;
    }

    @Override
    public void firstPage() {
        currentPage = 1;
        loadedMovies.clear();
        displayMovies();
    }

    @Override
    public void nextPage() {
        if (showingSearchResult) {
            return;
        }
        if (moviesListingInteractor.isPaginationSupported()) {
            currentPage++;
            displayMovies();
        }
    }

    @Override
    public void setView(MoviesListingView view) {
        this.view = view;
        if (!showingSearchResult) {
            displayMovies();
        }
    }

    @Override
    public void searchMovie(String searchText) {
        if (searchText == null || searchText.length() < 1) {
            displayMovies();
        } else {
            displayMovieSearchResult(searchText);
        }
    }

    @Override
    public void searchMovieBackPressed() {
        if (showingSearchResult) {
            showingSearchResult = false;
            loadedMovies.clear();
            displayMovies();
        }
    }

    @Override
    public void destroy() {
        view = null;
        RxUtils.unSubscribe(fetchSubscription, movieSearchSubscription);
    }

    private void displayMovies() {
        showLoading();
        fetchSubscription = moviesListingInteractor.fetchMovies(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        onMovieFetchSuccess(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onMovieFetchFailed(throwable);
                    }
                });
    }

    private void displayMovieSearchResult(@NonNull final String searchText) {
        showingSearchResult = true;
        showLoading();
        movieSearchSubscription = moviesListingInteractor.searchMovie(searchText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        onMovieSearchSuccess(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onMovieSearchFailed(throwable);
                    }
                });
    }

    private void showLoading() {
        if (isViewAttached()) {
            view.loadingStarted();
        }
    }

    private void onMovieFetchSuccess(List<Movie> movies) {
        if (moviesListingInteractor.isPaginationSupported()) {
            loadedMovies.addAll(movies);
        } else {
            loadedMovies = new ArrayList<>(movies);
        }
        if (isViewAttached()) {
            view.showMovies(loadedMovies);
        }
    }

    private void onMovieFetchFailed(Throwable e) {
        if (isViewAttached()) {
            view.loadingFailed(e.getMessage());
        }
    }

    private void onMovieSearchSuccess(List<Movie> movies) {
        loadedMovies.clear();
        loadedMovies = new ArrayList<>(movies);
        if (isViewAttached()) {
            view.showMovies(loadedMovies);
        }
    }

    private void onMovieSearchFailed(Throwable e) {
        if (isViewAttached()) {
            view.loadingFailed(e.getMessage());
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }
}
