package com.example.tugas7.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespAiringToday {

    @SerializedName("results")
    private List<ModelAiringToday> airingToday;

    public List<ModelAiringToday> getAiringToday() {
        return airingToday;
    }
}
