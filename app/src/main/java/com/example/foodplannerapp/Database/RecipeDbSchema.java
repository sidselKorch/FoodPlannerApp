package com.example.foodplannerapp.Database;

public class RecipeDbSchema {
    public static final class ItemTable {
        public static final String NAME = "RecipeName";

        public static final class Cols {
            public static final String RecipeName = "recipeName"; //recipeName is a keyword in SQL
            public static final String RecipeGuide = "recipeGuide";
            //public static final String RecipePicture = "recipePicture";
        }
    }
}
