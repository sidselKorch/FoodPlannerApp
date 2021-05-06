package com.example.foodplannerapp;


import android.content.Context;
        import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
        import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.RecyclerView;
        import androidx.viewpager2.widget.ViewPager2;

        import java.util.List;

        public class SavedFragment extends RecyclerView.Adapter<SavedFragment.ViewHolder>{
        private List<RecipeItem> savedRecipeDB;
        private LayoutInflater mInflater;
        private ViewPager2 viewPager2;

        //private int[]colorArray=new int[]{android.R.color.black,android.R.color.holo_blue_dark,android.R.color.holo_green_dark,android.R.color.holo_red_dark};

        public SavedFragment(Context context,List<RecipeItem>data,ViewPager2 viewPager2){
            this.mInflater=LayoutInflater.from(context);
            this.savedRecipeDB=data;
            this.viewPager2=viewPager2;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=mInflater.inflate(R.layout.fragment_saved,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            RecipeItem recipeItem=savedRecipeDB.get(position);
            holder.myTextView.setText(recipeItem.toString());
            //holder.relativeLayout.setBackgroundResource(colorArray[position]);

        }

        @Override
        public int getItemCount() {
            return savedRecipeDB.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView myTextView, mWeekday, mRecipeName;
            RelativeLayout relativeLayout;
            Button mViewCalenderButton, mAddTooCalenderButton;
            ImageView mPhoto;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.saved_viewPager_text);
                relativeLayout = itemView.findViewById(R.id.viewPager_saved);

                mWeekday = itemView.findViewById(R.id.add_weekday_field);
                mRecipeName = itemView.findViewById(R.id.add_recipeName_field);
                mPhoto = itemView.findViewById(R.id.recipe_photo);

                mAddTooCalenderButton = itemView.findViewById(R.id.add_to_calender);
                mAddTooCalenderButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String weekday = mWeekday.getText().toString().trim();
                        String key = weekday.substring(0,1).toUpperCase() + weekday.substring(1).toLowerCase();
                        String recipeName = mRecipeName.getText().toString().trim();
                        String value = recipeName.substring(0,1).toUpperCase() + recipeName.substring(1).toLowerCase();
                        CalenderDB.getCalendarDB().put(key, value);
                        mWeekday.setText(" ");
                        mRecipeName.setText(" ");
                        Toast.makeText(itemView.getContext(), "Added to weekday: "+ key + "\n" + "recipe: " + value + " to the calender", Toast.LENGTH_SHORT).show();
                    }
                });

                mViewCalenderButton = itemView.findViewById(R.id.view_calender_button);
                mViewCalenderButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       itemView.getContext().startActivity(new Intent(itemView.getContext(), CalenderActivityList.class));
                    }
                });
            }
        }
}
