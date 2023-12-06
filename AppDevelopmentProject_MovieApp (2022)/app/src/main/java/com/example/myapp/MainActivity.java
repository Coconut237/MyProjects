package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Listener for overall BottomNavigation
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    /**
     * If clicked on BottomNavigationIcon:
     * Navigate to particular fragment (Home | WatchList | Search)
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    NavController navController= Navigation.findNavController(findViewById(R.id.nav_host_fragment));

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            navController.navigate(R.id.fragment_home);
                            break;
                        case R.id.nav_watchlist:
                            navController.navigate(R.id.fragment_watchlist);
                            break;
                        case R.id.nav_search:
                            navController.navigate(R.id.fragment_search);
                            break;
                    }
                    return true;
                }
            };

}

