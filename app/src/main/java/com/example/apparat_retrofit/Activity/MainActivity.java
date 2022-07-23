package com.example.apparat_retrofit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apparat_retrofit.Fragment.AboutUsFragment;
import com.example.apparat_retrofit.Fragment.CatFragment;
import com.example.apparat_retrofit.Fragment.DownloadFragment;
import com.example.apparat_retrofit.Fragment.HomeFragment;
import com.example.apparat_retrofit.Fragment.MyvideoFragment;
import com.example.apparat_retrofit.R;
import com.example.apparat_retrofit.UserRegister;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Toolbar toolbar;
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    CreateAccountActivity createAccountActivity;
    UserRegister userRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createAccountActivity = new CreateAccountActivity();

        bottomNavigation = findViewById(R.id.bottom_navigatin_view);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);


        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Setup toggle to display hamburger icon with nice animation
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);


        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Inflate the header view at runtime
        // View headerLayout =  nvDrawer.inflateHeaderView(R.layout.nav_header);
        View headerView = nvDrawer.getHeaderView(0);

        ImageView headerPhoto = headerView.findViewById(R.id.img_nav_header);
        TextView headerTitle = headerView.findViewById(R.id.tv_title_header);
        // headerTitle.setText(createAccountActivity.textInputEditTextname.getText().toString());
        TextView view = headerView.findViewById(R.id.tv_header);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });


        // Setup drawer view
        setupDrawerContent(nvDrawer);

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }


    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.first_frag_home:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.secont_frag_video:
                fragmentClass = MyvideoFragment.class;
                break;
            case R.id.info:
                fragmentClass = AboutUsFragment.class;
                break;

            default:
                fragmentClass = HomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_fragment, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }


    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.icon_home:
                    Fragment fragment = new HomeFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_fragment, fragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.icon_cat:
                    Fragment fragment1 = new CatFragment();
                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                    fragmentTransaction1.replace(R.id.nav_fragment, fragment1);
                    fragmentTransaction1.commit();
                    break;
                case R.id.icon_download:
                    Fragment fragment2 = new DownloadFragment();
                    FragmentManager fragmentManager2 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                    fragmentTransaction2.replace(R.id.nav_fragment, fragment2);
                    fragmentTransaction2.commit();
                    break;
            }

            return false;
        }
    };


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        //return super.onOptionsItemSelected(item);
        return true;


    }
}
