package com.example.cardiac_data_recoder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.SystemClock;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

public class UiEditDeleteTest {
    @Rule
    public ActivityScenarioRule<DataViewerActivity> rule = new ActivityScenarioRule<>(DataViewerActivity.class);

    @Test
    public void editTest(){
        SystemClock.sleep(5000);

        Espresso.onView(withId(R.id.medical_report_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,
                        MyViewAction.clickChildViewWithId(R.id.edit_rec_button)));

        SystemClock.sleep(1000);


        onView(withId(R.id.record_date_update)).perform(click());

        onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2023, 10, 10));

        onView(withText("OK")).perform(click());

        onView(withId(R.id.record_time_update)).perform(click());
        onView(ViewMatchers.withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(10, 15));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.systolic_pressure_update)).perform(ViewActions.replaceText("100"));
        onView(withId(R.id.diastolic_pressure_update)).perform(ViewActions.replaceText("110"));
        onView(withId(R.id.heart_rate_update)).perform(ViewActions.replaceText("104"));
        onView(withId(R.id.comment_update)).perform(ViewActions.replaceText("I'm not Perfect"));//writing comment I'am perfect

        onView(withId(R.id.update_the_record)).perform(click());

        SystemClock.sleep(3000);
    }


    @Test
    public void deleteTest(){
        SystemClock.sleep(5000);

        Espresso.onView(withId(R.id.medical_report_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,
                        MyViewAction.clickChildViewWithId(R.id.delete_rec_button)));
        SystemClock.sleep(4000);
    }


    static class MyViewAction{
        public static ViewAction clickChildViewWithId(final int id) {
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return ViewMatchers.isAssignableFrom(RecyclerView.class);
                }

                @Override
                public String getDescription() {
                    return "Click on a child view with specified id.";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    View v = view.findViewById(id);
                    v.performClick();
                }
            };
        }
    }

}
