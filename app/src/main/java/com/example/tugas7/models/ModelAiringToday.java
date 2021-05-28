package com.example.tugas7.models;

import com.google.gson.annotations.SerializedName;

public class ModelAiringToday {
    private String id;

    @SerializedName("name")
    private String title;

    @SerializedName("poster_path")
    private String imageURL;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return imageURL;
    }
}
