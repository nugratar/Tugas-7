package com.example.tugas7.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tugas7.R;
import com.example.tugas7.activities.TelevisionDetailActivity;
import com.example.tugas7.adapters.AdapterAiringToday;
import com.example.tugas7.helper.Cons;
import com.example.tugas7.helper.OnItemClickListener;
import com.example.tugas7.models.ModelAiringToday;
import com.example.tugas7.models.RespAiringToday;
import com.example.tugas7.networks.MovieApiClient;
import com.example.tugas7.networks.MovieApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AiringTodayFragment extends Fragment implements OnItemClickListener<ModelAiringToday> {

    private static final String TAG = "AiringTodayFragment";
    private RecyclerView rvAiringToday;
    private AdapterAiringToday adapterAiringToday;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_airing_today, container, false);
        rvAiringToday = v.findViewById(R.id.rv_airing_today);
        loadData();
        return v;
    }

    private void loadData() {
        MovieApiInterface movieApiInterface = MovieApiClient.getRetrofit().create(MovieApiInterface.class);
        Call<RespAiringToday> respAiringTodayCall = movieApiInterface.getAiringToday(Cons.API_KEY);
        respAiringTodayCall.enqueue(new Callback<RespAiringToday>() {
            @Override
            public void onResponse(Call<RespAiringToday> call, Response<RespAiringToday> response) {
                if (response.isSuccessful() && response.body().getAiringToday() != null) {
                    adapterAiringToday = new AdapterAiringToday(response.body().getAiringToday(), AiringTodayFragment.this);
                    rvAiringToday.setAdapter(adapterAiringToday);
                } else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<RespAiringToday> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Failed" + t.getLocalizedMessage(), Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public void onClick(ModelAiringToday modelAiringToday) {
        Intent intent = new Intent(getActivity(), TelevisionDetailActivity.class);
//        intent.putExtra(TelevisionDetailActivity.Tele)
        startActivity(intent);
    }
}