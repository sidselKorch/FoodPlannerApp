package com.example.foodplannerapp;

import android.content.Context;
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


    //private int[] colorArray = new int[]{android.R.color.black, android.R.color.holo_blue_dark, android.R.color.holo_green_dark, android.R.color.holo_red_dark};

    RecipeFragmentStateAdapter(Context context, List<RecipeItem> data, ViewPager2 viewPager2) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.viewPager2 = viewPager2;
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
        //holder.relativeLayout.setBackgroundResource(colorArray[position]);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        RelativeLayout relativeLayout;
        Button saveButton;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.viewPager_text);
            relativeLayout = itemView.findViewById(R.id.container);
            saveButton = itemView.findViewById(R.id.save_button);

            /*
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(viewPager2.getOrientation() == ViewPager2.ORIENTATION_VERTICAL)
                        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                    else{
                        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                    }
                }
            });

             */
        }
    }

}
