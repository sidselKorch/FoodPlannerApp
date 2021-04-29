package com.example.foodplannerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // GUI variables
    private FragmentManager fm;
    Fragment fragmentUI;
    Fragment fragmentList;
    Fragment fragmentAdd;

    // Model: Database of items
    private static RecipeDB recipesDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodplanner);
        recipesDB = RecipeDB.get(this);
        fm = getSupportFragmentManager();
        setUpFragments();
    }


    private void setUpFragments() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentUI= fm.findFragmentById(R.id.container_ui_landscape);
            fragmentList= fm.findFragmentById(R.id.recipe_container_list);
            if ((fragmentUI == null) && (fragmentList == null) && (fragmentAdd == null)) {
                fragmentUI= new UIFragment();
                fragmentList= new RecipeFragment();
                fm.beginTransaction()
                        .add(R.id.container_ui_landscape, fragmentUI)
                        .add(R.id.recipe_container_list, fragmentList)
                        .commit();
            }
        } else {
            //Orientation portrait
            fragmentUI= fm.findFragmentById(R.id.container_ui_portrait);
            if (fragmentUI== null) {
                fragmentUI = new UIFragment();
                fm.beginTransaction()
                        .add(R.id.container_ui_portrait, fragmentUI)
                        .commit();
            }
        }
    }
}