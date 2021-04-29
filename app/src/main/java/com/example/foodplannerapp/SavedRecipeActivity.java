package com.example.foodplannerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * This class is representing af list of saved recipes with a text and a picture.
 */

public class SavedRecipeActivity extends AppCompatActivity {

    private static SavedRecipeDB savedRecipeDB;
    private FragmentManager fm;
    Fragment fragmentSavedRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_list);

        savedRecipeDB = SavedRecipeDB.get(this); //ListActivity.this?
        fm = getSupportFragmentManager();

        fragmentSavedRecipes = fm.findFragmentById(R.id.container_saved_list);
        if (fragmentSavedRecipes == null) {
            fragmentSavedRecipes = new SavedFragment();
            fm.beginTransaction()
                    .add(R.id.container_saved_list, fragmentSavedRecipes)
                    .commit();
        }
    }
}
