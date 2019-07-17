package com.kkolontay.baking.view.mainviewactive;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.kkolontay.baking.R;
import com.kkolontay.baking.databinding.ActivityMainBinding;
import com.kkolontay.baking.extension.AlertMessageDialog;
import com.kkolontay.baking.extension.ChoosenRecipeIngredientList;
import com.kkolontay.baking.extension.DesiredRecipeIngredientsWidgetProvider;
import com.kkolontay.baking.extension.idlingresource.SimpleIdlingResource;
import com.kkolontay.baking.model.BakeModel;
import com.kkolontay.baking.view.bakingdetail.BakeDetailActivity;
import com.kkolontay.baking.viewmodel.MainViewModel;


public class MainActivity extends AppCompatActivity implements MainRecyclerViewAdapter.OnMainItemClickListener {
    private static final String ERROR = "Error";
    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter recyclerViewAdapter;
    private GridLayoutManager gridLayoutManager;
    public static final String MODEL = "BackerModel";
    private ActivityMainBinding activityMainBinding;
    @Nullable private SimpleIdlingResource simpleIdlingResource;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIdlingResource();
        if (savedInstanceState == null) {
            if (simpleIdlingResource != null) {
                simpleIdlingResource.setIdleState(false);
            }
        }
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        recyclerView = activityMainBinding.mainRecyclerView;
        MainViewModel  viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getBakes().observe(this, bakes -> {
            recyclerViewAdapter.setBakeModels(bakes);
            recyclerViewAdapter.notifyDataSetChanged();
            if (simpleIdlingResource != null) {
                simpleIdlingResource.setIdleState(true);
            }
        });

        viewModel.getErrorMessage().observe(this, error -> {
            AlertMessageDialog messageDialog = new AlertMessageDialog(ERROR, error);
            messageDialog.show(getSupportFragmentManager(), ERROR);
        });
        initRecyclerView();

    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (simpleIdlingResource == null) {
            simpleIdlingResource = new SimpleIdlingResource();
        }
        return simpleIdlingResource;
    }

    private void handleActionUpdateWidgets() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetsId = appWidgetManager.getAppWidgetIds(new ComponentName(this, DesiredRecipeIngredientsWidgetProvider.class));
        DesiredRecipeIngredientsWidgetProvider.updateIngredientWidgets(this,
                appWidgetManager,
                appWidgetsId);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetsId, R.id.widget_list_view);
    }

    private void initRecyclerView() {
        int columns = getColumns();
        recyclerViewAdapter = new MainRecyclerViewAdapter(getApplicationContext(), this);
        gridLayoutManager = new GridLayoutManager(this, columns);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private int getColumns() {
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            boolean isTablet = getResources().getBoolean(R.bool.isTablet);
            if (isTablet) {
                return 2;
            } else {
                return 1;
            }
        }
        else {
            boolean isTablet = getResources().getBoolean(R.bool.isTablet);
            if (isTablet) {
                return 3;
            } else {
                return 2;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerViewAdapter = null;
        gridLayoutManager = null;
    }

    @Override
    public void onItemClick(BakeModel model) {
        Intent intent = new Intent(getApplicationContext(), BakeDetailActivity.class);
        ChoosenRecipeIngredientList.getInstance().setData(model.getIngredients());
        handleActionUpdateWidgets();
        intent.putExtra(MODEL, model);
        startActivity(intent);

    }
}
