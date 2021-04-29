package com.example.foodplannerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;
//there should be a button that adds the recipe to calendar list with a given weekday and a button that says calendar
//where it starts the new activity of the calendar, which lists the week with the recipe
public class SavedFragment extends Fragment implements Observer {
    private static SavedRecipeDB savedRecipeDB;
    private RecyclerView recipeList;
    private ItemAdapter mAdapter;

    @Override
    public void update(Observable observable, Object data) {
        recipeList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedRecipeDB = savedRecipeDB.get(getActivity());
        savedRecipeDB.addObserver((Observer) this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_saved, container, false);
        recipeList = v.findViewById(R.id.list_saved_recipe);
        recipeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ItemAdapter();
        recipeList.setAdapter(mAdapter);
        return v;
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.one_row, parent, false);
            return new ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            RecipeItem recipe = savedRecipeDB.getAllSavedRecipees().get(position);
            holder.bind(recipe, position);

        }

        @Override
        public int getItemCount() {
            return savedRecipeDB.size();
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
            mPictureView.setText(recipe.getRecipePicture(recipe.getRecipeName()));
        }

        @Override
        public void onClick(View v) {
            String recipeName = (String) ((TextView)v.findViewById(R.id.recipe_name)).getText();
            savedRecipeDB.removeItem(recipeName);


        }
    }
}
