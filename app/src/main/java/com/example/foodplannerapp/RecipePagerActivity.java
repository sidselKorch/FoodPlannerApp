package com.example.foodplannerapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RecipePagerActivity extends AppCompatActivity {
    private static final String EXTRA_RECIPE_ID = "com.example.foodplannerapp.crime_id";
    private static RecipeDB recipesDB;
    public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;

    private ViewPager mViewPager;
    private List<RecipeItem> mRecipies;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, RecipePagerActivity.class);
        intent.putExtra(EXTRA_RECIPE_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recipe);
        recipesDB = RecipeDB.get(this);
        mRecipies = new ArrayList<>();

        UUID recipeId = (UUID) getIntent().getSerializableExtra(EXTRA_RECIPE_ID);

        mViewPager = (ViewPager) findViewById(R.id.recipe_view_pager);

        mRecipies = recipesDB.getAll();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

            @Override
            public Fragment getItem(int position) {
               RecipeItem recipeItem = mRecipies.get(position);
               return RecipeFragment.newInstance(recipeItem.getId());
            }

            @Override
            public int getCount() {
                return mRecipies.size();
            }
        });
        mViewPager.setCurrentItem(1);
        /*
        for (int i =0; i < mRecipies.size(); i++) {
            RecipeItem res = mRecipies.get(i);
            UUID n = res.getId();
            UUID q = recipeId;
            boolean bool = n.equals(q);
            if (bool) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

         */
        /*
        for (int i =0; i < mRecipies.size(); i++) {
            if (mRecipies.get(i).getId().equals(recipeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

         */
    }
}