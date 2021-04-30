package com.example.foodplannerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Map;

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
                    calenderDB.removeRecipe(mWeekday.toString());
                    Toast.makeText(getActivity(), "Removed "+ calenderDB.getValue(mWeekday.toString()), Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getActivity(), "Removed", Toast.LENGTH_LONG).show();
            }
        });

        clearCalender = v.findViewById(R.id.clear_button);
        clearCalender.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
               for (Map.Entry<String, String> recipe : calenderDB.getCalendarDB().entrySet()){
                   calenderDB.removeRecipe(mWeekday.toString());

               }

            }
        });

        return v;
    }
}

