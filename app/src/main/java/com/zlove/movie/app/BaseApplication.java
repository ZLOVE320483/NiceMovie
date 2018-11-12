package com.zlove.movie.app;

import android.app.Application;
import android.os.StrictMode;

import com.zlove.movie.details.DetailsComponent;
import com.zlove.movie.details.DetailsModule;
import com.zlove.movie.favorites.FavoritesModule;
import com.zlove.movie.listing.ListingComponent;
import com.zlove.movie.listing.ListingModule;
import com.zlove.movie.network.NetworkModule;

import io.realm.Realm;

public class BaseApplication extends Application {

    private AppComponent appComponent;
    private DetailsComponent detailsComponent;
    private ListingComponent listingComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.enableDefaults();
        initRealm();
        appComponent = createAppComponent();
    }

    private void initRealm() {
        Realm.init(this);
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .favoritesModule(new FavoritesModule())
                .build();
    }

    public DetailsComponent createDetailsComponent() {
        detailsComponent = appComponent.plus(new DetailsModule());
        return detailsComponent;
    }

    public void releaseDetailsComponent() {
        detailsComponent = null;
    }

    public ListingComponent createListingComponent() {
        listingComponent = appComponent.plus(new ListingModule());
        return listingComponent;
    }

    public void releaseListingComponent()
    {
        listingComponent = null;
    }

    public ListingComponent getListingComponent()
    {
        return listingComponent;
    }

}
