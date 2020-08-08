package com.example.abhishek.speakup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import static com.example.abhishek.speakup.R.styleable.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        ImageButton imgbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgbtn=(ImageButton)findViewById(R.id.imageButton);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sound.class);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RelativeLayout main_view =(RelativeLayout)findViewById(R.id.content_main);
        switch (item.getItemId())
        {
            case R.id.darkmenu:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.DKGRAY);
                return true;
            case R.id.brightmenu:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.WHITE);
                return true;
            case R.id.greymenu:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.GRAY);
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Import) {
            Intent intent = new Intent(MainActivity.this, Import.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, Gallery.class);
            startActivity(intent);

        }else if (id == R.id.Contact) {
            Intent intent = new Intent(MainActivity.this, ContactMainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.About) {
            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
