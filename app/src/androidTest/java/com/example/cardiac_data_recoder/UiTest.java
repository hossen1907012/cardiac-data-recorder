package com.example.cardiac_data_recoder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)

public class UiTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);



    @Test
    public void splash() {


        onView(withId(R.id.splashscreen)).check(matches(isDisplayed()));
        onView(withId(R.id.splashScreenImg)).check(matches(isDisplayed()));
        onView(withId(R.id.splashScreenImg)).check(matches(isDisplayed()));
        onView(withId(R.id.splashScreenImg)).check(matches(isDisplayed()));

    }

    @Test
    public void testlogin()
    {
        try {
            Thread.sleep(3500); // Adjust the delay as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.loginscreen)).check(matches(isDisplayed()));

        onView(withId(R.id.signupemail1)).perform(ViewActions.typeText("turjo.chinmoymodak01@gmail.com"));
        onView(withId(R.id.signuppassword2)).perform(ViewActions.typeText("1234Turjo@"));
        onView(withId(R.id.login1)).perform(click());
        

        try {
            Thread.sleep(4000); // Adjust the delay as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.add_note_btn)).check(matches(isDisplayed()));




    }

}