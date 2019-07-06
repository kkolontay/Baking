package com.kkolontay.baking.view.cookingstep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.PersistableBundle;
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
        setTitle(stepsList.get(selectedIndex).getShortDescription());
        CookingStepFragment fragment = new CookingStepFragment(stepsList.get(selectedIndex));
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.cooking_step_frame, fragment)
                .commit();
    }

    private  void configureButtons() {
        nextStepButton = findViewById(R.id.next_step_cooking_button);
        previousStepButton = findViewById(R.id.previous_step_cooking_button);
        setButtonsVisible();
        nextStepButton.setOnClickListener((View v) -> {
           if (selectedIndex < stepsList.size() - 1) {
               selectedIndex += 1;
               setButtonsVisible();
           }
        });
        previousStepButton.setOnClickListener(view -> {
            if (selectedIndex - 1 >= 0) {
                selectedIndex -= 1;
                setButtonsVisible();
            }
        });
    }

    private void setButtonsVisible() {
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(BakeDetailActivity.SELECTEDPOSITION, selectedIndex);
        outState.putParcelableArrayList(BakeDetailActivity.STEPS, stepsList);
        super.onSaveInstanceState(outState);
    }
}
