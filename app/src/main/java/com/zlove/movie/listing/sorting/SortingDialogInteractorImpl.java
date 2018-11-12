package com.zlove.movie.listing.sorting;

public class SortingDialogInteractorImpl implements SortingDialogInteractor {

    private SortingOptionStore sortingOptionStore;

    public SortingDialogInteractorImpl(SortingOptionStore sortingOptionStore) {
        this.sortingOptionStore = sortingOptionStore;
    }

    @Override
    public int getSelectedSortingOption() {
        return sortingOptionStore.getSelectedOption();
    }

    @Override
    public void setSortingOption(SortType sortType) {
        sortingOptionStore.setSelectedOption(sortType);
    }
}
