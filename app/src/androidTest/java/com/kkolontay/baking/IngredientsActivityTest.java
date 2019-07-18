package com.kkolontay.baking;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.kkolontay.baking.view.ingredientslist.IngredientsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class IngredientsActivityTest {
    @Rule
    public ActivityTestRule<IngredientsActivity> activityActivityTestRule = new ActivityTestRule<>(IngredientsActivity.class);

    @Test
    public void displayingFragment() {
        onView(withId(R.id.ingredients_list_view)).check(matches(isDisplayed()));
    }
}
