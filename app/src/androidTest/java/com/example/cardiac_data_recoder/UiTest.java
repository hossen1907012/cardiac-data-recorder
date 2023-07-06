package com.example.cardiac_data_recoder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(AndroidJUnit4.class)

public class UiTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);



    @Test
    /**
     * This is a splash scrren UI Test
     * This method is used to check splash screen
     */
    public void splash() {


        onView(withId(R.id.splashscreen)).check(matches(isDisplayed()));
        onView(withId(R.id.splashScreenImg)).check(matches(isDisplayed()));
        onView(withId(R.id.splashScreenImg)).check(matches(isDisplayed()));
        onView(withId(R.id.splashScreenImg)).check(matches(isDisplayed()));

        try {
            Thread.sleep(3500); // Adjust the delay as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



//    @Test
//    public void testadd()
//    {
//        try {
//            Thread.sleep(1500); // Adjust the delay as needed
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        onView(withId(R.id.redirect_to_add_data)).perform(click());
//        onView(withId(R.id.insert_medical_report_layout)).check(matches(isDisplayed()));
//        onView(withId(R.id.systolic_pressure)).perform(ViewActions.typeText("120"));
//        onView(withId(R.id.diastolic_pressure)).perform(ViewActions.typeText("80"));
//        onView(withId(R.id.heart_rate)).perform(ViewActions.typeText("70"));
//        onView(withId(R.id.comment)).perform(ViewActions.typeText("I'm Perfect!"));
//
//        // Open the DatePicker
//        //onView(withId(R.id.datePickerButton)).perform(click())
//        onView(withId(R.id.add_new_record)).perform(click());
//
//    }



}