package com.zlove.movie.network;

import com.zlove.movie.model.MoviesWrapper;
import com.zlove.movie.model.ReviewsWrapper;
import com.zlove.movie.model.VideoWrapper;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbWebService {

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    Observable<MoviesWrapper> popularMovies(@Query("page") int page);

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    Observable<MoviesWrapper> highestRatedMovies(@Query("page") int page);

    @GET("3/discover/movie?language=en&sort_by=elease_date.desc")
    Observable<MoviesWrapper> newestMovies(@Query("release_date.lte") String maxReleaseDate, @Query("vote_count.gte") int minVoteCount);

    @GET("3/movie/{movieId}/videos")
    Observable<VideoWrapper> trailers(@Path("movieId") String movieId);

    @GET("3/movie/{movieId}/reviews")
    Observable<ReviewsWrapper> reviews(@Path("movieId") String movieId);

    @GET("3/search/movie?language=en-US&page=1")
    Observable<MoviesWrapper> searchMovies(@Query("query") String searchQuery);
}
