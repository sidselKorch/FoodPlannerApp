package com.example.foodplannerapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UIFragment extends Fragment {
    private Button listRecipes;
    private Button savedRecipes;
    private Button calenderRecipes;
    private static RecipeDB recipesDB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipesDB = recipesDB.get(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_ui, container, false);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Log.i("***", "PP");

            // LIST RECIPE BUTTON
            listRecipes = v.findViewById(R.id.recipe_list);
            listRecipes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), RecipePagerActivity.class);
                    startActivity(intent);
                }
            });

            // LIST SAVED RECIPE BUTTON
            savedRecipes = v.findViewById(R.id.saved_list);
            savedRecipes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), SavedRecipeActivity.class);
                    startActivity(intent);
                }
            });

            // LIST CALENDER RECIPE BUTTON
            calenderRecipes = v.findViewById(R.id.calender_list);
            calenderRecipes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CalendarActivityList.class);
                    startActivity(intent);
                }
            });



        }

        return v;
    }
}