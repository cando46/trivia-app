package com.mentalsegment.triviaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mentalsegment.triviaapp.fragments.HomeFragment;
import com.mentalsegment.triviaapp.fragments.ProfileFragment;
import com.mentalsegment.triviaapp.fragments.SettingsFragment;
import com.mentalsegment.triviaapp.fragments.ShopFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout_main);
        NavigationView navigationView = findViewById(R.id.nav_draw_main);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.nav_menu_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_menu_profile:
                    selectedFragment = new ProfileFragment();
                    break;
                case R.id.nav_menu_shop:
                    selectedFragment = new ShopFragment();
                    break;
                case R.id.nav_menu_settings:
                    selectedFragment = new SettingsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, selectedFragment).commit();
            return true;
        }
    };

    private void init() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new HomeFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_draw_message:
                onClickMessageNavDraw();
                break;
            case R.id.nav_draw_chat:
                onClickChatNavDraw();
                break;
            case R.id.nav_draw_profile:
                onClickProfileNavDraw();
                break;
            case R.id.nav_draw_share:
                onClickShareNavDraw();
                break;
            case R.id.nav_draw_send:
                onClickSendNavDraw();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onClickSendNavDraw() {
        Intent intent=new Intent(this,SendMessageActivity.class);
        startActivity(intent);

    }

    private void onClickShareNavDraw() {
        Toast.makeText(this, "It's not working yet!", Toast.LENGTH_LONG).show();
    }

    private void onClickProfileNavDraw() {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.main_fragment_container,new ProfileFragment()).commit();
    }

    private void onClickChatNavDraw() {
        Toast.makeText(this, "It's not working yet!", Toast.LENGTH_LONG).show();

    }

    private void onClickMessageNavDraw() {
        Intent intent=new Intent(this,MessageActivity.class);
        startActivity(intent);

    }
}
