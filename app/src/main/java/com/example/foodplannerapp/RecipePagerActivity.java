package com.example.foodplannerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class RecipePagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<RecipeItem> mRecipies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recipe);

        mViewPager = (ViewPager) findViewById(R.id.recipe_view_pager);

        mRecipies = RecipeDB.get(this).getAll();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

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
    }
}