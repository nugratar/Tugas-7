package com.example.tugas7.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.tugas7.R;
import com.example.tugas7.helper.Cons;
import com.example.tugas7.models.television.TelevisionModel;
import com.example.tugas7.networks.MovieApiClient;
import com.example.tugas7.networks.MovieApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelevisionDetailActivity extends AppCompatActivity {

    private static final String TAG = "TVDetailActivity";
    public static String TELEVISION_ID = "Television_ID";
    private TextView tvTitle;
    private TextView tvGenre;
    private TextView tvRating;
    private TextView tvSeasons;
    private TextView tvEpisodes;
    private TextView tvOverview;
    private ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_television_detail);
        tvTitle = findViewById(R.id.tv_title);
        tvGenre = findViewById(R.id.tv_genre);
        tvRating = findViewById(R.id.tv_rating);
        tvSeasons = findViewById(R.id.tv_seasons);
        tvEpisodes = findViewById(R.id.tv_episodes);
        tvOverview = findViewById(R.id.tv_overview);
        ivPoster = findViewById(R.id.iv_poster);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadData(getIntent().getStringExtra(TELEVISION_ID));
    }

    private void loadData(String televisionID) {
        MovieApiInterface movieApiInterface = MovieApiClient.getRetrofit().create(MovieApiInterface.class);
        Call<TelevisionModel> televisionModelCall = movieApiInterface.getTelevision(televisionID, Cons.API_KEY);
        televisionModelCall.enqueue(new Callback<TelevisionModel>() {
            @Override
            public void onResponse(Call<TelevisionModel> call, Response<TelevisionModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    setActionBarTitle(response.body().getTitle());
                    tvTitle.setText(response.body().getTitle());
                    tvGenre.setText(response.body().getGenre());
                    tvRating.setText(response.body().getRating());
                    tvOverview.setText(response.body().getOverview());
                    tvSeasons.setText(response.body().getSeasons());
                    tvEpisodes.setText(response.body().getEpisodes());

                    Glide.with(TelevisionDetailActivity.this)
                            .load(Cons.BASE_IMAGE_URL + response.body().getImgUrl())
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    return false;
                                }
                            })
                            .into(ivPoster);
                } else {
                    Toast.makeText(TelevisionDetailActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TelevisionModel> call, Throwable t) {
                Log.d(TAG,"onFailure: " + t.getMessage());
                Toast.makeText(TelevisionDetailActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}