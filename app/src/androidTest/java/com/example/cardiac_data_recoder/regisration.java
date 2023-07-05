package com.example.cardiac_data_recoder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class regisration {

    @Rule
    public ActivityScenarioRule<com.example.cardiac_data_recoder.auth.RegisterActivity> activityRule = new ActivityScenarioRule<>(com.example.cardiac_data_recoder.auth.RegisterActivity.class);

    @Rule
    public ActivityScenarioRule<com.example.cardiac_data_recoder.auth.loginactivity1> loginactivityrule = new ActivityScenarioRule<>(com.example.cardiac_data_recoder.auth.loginactivity1.class);
    @Test
    public void Uitest()
    {

        onView(withId(R.id.emailField)).perform(ViewActions.typeText("turjo.chinmoymodak02@gmail.com"));
        onView(withId(R.id.passwordField)).perform(ViewActions.typeText("1234Turjo@"));
        onView(withId(R.id.confirmPasswordField)).perform(ViewActions.typeText("1234Turjo@"));

        Espresso.pressBack();
        onView(withId(R.id.register)).perform(click());

        try {
            Thread.sleep(3500); // Adjust the delay as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.loginscreen)).check(matches(isDisplayed()));




        onView(withId(R.id.signupemail1)).perform(ViewActions.typeText("turjo.chinmoymodak02@gmail.com"));
        onView(withId(R.id.signuppassword2)).perform(ViewActions.typeText("1234Turjo@"));
        Espresso.pressBack();
        onView(withId(R.id.login1)).perform(click());


        try {
            Thread.sleep(3500); // Adjust the delay as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.cardiac_report_view)).check(matches(isDisplayed()));

    }
}


