package com.example.foodplannerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

//this is a class that lists all the weekdays with the recipe, it should have a remove button

public class CalendarActivityList extends AppCompatActivity {
    private static CalendarDB mCalendarDB;
    private FragmentManager fm;
    Fragment fragmentCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mCalendarDB = CalendarDB.get(CalendarActivityList.this); //gets the map where the recipees are added?
        fm = getSupportFragmentManager();

        fragmentCalendar = fm.findFragmentById(R.id.calendar_container_list);
        if (fragmentCalendar == null) {
            fragmentCalendar = new CalendarFragment();
            fm.beginTransaction()
                    .add(R.id.calendar_container_list, fragmentCalendar)
                    .commit();
        }
    }

}
