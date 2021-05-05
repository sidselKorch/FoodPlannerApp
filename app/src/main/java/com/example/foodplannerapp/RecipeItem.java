package com.example.foodplannerapp;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RecipeItem {
    private static UUID mId;
    private String recipeName;
    private String recipeGuide;
    private String pictureFilename;
    Context mContext;


    public RecipeItem() {
        recipeName = "";
        recipeGuide = "";
        mId = UUID.randomUUID();
    }

    public static UUID getId() {
        return mId;
    }


    /*
    public static String getPictureFilename() {
        return pictureFilename;
    }

     */

    /**
     *
     * @return a list of information to the given recipe
     */
    @Override
    public String toString() {
        return recipeName +"\n"+"\n"+
                recipeGuide;
                //pictureFilename + "\n";
    }

    public String getRecipeName(){
        return recipeName;
    }

    public String getRecipeGuide(){
        return recipeGuide;
    }
    /*
    public File getPhotoFile(RecipeIngredients recipe) {
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, getPhotoFilename());
    }

     */

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setRecipeGuide(String recipeGuide) {
        this.recipeGuide = recipeGuide;
    }
}

