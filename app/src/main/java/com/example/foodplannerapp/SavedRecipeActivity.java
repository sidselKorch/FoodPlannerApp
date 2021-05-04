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
    tempList = new ArrayList<>();
    tempList = mSavedRecipesDB.getSavedRecipeList();
    mRecipies = new ArrayList<>();
    mRecipies = sortList((ArrayList<RecipeItem>) tempList);

    viewPager2.setAdapter(new SavedFragment(this, mRecipies, viewPager2));

    }

    private ArrayList<RecipeItem> sortList(ArrayList<RecipeItem> firstTempList) {
        ArrayList<RecipeItem> returnableList = new ArrayList<>();
        ArrayList<RecipeItem> secondTempList = new ArrayList<>();

        for (RecipeItem ri : firstTempList) {
            String name = ri.getRecipeName();
            String guide = ri.getRecipeGuide();
            if (!name.equals("") && !guide.equals("")) {
                secondTempList.add(ri);
            }
        }

        for (RecipeItem recipeItem : secondTempList) {
            boolean isFound = false;

            for (RecipeItem r : returnableList) {
                if (r.getRecipeName().equals(recipeItem.getRecipeName()) || (r.equals(recipeItem))) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) returnableList.add(recipeItem);
        }
        return returnableList;
    }
}