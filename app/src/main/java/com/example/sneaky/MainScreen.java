package com.example.sneaky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sneaky.databinding.ActivityMainScreenBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainScreen extends AppCompatActivity {

    ActivityMainScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main_screen);
        setContentView(binding.getRoot());

        FragmentManager homeMan = getSupportFragmentManager();

        FragmentTransaction homeTrans = homeMan.beginTransaction();

        homeTrans.replace(R.id.content,new HomeFragment());
//        homeTrans.addToBackStack(null);
        homeTrans.commit();


        binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager homeMan = getSupportFragmentManager();
                FragmentTransaction homeTrans = homeMan.beginTransaction();
//                homeTrans.addToBackStack(null);
                switch (item.getItemId()){
                    case R.id.home:
                        homeTrans.replace(R.id.content,new HomeFragment());
                        homeTrans.commit();
//                        Toast.makeText(MainScreen.this, "Home Selected", Toast.LENGTH_SHORT).show();
                        break;
                }
                switch (item.getItemId()){
                    case R.id.trending:
                        homeTrans.replace(R.id.content,new trendingFragment());
                        homeTrans.commit();

//                        Toast.makeText(MainScreen.this, "Group Selected", Toast.LENGTH_SHORT).show();
                        break;
                }
                switch (item.getItemId()){
                    case R.id.cart:
                        homeTrans.replace(R.id.content,new cartFragment());
                        homeTrans.commit();
//                        Toast.makeText(MainScreen.this, "Table Selected", Toast.LENGTH_SHORT).show();
                        break;
                }
                switch (item.getItemId()){
                    case R.id.profile:
                        homeTrans.replace(R.id.content,new profileFragment());
                        homeTrans.commit();
//                        Toast.makeText(MainScreen.this, "Account Selected", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });
    }
}