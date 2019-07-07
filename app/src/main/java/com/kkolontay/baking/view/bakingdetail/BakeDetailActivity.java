package com.kkolontay.baking.view.bakingdetail;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
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

public class BakeDetailActivity extends AppCompatActivity implements BakeDetaiItemChoosenProtocol {

    private BakeModel model;
    private static final String TAG = BakeDetailActivity.class.getSimpleName();
    private static final String MODEL = "model";
    public static final String INGREDIENTS = "ingredientsItem";
    public static final String SELECTEDPOSITION = "selectedPosition";
    public static final String STEPS = "cookingSteps";
    private boolean twoPanel;
    private int selectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bake_detail);

        if (savedInstanceState == null) {
            selectedIndex = 0;
            model = getIntent().getParcelableExtra(MainActivity.MODEL);
        } else {
            model =  savedInstanceState.getParcelable(MODEL);
            selectedIndex = savedInstanceState.getInt(SELECTEDPOSITION);
        }
        if(findViewById(R.id.cooking_step_frame) != null) {
            twoPanel = true;
            replaceFragment();
        } else {
            twoPanel = false;
        }
        setTitle(model.getName());
        Log.v(TAG, model.getImage());
        RecipeStepsFragment fragment = new RecipeStepsFragment(model);
        FragmentManager manager = getSupportFragmentManager();
        fragment.setDelegate(this);
        manager.beginTransaction()
                .add(R.id.steps_fragment_container, fragment)
                .commit();
    }


        @Override

    public void onSaveInstanceState(@NonNull Bundle outState ) {
        outState.putParcelable(MODEL, model);
        outState.putInt(SELECTEDPOSITION, selectedIndex);
        super.onSaveInstanceState(outState);
    }

    private void replaceFragment() {
        CookingStepFragment fragment = new CookingStepFragment(model.getSteps().get(selectedIndex));
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
            selectedIndex = stepPosition;
            if (twoPanel == true) {
                replaceFragment();
            } else {
                if (stepPosition >= 0 && stepPosition < model.getSteps().size()) {
                    Intent intent = new Intent(this, CookingStepActivity.class);
                    intent.putExtra(SELECTEDPOSITION, stepPosition);
                    intent.putParcelableArrayListExtra(STEPS, model.getSteps());
                    startActivity(intent);
                }
            }
        }
    }
}


