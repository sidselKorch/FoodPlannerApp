package com.example.foodplannerapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class CalendarDB extends Observable {
    private static CalendarDB sCalendarDB;
    private static HashMap<String, String> calendarDB;
    private static Context sContext;

    private CalendarDB (Context context){
        calendarDB = SavedRecipeDB.getCalendarDB();

    }

    public static CalendarDB get(Context context) {
        if (sCalendarDB == null){
            sContext = context;
            sCalendarDB = new CalendarDB(context);
        }
        return sCalendarDB;
    }


    public String listItems() {
        String r = "";
        for (Map.Entry<String, String> recipe : calendarDB.entrySet()) {
            r += recipe.getKey() + recipe.getValue();
        }
        return r;
    }

    public List<RecipeItem> getAll() {
        List<RecipeItem> result= new ArrayList<>();
        for (Map.Entry <String, String> recipeDay: calendarDB.entrySet()) {
            result.add(new RecipeItem());
        }
        return result;
    }

    public int size() { return sCalendarDB.size(); }

    public void removeItem(String weekday){
        if (calendarDB.get(weekday) != null)  calendarDB.remove(weekday);
        this.setChanged();
        notifyObservers();
    }

}
