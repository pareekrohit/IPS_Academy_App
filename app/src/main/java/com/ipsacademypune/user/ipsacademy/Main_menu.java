package com.ipsacademypune.user.ipsacademy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class Main_menu extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //For Displaying the home back button on actionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


//On Click on any navigation item.........................................................................
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            android.support.v4.app.Fragment fragment = null;
            Bundle args = new Bundle();

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                item.setChecked(true);
                switch (item.getItemId()) {

                    case R.id.nav_home:
                        getSupportActionBar().setTitle(R.string.app_name);
                        Intent i=new Intent(Main_menu.this,Main_menu.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_Facebook:
                        getSupportActionBar().setTitle("Facebook Page");
                        //Toast.makeText(Main_menu.this, "Inside Facebook", Toast.LENGTH_SHORT).show();
                        args.putString("response","facebook");
                        fragment = new WebSite_Fragment();
                        fragment.setArguments(args);

                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_Linkedin:
                        getSupportActionBar().setTitle("Linkedin");
                        args.putString("response","linkedin");
                        fragment = new WebSite_Fragment();
                        fragment.setArguments(args);

                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_student_say:
                        getSupportActionBar().setTitle("Student Comment");
                        fragment = new Student_Say_fragment();
                       // Toast.makeText(Main_menu.this, "Map Clicked", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_website:
                        getSupportActionBar().setTitle("Our Website");
                        args.putString("response","website");
                        fragment = new WebSite_Fragment();
                        fragment.setArguments(args);
                        mDrawerLayout.closeDrawers();
                        break;

                }

                //Fragment Coding..............
                if (fragment != null) {
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.frame_layout, fragment);
                    ft.commit();
                }
                //..............................
                return true;
            }
        });

//Hamburger icon in the application.....................................................
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void showVideo(View v){
        Intent i=new Intent(Main_menu.this,Videos.class);
        startActivity(i);
    }


    public void showAbout_us(View v){
        Intent i=new Intent(Main_menu.this,About_us.class);
        startActivity(i);
    }

    public void showPictures(View v){
        Intent i=new Intent(Main_menu.this,Picture.class);
        startActivity(i);
    }

    public void showDailyNews(View v){
        Intent i=new Intent(Main_menu.this,Daily_News.class);
        startActivity(i);
    }
}

