package com.zlove.movie.details;

import com.zlove.movie.model.Movie;
import com.zlove.movie.model.Review;
import com.zlove.movie.model.Video;

import java.util.List;

public interface MovieDetailsView {
    void showDetails(Movie movie);
    void showTrailers(List<Video> trailers);
    void showReviews(List<Review> reviews);
    void showFavorited();
    void showUnFavorited();
}
