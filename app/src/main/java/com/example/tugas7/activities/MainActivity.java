package com.example.tugas7.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.tugas7.R;
import com.example.tugas7.fragments.AiringTodayFragment;
import com.example.tugas7.fragments.NowPlayingFragment;
import com.example.tugas7.fragments.UpcomingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;
    private Map<Integer, Fragment> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frame_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.item_now_playing, new NowPlayingFragment());
        fragmentMap.put(R.id.item_airing_today, new AiringTodayFragment());
        fragmentMap.put(R.id.item_upcoming, new UpcomingFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.item_now_playing);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = fragmentMap.get(item.getItemId());
        assert fragment != null;
        switch (item.getItemId()) {
            case R.id.item_now_playing:
                getSupportActionBar().setTitle("Now Playing");
                break;
            case R.id.item_airing_today:
                getSupportActionBar().setTitle("Airing Today");
                break;
            case R.id.item_upcoming:
                getSupportActionBar().setTitle("Upcoming");
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
        return true;
    }

}