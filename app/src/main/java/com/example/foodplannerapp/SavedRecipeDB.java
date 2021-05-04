package com.example.foodplannerapp;

import android.content.Context;
import android.media.Image;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * This class is representing af database of saved recipes with a text, the recipes should be added
 * from the recipeDB list
 * */

public class SavedRecipeDB extends Observable {
    private static final String TAG = "myLOG";
    private static final List<RecipeItem> savedRecipeDB = new ArrayList<>();
    private static SavedRecipeDB mSavedRecipeDB;
    private static Context sContext;

    private SavedRecipeDB (Context context) {
    }


    public static SavedRecipeDB  get(Context context)  {
        if (mSavedRecipeDB == null) {
            sContext = context;
            mSavedRecipeDB = new SavedRecipeDB (context);
        }
        return mSavedRecipeDB;
    }

    public List<RecipeItem> getSavedRecipeList() {return savedRecipeDB; }

    public String listItems() {
        String r = "";
        for (RecipeItem recipeItem : savedRecipeDB) {
            r += "Recipename: " + recipeItem.getRecipeName() + "\n" + "Guide: " + recipeItem.getRecipeGuide();
        }
        return r;
    }

    public int size() { return savedRecipeDB.size(); }

    //Emil: denne metode skal så connectes til saved knappen.
    public void addRecipeToSaved(RecipeItem recipeItem) {
        if (!savedRecipeDB.contains(recipeItem)) savedRecipeDB.add(recipeItem);
    }

    public void removeItem(String recipeName){
        if (savedRecipeDB.contains(recipeName))  savedRecipeDB.remove(recipeName);
        this.setChanged();
        notifyObservers();
    }

    public static RecipeItem getSavedRecipeItem(String recipeName){
        RecipeItem recipe = new RecipeItem();
        for (RecipeItem recipeItem : savedRecipeDB
             ) { recipe = recipeItem;

        }
        return recipe;
    }

    public List<RecipeItem> getAllSavedRecipees() { //not sure the second paramter in new RecipeItem
        List<RecipeItem> result= new ArrayList<>();
        for (RecipeItem recipeItem : savedRecipeDB) {
            result.add(new RecipeItem());
        }
        return result;
    }

}
