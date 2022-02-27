package com.example.tvshowsapp.network;

import com.example.tvshowsapp.responses.TvShowDetailsResponse;
import com.example.tvshowsapp.responses.TvShowResponse;
import com.google.gson.Gson;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL=" https://www.episodate.com/api/";
    private static Retrofit retrofit;
    public static ApiServices getInstance(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofit.create(ApiServices.class);
    }
public Observable<TvShowResponse>getMostPopularTvShows(int page){
        return getInstance().getMostPopularTvShows(page);
}
public Observable<TvShowDetailsResponse>getTvShowDetails(String tvShowId){
        return getInstance().getTvShowDetails(tvShowId);

}
}
