package com.example.cardiac_data_recoder;

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

    @Test
    public void Uitest()
    {

    }
}


