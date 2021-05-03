package com.example.foodplannerapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class SavedFragment extends Fragment {
    private RecipeItem recipeItem;
    private Button mAddTooCalenderButton, mViewCalenderButton;
    private SavedRecipeDB savedRecipeDB;

    //Emil: Jeg har prøvet at tilføje den her.
    private TextView textView;
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
        savedRecipeDB = SavedRecipeDB.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_saved, container, false);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            //Emil: Jeg har prøvet at tilføje den her.
            //textView = v.findViewById(R.id.text_in_viewpager);
            textView.setText(savedRecipeDB.toString());

            mAddTooCalenderButton = (Button) v.findViewById(R.id.add_to_calender);
            //mSaveButton.setText("Save recipe");
            mAddTooCalenderButton.setEnabled(false);

            mViewCalenderButton = v.findViewById(R.id.view_calender_button);
            mViewCalenderButton.setEnabled(true);
            mViewCalenderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CalenderActivityList.class);
                    startActivity(intent);

                }
            });
        }


        return v;
    }
}
