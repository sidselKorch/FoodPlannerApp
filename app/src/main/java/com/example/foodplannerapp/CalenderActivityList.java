package com.example.foodplannerapp;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

//this is a class that lists all the weekdays with the recipe, it should have a remove button

public class CalenderActivityList extends AppCompatActivity {
    private static CalenderDB mCalendarDB;
    private FragmentManager fm;
    Fragment fragmentCalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mCalendarDB = CalenderDB.get(this); //gets the map where the recipees are added?
        fm = getSupportFragmentManager();
        setUpFragments();
    }

    private void setUpFragments() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentCalender= fm.findFragmentById(R.id.calendar_container_landscape);
            if ((fragmentCalender == null)) {
                fragmentCalender= new CalenderFragment();
                fm.beginTransaction()
                        .add(R.id.calendar_container_landscape, fragmentCalender)
                        .commit();
            }
        } else {
            //Orientation portrait
            fragmentCalender = fm.findFragmentById(R.id.calendar_container_portrait);
            if (fragmentCalender == null) {
                fragmentCalender = new CalenderFragment();
                fm.beginTransaction()
                        .add(R.id.calendar_container_portrait, fragmentCalender)
                        .commit();
            }
        }
    }

}
