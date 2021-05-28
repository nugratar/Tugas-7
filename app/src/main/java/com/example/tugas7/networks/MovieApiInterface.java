package com.example.tugas7.networks;

import com.example.tugas7.models.RespNowPlaying;
import com.example.tugas7.models.RespAiringToday;
import com.example.tugas7.models.movie.MovieModel;
import com.example.tugas7.models.television.TelevisionModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiInterface {
    @GET("movie/now_playing")
    Call<RespNowPlaying> getNowPlaying(
            @Query("api_key") String apiKey
    );

    @GET("movie/popular")
    Call<RespAiringToday> getAiringToday(
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}")
    Call<MovieModel> getMovie(
            @Path("movie_id") String id,
            @Query("api_key") String apiKey
    );

    @GET("tv/{tv_id}")
    Call<TelevisionModel> getTelevision(
            @Path("movie_id") String id,
            @Query("api_key") String apiKey
    );
}
