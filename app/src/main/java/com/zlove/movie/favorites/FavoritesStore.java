package com.zlove.movie.favorites;

import com.zlove.movie.model.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;

@Singleton
public class FavoritesStore {

    private Realm realm;

    @Inject
    public FavoritesStore(Realm realm) {
        this.realm = realm;
    }

    public void setFavorite(Movie movie) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(new MovieRealmObject(movie));
        realm.commitTransaction();
    }

    public boolean isFavorite(String id) {
        MovieRealmObject res = realm.where(MovieRealmObject.class).equalTo(MovieRealmObject.COLUMN_NAME_ID, id).findFirst();
        return res != null;
    }

    public List<Movie> getFavorites() {
        RealmResults<MovieRealmObject> res = realm.where(MovieRealmObject.class).findAll();
        List<Movie> movies = new ArrayList<>();

        for (MovieRealmObject object : res) {
            movies.add(movieRealmObjectToMovie(object));
        }
        return movies;
    }

    public void unFavorite(String id) {
        realm.beginTransaction();
        MovieRealmObject movie = realm.where(MovieRealmObject.class).equalTo(MovieRealmObject.COLUMN_NAME_ID, id).findFirst();
        if(movie != null)
            movie.deleteFromRealm();
        realm.commitTransaction();
    }

    private Movie movieRealmObjectToMovie(MovieRealmObject movieRealmObject){
        Movie movie = new Movie();
        movie.setId(movieRealmObject.getId());
        movie.setOverview(movieRealmObject.getOverview());
        movie.setReleaseDate(movieRealmObject.getReleaseDate());
        movie.setPosterPath(movieRealmObject.getPosterPath());
        movie.setBackdropPath(movieRealmObject.getBackdropPath());
        movie.setTitle(movieRealmObject.getTitle());
        movie.setVoteAverage(movieRealmObject.getVoteAverage());

        return movie;
    }
}
