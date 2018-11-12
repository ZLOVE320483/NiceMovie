package com.zlove.movie.listing.sorting;

import dagger.Module;
import dagger.Provides;

@Module
public class SortingModule {

    @Provides
    SortingDialogInteractor provideSortingDialogInteractor(SortingOptionStore store) {
        return new SortingDialogInteractorImpl(store);
    }

    @Provides
    SortingDialogPresenter providePresenter(SortingDialogInteractor interactor) {
        return new SortingDialogPresenterImpl(interactor);
    }
}
