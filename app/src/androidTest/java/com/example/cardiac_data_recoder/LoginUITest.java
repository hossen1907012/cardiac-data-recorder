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

import com.example.cardiac_data_recoder.auth.loginactivity1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)


/**
 * This is a test method for checking user-interface login feature
 * This will check after successful validation it will redirect to
 * activity page
 */
public class LoginUITest {

    @Rule
    public ActivityScenarioRule<com.example.cardiac_data_recoder.auth.loginactivity1> loginactivityrule = new ActivityScenarioRule<>(com.example.cardiac_data_recoder.auth.loginactivity1.class);
    @Test
    public void testlogin()
    {
        try {
            Thread.sleep(4000); // Adjust the delay as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        onView(withId(R.id.loginscreen)).check(matches(isDisplayed()));//display matching with id checked

        onView(withId(R.id.signupemail1)).perform(ViewActions.typeText("turjo.chinmoymodak01@gmail.com"));
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