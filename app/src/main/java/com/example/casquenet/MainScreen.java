package com.example.casquenet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.casquenet.main_screen_fragments.Appareil;
import com.example.casquenet.main_screen_fragments.InformationsMedicales;
import com.example.casquenet.main_screen_fragments.Maps;
import com.example.casquenet.main_screen_fragments.Settings;
import com.example.casquenet.main_screen_fragments.UserProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreen extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finishAffinity();
        }
    }




    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Manually select the first item (Map fragment)
        bottomNavigationView.setSelectedItemId(R.id.bottom_maps);

        // Create a container object to hold the selected fragment
        final Fragment[] selectedFragment = {new Maps()};
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, selectedFragment[0])
                .commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int previousItem = bottomNavigationView.getSelectedItemId();
            int selectedItem = item.getItemId();

            if (previousItem != selectedItem) {
                // Update the selected fragment in the container object
                switch (selectedItem) {
                    case R.id.bottom_maps:
                        selectedFragment[0] = new Maps();
                        break;
                    case R.id.bottom_informations:
                        selectedFragment[0] = new InformationsMedicales();
                        break;
                    case R.id.bottom_profile:
                        selectedFragment[0] = new UserProfile();
                        break;
                    case R.id.bottom_Apparail:
                        selectedFragment[0] = new Appareil();
                        break;
                    case R.id.bottom_Parametres:
                        selectedFragment[0] = new Settings();
                        break;
                    default:
                        return false;
                }

                // Replace the current fragment with the selected fragment
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Define the custom animations for the fragment transition
                if (selectedItem > previousItem) {
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                } else {
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                }

                transaction.replace(R.id.fragmentContainer, selectedFragment[0]);
                transaction.commit();

            }

            return true;
        });


    }       ////
























}           /////
