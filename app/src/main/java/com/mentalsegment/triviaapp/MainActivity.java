package com.mentalsegment.triviaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mentalsegment.triviaapp.fragments.HomeFragment;
import com.mentalsegment.triviaapp.fragments.ProfileFragment;
import com.mentalsegment.triviaapp.fragments.SettingsFragment;
import com.mentalsegment.triviaapp.fragments.ShopFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
     private BottomNavigationView.OnNavigationItemSelectedListener navListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
             Fragment selectedFragment=null;
             switch (menuItem.getItemId()){
                 case R.id.nav_menu_home:
                     selectedFragment= new HomeFragment();
                     break;
                 case R.id.nav_menu_profile:
                     selectedFragment= new ProfileFragment();
                     break;
                 case R.id.nav_menu_shop:
                     selectedFragment=new ShopFragment();
                     break;
                 case R.id.nav_menu_settings:
                     selectedFragment=new SettingsFragment();
                     break;
             }
             getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,selectedFragment ).commit();
             return true;
         }
     };

    private void init() {
        bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,new HomeFragment()).commit();

    }
}
