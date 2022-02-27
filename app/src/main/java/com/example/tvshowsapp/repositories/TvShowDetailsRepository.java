package com.example.tvshowsapp.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tvshowsapp.network.ApiClient;
import com.example.tvshowsapp.responses.TvShowDetailsResponse;
import com.example.tvshowsapp.responses.TvShowResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TvShowDetailsRepository extends ViewModel {
    ApiClient apiClient = new ApiClient();
    public MutableLiveData<TvShowDetailsResponse> mutableLiveData = new MutableLiveData<>();
    private static final String TAG = "TvShowDetailsRepository";

    public void getTvShowDetails(String tvShowId) {
        Observable<TvShowDetailsResponse> data = apiClient.getTvShowDetails(tvShowId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        data.subscribe(new Observer<TvShowDetailsResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull TvShowDetailsResponse tvShowDetailsResponse) {
                mutableLiveData.setValue(tvShowDetailsResponse);
                Log.i(TAG, "onNext: "+tvShowDetailsResponse.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "onError: "+e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });


    }
}
