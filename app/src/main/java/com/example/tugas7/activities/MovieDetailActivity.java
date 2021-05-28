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
import com.example.tugas7.models.movie.MovieModel;
import com.example.tugas7.networks.MovieApiClient;
import com.example.tugas7.networks.MovieApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = "MovieDetailActivity";
    public static String MOVIE_ID = "Movie_ID";
    private TextView tvTitle;
    private TextView tvReleaseDate;
    private TextView tvRating;
    private TextView tvStatus;
    private TextView tvGenre;
    private TextView tvOverview;
    private ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        tvTitle = findViewById(R.id.tv_title);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvRating = findViewById(R.id.tv_rating);
        tvStatus = findViewById(R.id.tv_status);
        tvGenre = findViewById(R.id.tv_genre);
        tvOverview = findViewById(R.id.tv_overview);
        ivPoster = findViewById(R.id.iv_poster);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadData(getIntent().getStringExtra(MOVIE_ID));
    }

    private void loadData(String movieID) {
        MovieApiInterface movieApiInterface = MovieApiClient.getRetrofit().create(MovieApiInterface.class);
        Call<MovieModel> movieModelCall = movieApiInterface.getMovie(movieID, Cons.API_KEY);
        movieModelCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    setActionBarTitle(response.body().getTitle());
                    tvTitle.setText(response.body().getTitle());
                    tvReleaseDate.setText(response.body().getReleaseDate());
                    tvRating.setText(response.body().getRating());
                    tvStatus.setText(response.body().getStatus());
                    tvGenre.setText(response.body().getGenre());
                    tvOverview.setText(response.body().getOverview());

                    Glide.with(MovieDetailActivity.this)
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
                    Toast.makeText(MovieDetailActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(MovieDetailActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}