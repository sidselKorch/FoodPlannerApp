package com.example.foodplannerapp;

import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.foodplannerapp.Database.RecipeItemBaseHelper;

public class MainActivity extends AppCompatActivity {

    // GUI variables
    private FragmentManager fm;
    Fragment fragmentUI;
    Fragment fragmentList;
    Fragment fragmentAdd;
    private static SQLiteDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodplanner);
        fm = getSupportFragmentManager();
        setUpFragments();
        mDatabase = new RecipeItemBaseHelper(this).getWritableDatabase();

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