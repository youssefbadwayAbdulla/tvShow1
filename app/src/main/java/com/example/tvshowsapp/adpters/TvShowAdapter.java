package com.example.tvshowsapp.adpters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshowsapp.R;
import com.example.tvshowsapp.databinding.ItemContainerTvShowsBinding;
import com.example.tvshowsapp.listeners.TvShowListener;
import com.example.tvshowsapp.models.TvShow;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowHolder> {
    private List<TvShow> tvShows;
    private TvShowListener tvShowListener;

    public TvShowAdapter(List<TvShow> tvShows, TvShowListener tvShowListener) {
        this.tvShows = tvShows;
        this.tvShowListener = tvShowListener;
    }

    @NonNull
    @Override
    public TvShowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TvShowHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.item_container_tv_shows, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowHolder holder, int position) {
        TvShow tvShow = tvShows.get(position);
        holder.bindTvShow(tvShow);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class TvShowHolder extends RecyclerView.ViewHolder {
        ItemContainerTvShowsBinding binding;

        public TvShowHolder(@NonNull ItemContainerTvShowsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTvShow(TvShow tvShow) {
            binding.setTvShow(tvShow);
            binding.executePendingBindings();
            binding.getRoot().setOnClickListener(view -> tvShowListener.onTvShowClicked(tvShow));
        }
    }

}
