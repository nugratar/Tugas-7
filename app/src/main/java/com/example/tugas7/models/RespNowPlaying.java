package com.example.tugas7.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespNowPlaying {

    @SerializedName("results")
    private List<ModelNowPlaying> nowPlaying;

    public List<ModelNowPlaying> getNowPlaying() {
        return nowPlaying;
    }
}
