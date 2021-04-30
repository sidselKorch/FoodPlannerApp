package com.example.foodplannerapp;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RecipeItem {
    private static UUID mId;
    private static String recipeName = "";
    private static String recipeGuide = "";
    private List<RecipeIngredients> recipeIngredients;
    private static String pictureFilename = "";
    private RecipeIngredients mRecipeIngredients;
    Context mContext;


    public RecipeItem() {
        recipeIngredients = new ArrayList<>();
        mRecipeIngredients = new RecipeIngredients();
        mId = UUID.randomUUID();
    }

    public void addIngredient(RecipeIngredients ingredients) {
        if (!recipeIngredients.contains(ingredients)) recipeIngredients.add(ingredients);
    }

    public List<RecipeIngredients> getRecipeIngredients(){
        return recipeIngredients;
    }

    public void removeIngredient(RecipeIngredients ingredients) {
        if (recipeIngredients.contains(ingredients)) recipeIngredients.remove(ingredients);
    }

    public String printIngredient() {
        String str = "";
        for (RecipeIngredients recipe : recipeIngredients) {
            str += recipe.toString();
        }
        return str;
    }

    public static UUID getId() {
        return mId;
    }

    public static String getPictureFilename() {
        return pictureFilename;
    }

    public String getPhotoFilename() {//tror vi skal bruge recipeName til at gemme billedet under?
        return "IMG_" + mRecipeIngredients.getRecipeName().toString() + ".jpg";
    }

    /**
     *
     * @return a list of information to the given recipe
     */
    @Override
    public String toString() {
        return getRecipeName() +"\n"+"\n"+
                getRecipeGuide() +
                getPictureFilename() + "\n";
    }

    public static String getRecipeName(){
        return recipeName;
    }

    public static String getRecipeGuide(){
        return recipeGuide;
    }



    public File getPhotoFile(RecipeIngredients recipe) {
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, getPhotoFilename());
    }
}

