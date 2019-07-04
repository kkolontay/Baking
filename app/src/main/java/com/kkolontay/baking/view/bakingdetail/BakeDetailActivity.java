package com.kkolontay.baking.view.bakingdetail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.kkolontay.baking.R;
import com.kkolontay.baking.model.BakeModel;
import com.kkolontay.baking.view.bakingdetail.fragments.recipefragment.RecipeStepsFragment;
import com.kkolontay.baking.view.mainviewactive.MainActivity;

public class BakeDetailActivity extends AppCompatActivity {

    private BakeModel model;
    private static final String TAG = BakeDetailActivity.class.getSimpleName();
    private static final String MODEL = "model";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bake_detail);
        if (savedInstanceState == null) {
            model = (BakeModel) getIntent().getSerializableExtra(MainActivity.MODEL);
        } else {
            model = (BakeModel) savedInstanceState.getSerializable(MODEL);
        }
        setTitle(model.getName());
        Log.v(TAG, model.getImage());
        RecipeStepsFragment fragment = new RecipeStepsFragment(model);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.steps_fragment_container, fragment)
                .commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(MODEL, model);
        super.onSaveInstanceState(outState);
    }
}
