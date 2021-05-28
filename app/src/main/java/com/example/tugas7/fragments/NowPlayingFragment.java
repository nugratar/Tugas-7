package com.example.tugas7.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tugas7.R;
import com.example.tugas7.adapters.AdapterNowPlaying;
import com.example.tugas7.helper.Cons;
import com.example.tugas7.helper.OnItemClickListener;
import com.example.tugas7.models.ModelNowPlaying;
import com.example.tugas7.models.RespNowPlaying;
import com.example.tugas7.networks.MovieApiClient;
import com.example.tugas7.networks.MovieApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingFragment extends Fragment implements OnItemClickListener<ModelNowPlaying> {

    private static final String TAG = "NowPlayingFragment";
    private RecyclerView rvNowPlaying;
    private AdapterNowPlaying adapterNowPlaying;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_now_playing, container, false);

        rvNowPlaying = v.findViewById(R.id.rv_now_playing);
        loadData();

        return v;
    }

    private void loadData() {
        MovieApiInterface movieApiInterface = MovieApiClient.getRetrofit().create(MovieApiInterface.class);
        Call<RespNowPlaying> respNowPlayingCall = movieApiInterface.getNowPlaying(Cons.API_KEY);
        respNowPlayingCall.enqueue(new Callback<RespNowPlaying>() {
            @Override
            public void onResponse(Call<RespNowPlaying> call, Response<RespNowPlaying> response) {
                if (response.isSuccessful() && response.body().getNowPlaying() != null) {
                    adapterNowPlaying = new AdapterNowPlaying(response.body().getNowPlaying(), NowPlayingFragment.this);
                }
            }

            @Override
            public void onFailure(Call<RespNowPlaying> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(ModelNowPlaying modelNowPlaying) {

    }
}