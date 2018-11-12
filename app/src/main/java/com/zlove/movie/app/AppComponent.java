package com.zlove.movie.app;

import com.zlove.movie.details.DetailsComponent;
import com.zlove.movie.details.DetailsModule;
import com.zlove.movie.favorites.FavoritesModule;
import com.zlove.movie.listing.ListingComponent;
import com.zlove.movie.listing.ListingModule;
import com.zlove.movie.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules =
        {
            AppModule.class,
            NetworkModule.class,
            FavoritesModule.class
        })
public interface AppComponent {
        DetailsComponent plus(DetailsModule detailsModule);
        ListingComponent plus(ListingModule listingModule);
}
