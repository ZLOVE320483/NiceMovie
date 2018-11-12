package com.zlove.movie.favorites;

import com.zlove.movie.model.Movie;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MovieRealmObject extends RealmObject {
    @PrimaryKey
    private String id;

    private String overview;
    private String releaseDate;
    private String posterPath;
    private String backdropPath;
    private String title;
    private double voteAverage;

    public static final String COLUMN_NAME_ID = "id";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public MovieRealmObject(Movie movie) {
        id = movie.getId();
        overview = movie.getOverview();
        releaseDate = movie.getReleaseDate();
        posterPath = movie.getPosterPath();
        backdropPath = movie.getBackdropPath();
        title = movie.getTitle();
        voteAverage = movie.getVoteAverage();
    }

    public MovieRealmObject() {
    }
}
