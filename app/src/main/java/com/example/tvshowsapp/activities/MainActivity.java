package com.example.tvshowsapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tvshowsapp.R;
import com.example.tvshowsapp.adpters.TvShowAdapter;
import com.example.tvshowsapp.databinding.ActivityMainBinding;
import com.example.tvshowsapp.listeners.TvShowListener;
import com.example.tvshowsapp.models.TvShow;
import com.example.tvshowsapp.repositories.MostPopularShowRepository;
import com.example.tvshowsapp.responses.TvShowResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TvShowListener {
    ActivityMainBinding binding;
    private MostPopularShowRepository viewModel;
    private static final String TAG = "youssef MainActivity";
    private TvShowAdapter tvShowAdapter;
    private List<TvShow> tvShows = new ArrayList<>();
    private int currentPage = 1;
    private int totalAvailablePages = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialzation();


    }

    private void doInitialzation() {
        viewModel = ViewModelProviders.of(this).get(MostPopularShowRepository.class);
        tvShowAdapter = new TvShowAdapter(tvShows,this);
        binding.rvTvShows.setAdapter(tvShowAdapter);
        binding.rvTvShows.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (binding.rvTvShows.canScrollVertically(1)) {
                    if (currentPage<=totalAvailablePages) {
                        currentPage+=1;
                        getMostPopularTvShow();
                    }
                }
            }
        });
        getMostPopularTvShow();
    }

    private void getMostPopularTvShow() {
        toggleLoading();
        viewModel.getMostPopularTvShows(currentPage);
        viewModel.mutableLiveData.observe(this, new Observer<TvShowResponse>() {
            @Override
            public void onChanged(TvShowResponse tvShowResponse) {
                toggleLoading();
                if (tvShowResponse != null) {
                    totalAvailablePages = tvShowResponse.getPages();
                    if (tvShowResponse.getTvShows() != null) {
                        int oldCount=tvShows.size();
                        tvShows.addAll(tvShowResponse.getTvShows());
                        tvShowAdapter.notifyItemRangeInserted(oldCount,tvShows.size());

                    }
                }
            }
        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            if (binding.getIsLoading() != null && binding.getIsLoading()) {
                binding.setIsLoading(false);
            } else {
                binding.setIsLoading(true);
            }
        } else {
            if (binding.getIsLoadingMore() != null && binding.getIsLoadingMore()) {
                binding.setIsLoadingMore(false);

            } else {
                binding.setIsLoadingMore(true);
            }

        }
    }

    @Override
    public void onTvShowClicked(TvShow tvShow) {
        Intent intent=new Intent(getApplicationContext(),TvShowDetailsActivity.class);
        intent.putExtra("id",tvShow.getId());
        intent.putExtra("name",tvShow.getName());
        intent.putExtra("startDate",tvShow.getStartDate());;
        intent.putExtra("country",tvShow.getCountry());
        intent.putExtra("network",tvShow.getNetwork());
        intent.putExtra("status",tvShow.getStatus());
        startActivity(intent);
    }
}