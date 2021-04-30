package com.example.foodplannerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class CalendarFragment extends Fragment implements Observer {
    private static CalendarDB calendarDB;
    private RecyclerView calendarList;
    private CalendarFragment.ItemAdapter mAdapter;

    @Override
    public void update(Observable observable, Object data) {
        calendarList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendarDB = calendarDB.get(getActivity());
        calendarDB.addObserver(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarList = v.findViewById(R.id.list_calendar);
        calendarList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CalendarFragment.ItemAdapter();
        calendarList.setAdapter(mAdapter);
        return v;
    }

    private class ItemAdapter extends RecyclerView.Adapter<CalendarFragment.ItemHolder> {

        @NonNull
        @Override
        public CalendarFragment.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.one_row, parent, false);
            return new CalendarFragment.ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull CalendarFragment.ItemHolder holder, int position) {
            RecipeItem recipe = calendarDB.getAll().get(position);
            holder.bind(recipe, position);

        }

        @Override
        public int getItemCount() {
            return calendarDB.size();
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mRecipeNameView, mRecipeGuideView, mPictureView, mNoView;



        public ItemHolder(@NonNull View recipeView) {
            super(recipeView);
            mNoView = recipeView.findViewById(R.id.recipe_no);
            mRecipeNameView = recipeView.findViewById(R.id.recipe_name);
            mRecipeGuideView = recipeView.findViewById(R.id.recipe_guide);
            mPictureView = recipeView.findViewById(R.id.recipe_picture);
            recipeView.setOnClickListener(this);
        }

        public void bind(RecipeItem recipe, int position) {
            mNoView.setText(" " + position + " ");
            mRecipeNameView.setText(recipe.getRecipeName());
            mRecipeGuideView.setText(recipe.getRecipeGuide());
            mPictureView.setText(recipe.getPictureFilename(recipe.getRecipeName()));
        }

        @Override
        public void onClick(View v) {
            String recipeName = (String) ((TextView)v.findViewById(R.id.recipe_name)).getText();
            calendarDB.removeItem(recipeName);


        }
    }
}

