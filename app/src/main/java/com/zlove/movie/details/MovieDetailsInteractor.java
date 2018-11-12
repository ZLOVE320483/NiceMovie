package com.zlove.movie.details;

import com.zlove.movie.model.Review;
import com.zlove.movie.model.Video;

import java.util.List;

import io.reactivex.Observable;

public interface MovieDetailsInteractor {

    Observable<List<Video>> getTrailers(String id);
    Observable<List<Review>> getReviews(String id);
}
