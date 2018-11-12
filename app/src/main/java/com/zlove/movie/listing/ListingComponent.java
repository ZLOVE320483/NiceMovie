package com.zlove.movie.listing;

import com.zlove.movie.listing.sorting.SortingDialogFragment;
import com.zlove.movie.listing.sorting.SortingModule;

import dagger.Subcomponent;

@ListingScope
@Subcomponent(modules = {ListingModule.class, SortingModule.class})
public interface ListingComponent {

    MoviesListingFragment inject(MoviesListingFragment fragment);
    SortingDialogFragment inject(SortingDialogFragment fragment);
}
