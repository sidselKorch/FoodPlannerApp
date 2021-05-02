package com.example.foodplannerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.UUID;

public class RecipeFragment extends Fragment {
    private RecipeItem recipeItem;
    private Button mSaveButton;
    private RecipeDB recipeDB;

    //Emil: Jeg har prøvet at tilføje den her.
    private TextView textView, saveRecipeName;
    private static final String ARG_RECIPE_ID = "recipe_id";

    public static RecipeFragment newInstance(UUID recipeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_RECIPE_ID, recipeId);

        RecipeFragment fragment = new RecipeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeDB = RecipeDB.get(getActivity());
        recipeItem = new RecipeItem();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recipe, container, false);

        //Emil: Jeg har prøvet at tilføje den her.
        textView = v.findViewById(R.id.text_in_viewpager);
        textView.setText(recipeItem.toString());

        saveRecipeName = v.findViewById(R.id.save_recipe_field);

        mSaveButton = (Button) v.findViewById(R.id.save_button);
        //mSaveButton.setText("Save recipe");
        mSaveButton.setEnabled(false);

        return v;
    }

    public  String getSavedRecipe(){
        return saveRecipeName.toString().trim();
    }

}

