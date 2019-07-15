package com.kkolontay.baking;


import android.view.Display;

import androidx.test.annotation.UiThreadTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.kkolontay.baking.view.bakingdetail.BakeDetailActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class BakingDetailActivityTest {

    @Rule
    public ActivityTestRule<BakeDetailActivity> mBakeTestRule = new ActivityTestRule<>(BakeDetailActivity.class);

    @Before
    public void setData() {
        mBakeTestRule.getActivity().setModel(ModelExample.getBakingForTest());
    }

    @Test
    @UiThreadTest
    public void testListBakingDetailItem() {
        onData(anything()).inAdapterView(withId(R.id.steps_fragment_container)).atPosition(1).perform(click());
        onView(withId(R.id.previous_step_cooking_button)).check(matches(withText("Previous Step")));
    }
}
