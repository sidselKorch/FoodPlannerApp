package com.example.foodplannerapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class MyActivity extends AppCompatActivity {

    ViewPager2 myViewPager2;
    MyAdapter MyAdapter;
    RecipeDB recipeDB;
    private ArrayList<RecipeItem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_pager_activity);
        arrayList = recipeDB.getAll();
        myViewPager2 = findViewById(R.id.view_pager);

        MyAdapter = new MyAdapter(this, arrayList);

        myViewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        myViewPager2.setAdapter(MyAdapter);
    }
}