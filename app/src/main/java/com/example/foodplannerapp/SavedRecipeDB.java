package com.example.foodplannerapp;

import android.content.Context;
import android.media.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * This class is representing af database of saved recipes with a text and a picture. we should be able to add
 * recipes to this list by swiping right in the Recipe_List_Activity.
 * We should also be able to list all the recipes and delete them from the saved list.
 */

public class SavedRecipeDB extends Observable {
    private static final String TAG = "myLOG";
    private static final Map<String, RecipeItem> savedRecipeDB = new HashMap<String, RecipeItem>();
    private static SavedRecipeDB mSavedRecipeDB;
    private static HashMap<String, String> calendarDB = new HashMap<>();
    private static RecipeDB sRecipeDB;
    private static Context sContext;

    //Emil: Jeg synes dette burde være databasen for saved
    private List<RecipeItem> SavedRecipeDB2 = new ArrayList<>();


    private SavedRecipeDB (Context context) {
    }

    public static SavedRecipeDB  get(Context context)  {
        if (mSavedRecipeDB == null) {
            sContext = context;
            mSavedRecipeDB = new SavedRecipeDB (context);
        }
        return mSavedRecipeDB;
    }

    public Map<String, RecipeItem> getSavedRecipeDB() {return savedRecipeDB; }


    //here we want to get the recipe that we have swiped right on in the Recipe_List_activity
    public void addItemToCalendar(String weekday, String recipeName) {
        calendarDB.put(weekday, recipeName); //add the recipe to the calendar list with the weekday
        this.setChanged();
        notifyObservers();
    }

    public static HashMap<String, String> getCalendarDB(){
        return calendarDB;
    }

    public String listItems() {
        String r = "";
        for (Map.Entry<String, RecipeItem> recipe : savedRecipeDB.entrySet()) {
            r += recipe.getKey() + recipe.getValue();
        }
        return r;
    }

    public int size() { return savedRecipeDB.size(); }

    //Emil: denne metode skal så connectes til saved knappen.
    public void addRecipeToSaved(RecipeItem recipeItem) {
        if (!SavedRecipeDB2.contains(recipeItem)) SavedRecipeDB2.add(recipeItem);
    }

    public void removeItem(String recipe){
        if (savedRecipeDB.get(recipe) != null)  savedRecipeDB.remove(recipe);
        this.setChanged();
        notifyObservers();
    }

    public static RecipeItem getSavedRecipeItem(String recipeName){
        return savedRecipeDB.get(recipeName);

    }


    public List<RecipeItem> getAllSavedRecipees() { //not sure the second paramter in new RecipeItem
        List<RecipeItem> result= new ArrayList<>();
        for (Map.Entry <String, RecipeItem> recipe: savedRecipeDB.entrySet()) {
            result.add(new RecipeItem());
        }
        return result;
    }

}
