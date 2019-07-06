package com.kkolontay.baking.view.ingredientslist;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.kkolontay.baking.R;
import com.kkolontay.baking.model.Ingredient;
import com.kkolontay.baking.view.bakingdetail.BakeDetailActivity;
import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {
    private static final String title = "Ingredients";
    private ArrayList<Ingredient> ingredients;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        if (savedInstanceState != null) {
            ingredients =  savedInstanceState.getParcelableArrayList(title);
        } else {
            ingredients =  getIntent().getParcelableArrayListExtra(BakeDetailActivity.INGREDIENTS);
        }
        setTitle(title);
        listView = findViewById(R.id.ingredients_list_view);
        IngredientsListAdapter listAdapter = new IngredientsListAdapter(this, ingredients);
        listView.setAdapter(listAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(title, ingredients);
        super.onSaveInstanceState(outState);
    }
}
