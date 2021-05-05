package com.example.foodplannerapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CalenderFragment extends Fragment {
    private Button removeRecipe, clearCalender;
    private static CalenderDB calenderDB;
    private TextView listCalender, mWeekday;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calenderDB = calenderDB.get(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_calendar, container, false);



            listCalender = v.findViewById(R.id.list_calendar);
            listCalender.setText(calenderDB.listItems() + " " + "\n");


            mWeekday = v.findViewById(R.id.remove_recipe_field);

            removeRecipe = v.findViewById(R.id.remove_recipe);
            removeRecipe.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    if (!mWeekday.getText().toString().trim().isEmpty()) {
                        String weekday = mWeekday.getText().toString().trim();
                        String str = weekday.substring(0, 1).toUpperCase() + weekday.substring(1).toLowerCase();
                        calenderDB.removeRecipe(str);
                        Toast.makeText(getActivity(), "Removed " + str, Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(getActivity(), "Removed", Toast.LENGTH_LONG).show();
                    listCalender.setText(calenderDB.listItems() + " " + "\n");
                    mWeekday.setText("");
                }
            });

            clearCalender = v.findViewById(R.id.clear_button);
            clearCalender.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    calenderDB.getCalendarDB().clear();
                    listCalender.setText(calenderDB.listItems() + " " + "\n");

                }
            });

        return v;
    }
}

