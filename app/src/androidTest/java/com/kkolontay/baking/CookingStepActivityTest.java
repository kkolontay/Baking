package com.kkolontay.baking;

import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.kkolontay.baking.model.Step;
import com.kkolontay.baking.view.cookingstep.CookingStepActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class CookingStepActivityTest {
    private int countItems;
    private int selectedIndex;
    @Rule
   public ActivityTestRule<CookingStepActivity> mActivityTestResult = new ActivityTestRule<>(CookingStepActivity.class);

    @Before
    public void setStartedData() {
        mActivityTestResult.getActivity().setSteps(CreateSteps.getStepsData());
        countItems = mActivityTestResult.getActivity().getStepsCount();
        selectedIndex = mActivityTestResult.getActivity().getSelectedIndex();
    }

    @Test
    public void increaseNextFragment() {
       // onView(withId(R.id.previous_step_cooking_button)).check();
        //countItems = mActivityTestResult.getActivity().getStepsCount();
       // selectedIndex = mActivityTestResult.getActivity().getSelectedIndex();
        onView(withId(R.id.next_step_cooking_button)).perform(click());
       // mActivityTestResult.getActivity().getSelectedIndex().check();
    }

    @Test
    public void decreasePreviousFragment() {
        //countItems = mActivityTestResult.getActivity().getStepsCount();
       // selectedIndex = mActivityTestResult.getActivity().getSelectedIndex();
        onView(withId(R.id.previous_step_cooking_button)).perform(click());
    }

    private static class CreateSteps {
        private static ArrayList<Step> getStepsData() {
            ArrayList<Step> steps = new ArrayList<>();
            for(int i = 0; i < 3; i++) {
                steps.add( new Step(0, "Recipe Introduction", "Recipe Introduction",
                        "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4",
                        ""));
            }
            return steps;
        }
    }
}

