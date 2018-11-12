package com.zlove.movie.listing.sorting;

import com.zlove.movie.listing.ListingScope;

import dagger.Module;
import dagger.Provides;

@Module
public class SortingModule {

    @Provides
    @ListingScope
    SortingDialogInteractor provideSortingDialogInteractor(SortingOptionStore store) {
        return new SortingDialogInteractorImpl(store);
    }

    @Provides
    @ListingScope
    SortingDialogPresenter providePresenter(SortingDialogInteractor interactor) {
        return new SortingDialogPresenterImpl(interactor);
    }
}
