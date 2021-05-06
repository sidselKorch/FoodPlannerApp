package com.example.foodplannerapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.foodplannerapp.Database.RecipeDbSchema.ItemTable;

public class RecipeItemBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "foodPlanner.db";

    public RecipeItemBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ItemTable.NAME + "(" +
                ItemTable.Cols.RecipeName + ", " + ItemTable.Cols.RecipeGuide + ")");
        //db.execSQL("INSERT INTO ItemTable (recipeName, recipeGuide) VALUES ('Kødsovs', 'pasta og hakket oksekød')");
        /*
        Cursor cursor = db.query(Boolean.parseBoolean((DATABASE_NAME)), null, null,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.moveToFirst()) {
            Log.i("DB", cursor.getString(0)+" "+cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
         */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
