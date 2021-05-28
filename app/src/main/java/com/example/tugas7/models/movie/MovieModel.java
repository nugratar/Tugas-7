package com.example.tugas7.models.movie;

import android.annotation.SuppressLint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MovieModel {
    private String title;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    @Expose(serialize = false)
    private double rating;

    private String status;

    private String genre;

    private String overview;

    @SerializedName("imdb_id")
    private String imdbID;

    @SerializedName("poster_path")
    private String imgUrl;


    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern( "yyyy-MM-dd" )).format(DateTimeFormatter.ofPattern( "dd MMMM yyyy" ));
    }

    public String getRating() {
        return String.format("%.1f/10", rating);
    }

    public String getStatus() {
        return status;
    }

    public String getGenre() {
        return genre;
    }

    public String getOverview() {
        return overview;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
