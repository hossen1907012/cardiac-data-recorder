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

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

public class RViewCheck {

    @Rule
    public ActivityScenarioRule<DataViewerActivity> activityRule = new ActivityScenarioRule<>(DataViewerActivity.class);

    @Test
    public void UiTest() {


        onView(withId(R.id.redirect_to_add_data)).perform(click());

        try {
            Thread.sleep(1500); // Adjust the delay as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1500); // Adjust the delay as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.insert_medical_report_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.systolic_pressure)).perform(ViewActions.typeText("100"));
        onView(withId(R.id.diastolic_pressure)).perform(ViewActions.typeText("100"));
        onView(withId(R.id.heart_rate)).perform(ViewActions.typeText("100"));
        onView(withId(R.id.comment)).perform(ViewActions.typeText("I'm Perfect!"));

        Espresso.pressBack();
        onView(withId(R.id.add_new_record)).perform(click());

        try {
            Thread.sleep(500); // Adjust the delay as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.cardiac_report_view)).check(matches(isDisplayed()));


    }
}