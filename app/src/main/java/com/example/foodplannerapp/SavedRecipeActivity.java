package com.example.foodplannerapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SavedRecipeActivity extends AppCompatActivity{
    ViewPager2 viewPager2;
    static SavedRecipeDB mSavedRecipesDB;
    List<RecipeItem>tempList;
    List<RecipeItem> mRecipies;


@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_saved_list);
    mSavedRecipesDB = SavedRecipeDB.get(this);
    viewPager2 = findViewById(R.id.viewPager_saved);
    mRecipies = new ArrayList<>();
    tempList = new ArrayList<>();
    tempList = mSavedRecipesDB.getSavedRecipeList();

    for (RecipeItem ri : tempList) {
        String name = ri.getRecipeName();
        String guide = ri.getRecipeGuide();
        if (!name.equals("") && !guide.equals("")) {
            mRecipies.add(ri);
        }
    }

        viewPager2.setAdapter(new SavedFragment(this, mRecipies, viewPager2));

    }
}