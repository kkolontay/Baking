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
import com.kkolontay.baking.view.ingredientslist.IngredientsActivity;
import com.kkolontay.baking.view.mainviewactive.MainActivity;

public class BakeDetailActivity extends AppCompatActivity implements BakeDetaiItemChoosenProtocol {

    private BakeModel model;
    private static final String TAG = BakeDetailActivity.class.getSimpleName();
    private static final String MODEL = "model";
    public static final String INGREDIENTS = "ingredientsItem";
    public static final String SELECTEDPOSITION = "selectedPosition";
    public static final String STEPS = "cookingSteps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bake_detail);
        if (savedInstanceState == null) {
            model = getIntent().getParcelableExtra(MainActivity.MODEL);
        } else {
            model =  savedInstanceState.getParcelable(MODEL);
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
        super.onSaveInstanceState(outState);
    }

    @Override
    public void bakeItemChoosen(int position) {
        if (position == 0) {
            Intent intent = new Intent(this, IngredientsActivity.class);
            intent.putExtra(INGREDIENTS, model.getIngredients());
            startActivity(intent);
        } else {
            int stepPosition = position - 1;
            if (stepPosition >= 0 && stepPosition < model.getSteps().size()) {
                Intent intent = new Intent(this, CookingStepActivity.class);
                intent.putExtra(SELECTEDPOSITION, stepPosition);
                intent.putParcelableArrayListExtra(STEPS, model.getSteps());
                startActivity(intent);
            }
        }
    }
}


