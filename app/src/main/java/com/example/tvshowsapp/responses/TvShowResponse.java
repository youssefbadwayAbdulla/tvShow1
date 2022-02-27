package com.example.tvshowsapp.responses;

import com.example.tvshowsapp.models.TvShow;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("pages")
    private int pages;
    @SerializedName("tv_shows")
    private List<TvShow>tvShows;

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public List<TvShow> getTvShows() {
        return tvShows;
    }
}
