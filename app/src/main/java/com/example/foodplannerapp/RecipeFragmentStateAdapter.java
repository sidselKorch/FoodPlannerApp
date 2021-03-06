package com.example.foodplannerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class RecipeFragmentStateAdapter extends RecyclerView.Adapter<RecipeFragmentStateAdapter.ViewHolder> {

    private List<RecipeItem> mData;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager2;
    private SavedRecipeDB savedRecipeDB;
    private RecipeItem currentRecipeItem;



    RecipeFragmentStateAdapter(Context context, List<RecipeItem> data, ViewPager2 viewPager2) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.viewPager2 = viewPager2;
        savedRecipeDB = SavedRecipeDB.get(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_for_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecipeItem recipeItem = mData.get(position);
        holder.myTextView.setText(recipeItem.toString());
        currentRecipeItem = recipeItem;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        RelativeLayout relativeLayout;
        Button saveButton, savedListButton;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.viewPager_text);
            relativeLayout = itemView.findViewById(R.id.container);
            saveButton = itemView.findViewById(R.id.save_button);
            savedListButton = itemView.findViewById(R.id.saved_recipe_list_button);

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    savedRecipeDB.addRecipeToSaved(currentRecipeItem);
                }
            });

            savedListButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), SavedRecipeActivity.class));
                }
            });
        }
    }
}
