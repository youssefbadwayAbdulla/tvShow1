package com.example.tvshowsapp.models;

import com.google.gson.annotations.SerializedName;

public class Episode {
    @SerializedName("season")
    private String season;
    @SerializedName("episode")
    private String episode;
    @SerializedName("air_date")
    private String airDate;
    @SerializedName("name")
    private String name;

    public String getSeason() {
        return season;
    }

    public String getEpisode() {
        return episode;
    }

    public String getAirDate() {
        return airDate;
    }

    public String getName() {
        return name;
    }
}
