package com.icanerdogan.instragramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.icanerdogan.instragramclone.Fragments.HomeFragment;
import com.icanerdogan.instragramclone.Fragments.ProfileFragment;
import com.icanerdogan.instragramclone.Fragments.SearchFragment;

public class AppActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    ColorStateList myList = new ColorStateList(
            new int[][]{
                    new int[]{android.R.attr.state_enabled},
            },
            new int[] {
                    Color.BLACK,
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
        bottomNavigationView.setBackgroundColor(Color.WHITE);
        bottomNavigationView.setItemIconTintList(myList);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, new HomeFragment()).commit();

    }

    private  BottomNavigationView.OnNavigationItemSelectedListener navigationListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.bottom_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.bottom_search:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.bottom_profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }

            //Begin Transaction
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_layout,
                            selectedFragment).commit();

            return true;
        }
    };
}