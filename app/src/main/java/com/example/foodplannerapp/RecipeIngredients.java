package com.example.foodplannerapp;

public class RecipeIngredients {

    private String name;
    private String unit;


    public RecipeIngredients() {
        name = "";
        unit = "";

    }

    //getters
    public String getRecipeName() {
        return name;
    }

    public String getRecipeUnit() {
        return unit;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return getRecipeName() + ": " + getRecipeUnit() + "\n";
    }
}

