package com.kkolontay.baking.view.bakingdetail.fragments.recipefragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kkolontay.baking.R;
import com.kkolontay.baking.model.BakeModel;
import com.kkolontay.baking.model.Step;
import com.kkolontay.baking.view.BakeDetaiItemChoosenProtocol;
import com.kkolontay.baking.view.bakingdetail.fragments.RecipeListAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class RecipeStepsFragment extends Fragment {
    private static final String TAG = RecipeStepsFragment.class.getSimpleName();
    private BakeModel model;
    private ArrayList<String> steps;
    private WeakReference<BakeDetaiItemChoosenProtocol> delegate;

    public RecipeStepsFragment(BakeModel model) {
        this.model = model;
        initSteps();
    }

    public RecipeStepsFragment(){}

    private void initSteps() {
        steps = new ArrayList<>();
        steps.add("Ingredients");
        ArrayList<Step> stepsObject = model.getSteps();
        if (stepsObject != null) {
            for (Step step : stepsObject) {
                steps.add(step.getShortDescription());
            }
        }
    }

    public void setDelegate(BakeDetaiItemChoosenProtocol delegate) {
            this.delegate = new WeakReference<BakeDetaiItemChoosenProtocol>(delegate);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.baking_steps_fragment, container, false);
        ListView listView = view.findViewById(R.id.baking_staps_list_view);
        RecipeListAdapter adapter = new RecipeListAdapter(getContext(), steps);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                delegate.get().bakeItemChoosen(i);

                Log.v(TAG, new Integer(i).toString());
            }
        });
        return view;
    }
}
