package com.kkolontay.baking.view.cookingstep;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.kkolontay.baking.R;
import com.kkolontay.baking.model.Step;
import com.kkolontay.baking.view.bakingdetail.BakeDetailActivity;
import com.kkolontay.baking.view.cookingstep.stepfragment.CookingStepFragment;

import java.util.ArrayList;

public class CookingStepActivity extends AppCompatActivity {
    private ArrayList<Step> stepsList;
    private int selectedIndex;
    private Button nextStepButton;
    private Button previousStepButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_step);
        if (savedInstanceState == null) {
            stepsList = getIntent().getParcelableArrayListExtra(BakeDetailActivity.STEPS);
            selectedIndex = getIntent().getIntExtra(BakeDetailActivity.SELECTEDPOSITION, 0);
        } else {
            stepsList = savedInstanceState.getParcelableArrayList(BakeDetailActivity.STEPS);
            selectedIndex = savedInstanceState.getInt(BakeDetailActivity.SELECTEDPOSITION);
        }
        configureButtons();
        replaceFragment();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    @VisibleForTesting
    public void setSteps(ArrayList<Step> steps) {
        stepsList = steps;
    }

    @VisibleForTesting
    public int getStepsCount() {
        return stepsList.size();
    }

    @VisibleForTesting
    public int getSelectedIndex() {
        return selectedIndex;
    }

    private void replaceFragment() {
        if (stepsList == null) {return;}
        CookingStepFragment fragment = new CookingStepFragment(stepsList.get(selectedIndex));
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.cooking_step_frame, fragment)
                .commit();
        setTitle(stepsList.get(selectedIndex).getShortDescription());
    }

    private  void configureButtons() {
        nextStepButton = findViewById(R.id.next_step_cooking_button);
        previousStepButton = findViewById(R.id.previous_step_cooking_button);
        setButtonsVisible();
        nextStepButton.setOnClickListener((View v) -> {
           if (selectedIndex < stepsList.size() - 1) {
               selectedIndex += 1;
               setButtonsVisible();
               replaceFragment();
           }
        });
        previousStepButton.setOnClickListener(view -> {
            if (selectedIndex - 1 >= 0) {
                selectedIndex -= 1;
                setButtonsVisible();
                replaceFragment();
            }
        });
    }

    private void setButtonsVisible() {
        if (stepsList == null) {
            previousStepButton.setEnabled(false);
            nextStepButton.setEnabled(false);
            return;
        }
        if (selectedIndex <= 0) {
            previousStepButton.setEnabled(false);
        } else {
            previousStepButton.setEnabled(true);
        }
        if (selectedIndex >= stepsList.size() - 1) {
            nextStepButton.setEnabled(false);
        } else {
            nextStepButton.setEnabled(true);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            if (!getResources().getBoolean(R.bool.isTablet)) {
                hideSystemUI();

            }

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            if (!getResources().getBoolean(R.bool.isTablet)) {
                showSystemUI();
            }

        }
        super.onConfigurationChanged(newConfig);
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                         View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(BakeDetailActivity.SELECTEDPOSITION, selectedIndex);
        outState.putParcelableArrayList(BakeDetailActivity.STEPS, stepsList);
        super.onSaveInstanceState(outState);
    }
}
