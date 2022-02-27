package com.example.tvshowsapp.network;

import com.example.tvshowsapp.responses.TvShowDetailsResponse;
import com.example.tvshowsapp.responses.TvShowResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("most-popular")
    Observable<TvShowResponse>getMostPopularTvShows(@Query("page")int page);
    @GET("show-details")
    Observable<TvShowDetailsResponse>getTvShowDetails(@Query("q")String tvShowId);
}
