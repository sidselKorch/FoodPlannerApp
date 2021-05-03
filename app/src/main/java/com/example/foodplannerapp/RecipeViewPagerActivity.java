package com.example.foodplannerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewPagerActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    static RecipeDB recipesDB;
    List<RecipeItem> tempList;
    List<RecipeItem> mRecipies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_pager_activity);
        recipesDB = RecipeDB.get(this);

        viewPager2 = findViewById(R.id.viewPager2);
        mRecipies = new ArrayList<>();
        tempList = new ArrayList<>();
        tempList = recipesDB.getAll();

        for (RecipeItem ri : tempList) {
            String name = ri.getRecipeName();
            String guide = ri.getRecipeGuide();
            if (!name.equals("") && !guide.equals("")) {
                mRecipies.add(ri);
            }
        }

        viewPager2.setAdapter(new RecipeFragmentStateAdapter(this, mRecipies, viewPager2));

    }

}