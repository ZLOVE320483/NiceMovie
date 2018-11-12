package com.zlove.movie.details;

import com.zlove.movie.favorites.FavoritesInteractor;
import com.zlove.movie.network.TmdbWebService;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailsModule {

    @Provides
    @DetailsScope
    MovieDetailsInteractor provideInteractor(TmdbWebService tmdbWebService) {
        return new MovieDetailsInteractorImpl(tmdbWebService);
    }

    @Provides
    @DetailsScope
    MovieDetailsPresenter providePresenter(MovieDetailsInteractor movieDetailsInteractor,
                                           FavoritesInteractor favoritesInteractor) {
        return new MovieDetailsPresenterImpl(movieDetailsInteractor, favoritesInteractor);
    }
}
