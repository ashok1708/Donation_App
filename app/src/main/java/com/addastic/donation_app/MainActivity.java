package com.addastic.donation_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;



import com.addastic.donation_app.fragments.HomeFragment;
import com.addastic.donation_app.fragments.ListFragment;
import com.addastic.donation_app.fragments.PayFragment;
import com.addastic.donation_app.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigation;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       bottomNavigation = findViewById(R.id.bottom_navigation);
       toolbar=findViewById(R.id.toolbar);

        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(HomeFragment.newInstance("", ""));

    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
             new BottomNavigationView.OnNavigationItemSelectedListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                toolbar.setTitle("Home");
                            }
                            openFragment(HomeFragment.newInstance("", ""));
                            return true;
                        case R.id.action_pay:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                toolbar.setTitle("Payment");
                            }
                            openFragment(PayFragment.newInstance("", ""));
                            return true;
                        case R.id.action_list:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                toolbar.setTitle("List");
                            }
                            openFragment(ListFragment.newInstance("", ""));
                            return true;
                        case R.id.action_profile:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                toolbar.setTitle("Profile");
                            }
                            openFragment(ProfileFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };

    public void setNavigationVisibility(boolean visible) {
        if (bottomNavigation.isShown() && !visible) {

            bottomNavigation.animate().translationY(bottomNavigation.getHeight());
            //bottomNavigation.setVisibility(View.GONE);
        }
        else if (!bottomNavigation.isShown() && visible){
            bottomNavigation.animate().translationY(0);
            //bottomNavigation.setVisibility(View.VISIBLE);
        }
    }
    public void hideBottomNavigationView() {
        bottomNavigation.animate().translationY(bottomNavigation.getHeight());
    }

    public void showBottomNavigationView() {
        bottomNavigation.animate().translationY(0);
    }

}
