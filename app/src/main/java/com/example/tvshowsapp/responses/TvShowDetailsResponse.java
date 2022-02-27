package com.example.tvshowsapp.responses;

import com.example.tvshowsapp.models.TvShowDetails;
import com.google.gson.annotations.SerializedName;

public class TvShowDetailsResponse {
    @SerializedName("tvShow")
    private TvShowDetails tvShowDetails;

    public TvShowDetails getTvShowDetails() {
        return tvShowDetails;
    }
}
