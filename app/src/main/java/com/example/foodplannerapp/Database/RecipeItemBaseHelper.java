package com.example.foodplannerapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.foodplannerapp.Database.RecipeDbSchema.ItemTable;

public class RecipeItemBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "foodPlanner.db";

    public RecipeItemBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ItemTable.NAME + "(" + ItemTable.Cols.RecipeName + ", " + ItemTable.Cols.RecipeGuide + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
