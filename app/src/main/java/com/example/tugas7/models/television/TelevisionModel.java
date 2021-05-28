package com.example.tugas7.models.television;

import com.google.gson.annotations.SerializedName;

public class TelevisionModel {
    @SerializedName("name")
    private String title;

    private String genre;

    @SerializedName("vote_average")
    private double rating;

    @SerializedName("number_of_seasons")
    private int seasons;

    @SerializedName("number_of_episodes")
    private int episodes;

    private String overview;

    @SerializedName("poster_path")
    private String imgUrl;

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getRating() {
        return String.format("%.1f/10", rating);
    }

    public String getSeasons() {
        return Integer.toString(seasons);
    }

    public String getEpisodes() {
        return Integer.toString(episodes);
    }

    public String getOverview() {
        return overview;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
