package com.example.foodplannerapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

public class CalenderDB extends Observable {
    private static CalenderDB sCalenderDB;
    private static Map<String, String> calenderDB = new TreeMap<>();

    private CalenderDB(Context context){
        //fillCalenderDB();

    }

    public static CalenderDB get(Context context) {
        if (sCalenderDB == null)
            sCalenderDB = new CalenderDB(context);

        return sCalenderDB;
    }

    //here we want to get the recipe that we have swiped right on in the Recipe_List_activity
    public void addItemToCalendar(String weekday, String recipeName) {
        calenderDB.put(weekday, recipeName); //add the recipe to the calendar list with the weekday
        this.setChanged();
        notifyObservers();
    }

    public static Map<String, String> getCalendarDB(){
        return calenderDB;
    }


    public String listItems() {
        String r = "";
        if(calenderDB != null) {
            for (Map.Entry<String, String> recipeday : calenderDB.entrySet()) {
                r+= "Weekday: " + recipeday.getKey() + " Recipe: " + recipeday.getValue() + "\n";

            }
        }else r = "There is no elements in this map";
        return " " + r;
    }

    public List<RecipeItem> getAll() {
        List<RecipeItem> result= new ArrayList<>();
        for (Map.Entry <String, String> recipeDay: calenderDB.entrySet()) {
            result.add(new RecipeItem());
        }
        return result;
    }

    public int size() { return sCalenderDB.size(); }

    public void removeRecipe(String weekday){
        if (calenderDB != null)
            calenderDB.remove(weekday);
        this.setChanged();
        notifyObservers();
    }

    public String getValue(String weekday){
        return calenderDB.get(weekday);
    }


    public void fillCalenderDB() {
        calenderDB.put("Monday", "recipeName");
        calenderDB.put("Tuesday", "recipeName");
        calenderDB.put("Wednesday", "recipeName");
        calenderDB.put("Thursday", "recipeName");
        calenderDB.put("Friday", "recipeName");
        calenderDB.put("Saturday","recipeName");
        calenderDB.put("Sunday", "recipeName");


    }

}
