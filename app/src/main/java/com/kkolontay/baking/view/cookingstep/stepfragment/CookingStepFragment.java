package com.kkolontay.baking.view.cookingstep.stepfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kkolontay.baking.R;
import com.kkolontay.baking.model.Step;


public class CookingStepFragment extends Fragment {
    private Step step;
    private TextView textView;


    public CookingStepFragment(Step step) {
        super();
        this.step = step;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cooking_step_item, container, false);
        textView = rootView.findViewById(R.id.step_cooking_description_text_view);
        textView.setText(step.getDescription());
        return rootView;
    }
}
