package com.example.tvshowsapp.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tvshowsapp.network.ApiClient;
import com.example.tvshowsapp.responses.TvShowResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MostPopularShowRepository extends ViewModel {
        ApiClient apiClient = new ApiClient();
        public MutableLiveData<TvShowResponse> mutableLiveData = new MutableLiveData<>();
    private static String TAG = "MostPopularShowRepositoryModel";

    public void getMostPopularTvShows(int page) {
        Observable<TvShowResponse> observe = apiClient.getMostPopularTvShows(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observe.subscribe(new Observer<TvShowResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull TvShowResponse tvShowResponse) {
                mutableLiveData.setValue(tvShowResponse);
                Log.i(TAG, "onNext: " + tvShowResponse.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "onError: " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });
    }
}
