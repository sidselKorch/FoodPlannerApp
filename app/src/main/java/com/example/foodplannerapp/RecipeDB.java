package com.example.foodplannerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;

import com.example.foodplannerapp.Database.RecipeDbSchema;
import com.example.foodplannerapp.Database.RecipeItemBaseHelper;
import com.example.foodplannerapp.Database.RecipeItemCurserWrapper;

public class RecipeDB extends Observable{

        private static final String TAG = "myLOG";
        private static RecipeDB sRecipeDB;
        private static Context sContext;
        private static SQLiteDatabase mDatabase;


        private RecipeDB(Context context) {
            if (getAll().size() == 0) fillItemsDB("foodPlanner.json");
        }

        public static RecipeDB get(Context context)  {
            if (sRecipeDB == null) {
                mDatabase = new RecipeItemBaseHelper(context.getApplicationContext()).getWritableDatabase();
                sContext = context;
                sRecipeDB = new RecipeDB(context);
            }
            return sRecipeDB;
        }

        //public Map<String, String> getItemsDB() {return ItemsDB; }

        public void addRecipe(String recipeName, String recipeGuide, String picture) {
            RecipeItem newRecipe = new RecipeItem();
            ContentValues values = getContentValues(newRecipe);
            mDatabase.insert(RecipeDbSchema.ItemTable.NAME, null, values);
            this.setChanged();
            notifyObservers();
        }

        private static ContentValues getContentValues(RecipeItem recipe) {
            ContentValues values = new ContentValues();
            values.put(RecipeDbSchema.ItemTable.Cols.RecipeName, recipe.getRecipeName());
            values.put(RecipeDbSchema.ItemTable.Cols.RecipeGuide, recipe.getRecipeGuide());
            return values;
        }

        // SELECT item
        public String getRecipeValue(String itemName) {
            //slå op i database i stedet

            String can = "";
            RecipeItemCurserWrapper cursor = queryItems(null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                if (itemName.equals(cursor.getRecipeItem().getRecipeName())) {
                    can = cursor.getRecipeItem().getRecipeGuide();
                }
                cursor.moveToNext();
            }
            cursor.close();

            if (itemName == null) {
                return itemName + " not found";
            } else if(can == "") {
                return itemName + " not in database";
            } else {
                return itemName + " should be placed in " + can;
            }
        }

        //INSERT items
        private void fillItemsDB(String filename) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(sContext.getAssets().open(filename)));
                String jsonString = reader.readLine();
                JSONArray recipeA = new JSONArray(jsonString);
                for (int i = 0; i < recipeA.length(); i++) {
                    addRecipe(recipeA.getJSONObject(i).getString("gname"),
                            recipeA.getJSONObject(i).getString("gguide"),
                            recipeA.getJSONObject(i).getString("gpicture"));

                }
            } catch (JSONException je) {
                Log.e(TAG, "Failed to parse JSON", je);
            } catch (IOException e) {
                Log.e(TAG, "Failed to read file", e);
            }
            this.setChanged();
            notifyObservers();

        }

        public ArrayList<RecipeItem> getAll() {
            ArrayList<RecipeItem> recipes = new ArrayList<>();
            RecipeItemCurserWrapper cursor = queryItems(null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                recipes.add(cursor.getRecipeItem());
                cursor.moveToNext();
            }
            cursor.close();
            return recipes;
        }

        static private RecipeItemCurserWrapper queryItems(String whereClause, String[] whereArgs) {
            Cursor cursor = mDatabase.query(
                    RecipeDbSchema.ItemTable.NAME,
                    null, //Colums - null selects all columns
                    whereClause, whereArgs,
                    null, //groupBy
                    null, //having
                    null //orderBy
            );
            return new RecipeItemCurserWrapper(cursor);
        }

        public void close() {
            mDatabase.close();
        }

    public static int size() { return RecipeDB.size(); }

    public RecipeItem getRecipeItem(int recipe) {
            return getAll().get(recipe);
    }

    public String toString() {
        String recipeName = "";
        String recipeGuide = "";
        String pictureFilename = "";
            for(RecipeItem recipeItem : getAll()){
                recipeName = recipeItem.getRecipeName();
                recipeGuide = recipeItem.getRecipeGuide();
                pictureFilename = recipeItem.getPictureFilename(recipeItem.getRecipeName());
            }
        return recipeName +"\n"+"\n"+
                recipeGuide + "\n" +
                pictureFilename + "\n";
    }

}
