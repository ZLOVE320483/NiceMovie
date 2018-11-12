package com.zlove.movie.listing.sorting;

public interface SortingDialogView {

    void setPopularChecked();

    void setNewestChecked();

    void setHighestRatedChecked();

    void setFavoritesChecked();

    void dismissDialog();
}
