package com.example.foodplannerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class RecipeViewPagerActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    static RecipeDB recipesDB;
    List<RecipeItem> mRecipies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_pager_activity);
        recipesDB = RecipeDB.get(this);
        viewPager2 = findViewById(R.id.viewPager2);
        mRecipies = new ArrayList<>();
        mRecipies = recipesDB.getAll();
        viewPager2.setAdapter(new RecipeFragmentStateAdapter(this, mRecipies, viewPager2));
    }

}