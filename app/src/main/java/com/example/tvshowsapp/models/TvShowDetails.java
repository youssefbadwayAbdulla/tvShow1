package com.example.tvshowsapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowDetails {
    @SerializedName("url")
    private String url;
    @SerializedName("description")
    private String description;
    @SerializedName("runtime")
    private String runtime;
    @SerializedName("rating")
    private String rating;
    @SerializedName("image_path")
    private String image_path;
    @SerializedName("genres")
    private String[] genres;
    @SerializedName("pictures")
    private String[] pictures;
    @SerializedName("episodes")
    private List<Episode>episodes;

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getRating() {
        return rating;
    }

    public String getImage_path() {
        return image_path;
    }

    public String[] getGenres() {
        return genres;
    }

    public String[] getPictures() {
        return pictures;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
