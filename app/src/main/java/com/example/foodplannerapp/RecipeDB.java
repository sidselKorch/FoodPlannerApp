package com.example.foodplannerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.foodplannerapp.Database.RecipeDbSchema;
import com.example.foodplannerapp.Database.RecipeItemBaseHelper;
import com.example.foodplannerapp.Database.RecipeItemCurserWrapper;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;

public class RecipeDB extends Observable{

        private static final String TAG = "myLOG";
        private static RecipeDB sRecipeDB;
        private static Context sContext;
        private static SQLiteDatabase mDatabase;
        private ArrayList<RecipeItem> list = new ArrayList<>();

        private RecipeDB(Context context) {
            if (getAll().size() == 0) {
                fillItemsDB("foodPlanner.json");
            }
        }

        public static RecipeDB get(Context context)  {
            if (sRecipeDB == null) {
                mDatabase = new RecipeItemBaseHelper(context.getApplicationContext()).getWritableDatabase();
                sContext = context;
                sRecipeDB = new RecipeDB(context);
            }
            return sRecipeDB;
        }


        public void addRecipe(RecipeItem recipeItem) {
            ContentValues values = getContentValues(recipeItem);
            mDatabase.insert(RecipeDbSchema.ItemTable.NAME, null, values);
            this.setChanged();
            notifyObservers();
        }

        private ContentValues getContentValues(RecipeItem recipe) {
            ContentValues values = new ContentValues();
            values.put(RecipeDbSchema.ItemTable.Cols.RecipeName, recipe.getRecipeName());
            values.put(RecipeDbSchema.ItemTable.Cols.RecipeGuide, recipe.getRecipeGuide());
            return values;
        }

        public void fillItemsDB(String filename) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(sContext.getAssets().open(filename)));
                String jsonString = reader.readLine();
                JSONArray recipeA = new JSONArray(jsonString);
                for (int i = 0; i < recipeA.length(); i++) {
                    RecipeItem ri = new RecipeItem();

                    String rName = recipeA.getJSONObject(i).getString("gname");
                    String rGuide = recipeA.getJSONObject(i).getString("gguide");

                    ri.setRecipeName(rName);
                    ri.setRecipeGuide(rGuide);

                    list.add(ri);

                    addRecipe(ri);
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


}
