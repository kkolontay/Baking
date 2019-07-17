package com.kkolontay.baking.view.bakingdetail;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.kkolontay.baking.R;
import com.kkolontay.baking.model.BakeModel;
import com.kkolontay.baking.view.BakeDetaiItemChoosenProtocol;
import com.kkolontay.baking.view.bakingdetail.fragments.recipefragment.RecipeStepsFragment;
import com.kkolontay.baking.view.cookingstep.CookingStepActivity;
import com.kkolontay.baking.view.cookingstep.stepfragment.CookingStepFragment;
import com.kkolontay.baking.view.ingredientslist.IngredientsActivity;
import com.kkolontay.baking.view.mainviewactive.MainActivity;
import com.kkolontay.baking.viewmodel.backingdetailviewmodel.BakeDetailViewModel;

public class BakeDetailActivity extends AppCompatActivity implements BakeDetaiItemChoosenProtocol {

    private BakeModel model;
    private static final String TAG = BakeDetailActivity.class.getSimpleName();
    public static final String INGREDIENTS = "ingredientsItem";
    public static final String SELECTEDPOSITION = "selectedPosition";
    public static final String STEPS = "cookingSteps";
    private boolean twoPanel;
    private BakeDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bake_detail);
        model = getIntent().getParcelableExtra(MainActivity.MODEL);
        viewModel = ViewModelProviders.of(this).get(BakeDetailViewModel.class);
        viewModel.getBake().observe(this, bake -> {
            this.model = bake;
            launchFrame();
        });

        viewModel.getSelectedIndex().observe(this, selectedIndexModel -> {
            if (selectedIndexModel != null) {
                            if (twoPanel) {
                replaceFragment(selectedIndexModel);
            } else {
                if (selectedIndexModel >= 0 && selectedIndexModel < model.getSteps().size()) {
                    Intent intent = new Intent(this, CookingStepActivity.class);
                    intent.putExtra(SELECTEDPOSITION, selectedIndexModel);
                    intent.putParcelableArrayListExtra(STEPS, model.getSteps());
                    startActivity(intent);
                }
            }
            }
        });
        if (model != null) {
            viewModel.setBake(model);
        }

        if (findViewById(R.id.cooking_step_frame) != null) {
            twoPanel = true;
            replaceFragment(0);
        } else {
            twoPanel = false;
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Log.v(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }

    private void launchFrame() {
        if (model != null) {
            setTitle(model.getName());
            Log.v(TAG, model.getImage());
            RecipeStepsFragment fragment = new RecipeStepsFragment(model);
            FragmentManager manager = getSupportFragmentManager();
            fragment.setDelegate(this);
            manager.beginTransaction()
                    .replace(R.id.steps_fragment_container, fragment)
                    .commit();
        }
    }

    @VisibleForTesting
    public void setModel(BakeModel model) {
        this.model = model;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                launchFrame();
            }
        });

    }

    private void replaceFragment(int index) {
        CookingStepFragment fragment = new CookingStepFragment(model.getSteps().get(index));
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.cooking_step_frame, fragment)
                .commit();

    }

    @Override
    public void bakeItemChoosen(int position) {
        if (position == 0) {
            Intent intent = new Intent(this, IngredientsActivity.class);
            intent.putExtra(INGREDIENTS, model.getIngredients());
            startActivity(intent);
        } else {
            int stepPosition = position - 1;
            viewModel.setSelectedIndex(stepPosition);
        }
    }
}


