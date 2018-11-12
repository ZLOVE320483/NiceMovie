package com.zlove.movie.details;

import com.zlove.movie.model.Review;
import com.zlove.movie.model.ReviewsWrapper;
import com.zlove.movie.model.Video;
import com.zlove.movie.model.VideoWrapper;
import com.zlove.movie.network.TmdbWebService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MovieDetailsInteractorImpl implements MovieDetailsInteractor {

    private TmdbWebService tmdbWebService;

    public MovieDetailsInteractorImpl(TmdbWebService tmdbWebService) {
        this.tmdbWebService = tmdbWebService;
    }

    @Override
    public Observable<List<Video>> getTrailers(String id) {
        return tmdbWebService.trailers(id).map(new Function<VideoWrapper, List<Video>>() {
            @Override
            public List<Video> apply(VideoWrapper videoWrapper) throws Exception {
                return videoWrapper.getVideos();
            }
        });
    }

    @Override
    public Observable<List<Review>> getReviews(String id) {
        return tmdbWebService.reviews(id).map(new Function<ReviewsWrapper, List<Review>>() {
            @Override
            public List<Review> apply(ReviewsWrapper reviewsWrapper) throws Exception {
                return reviewsWrapper.getReviews();
            }
        });
    }
}
