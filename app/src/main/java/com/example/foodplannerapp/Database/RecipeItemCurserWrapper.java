package com.example.foodplannerapp.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

//import com.example.foodplannerapp.RecipeIngredients;
import com.example.foodplannerapp.RecipeItem;
import com.example.foodplannerapp.Database.RecipeDbSchema.ItemTable;

public class RecipeItemCurserWrapper extends CursorWrapper {

    public RecipeItemCurserWrapper(Cursor cursor) {
        super(cursor);
    }

    public RecipeItem getRecipeItem() {
        RecipeItem recipeItem = new RecipeItem();
        String recipeName = getString(getColumnIndex(ItemTable.Cols.RecipeName));
        String recipeGuide = getString(getColumnIndex(ItemTable.Cols.RecipeGuide));
        //String recipePicture = getString(getColumnIndex(ItemTable.Cols.RecipePicture));
        recipeItem.setRecipeName(recipeName);
        recipeItem.setRecipeGuide(recipeGuide);
        return recipeItem;

    }
}
