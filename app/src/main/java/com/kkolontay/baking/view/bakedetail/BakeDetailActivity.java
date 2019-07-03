package com.kkolontay.baking.view.bakedetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.kkolontay.baking.R;
import com.kkolontay.baking.model.BakeModel;
import com.kkolontay.baking.view.mainviewactive.MainActivity;

public class BakeDetailActivity extends AppCompatActivity {

    private BakeModel model;
    private static final String TAG = BakeDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bake_detail);
        model = (BakeModel) getIntent().getSerializableExtra(MainActivity.MODEL);
        setTitle(model.getName());
        Log.v(TAG, model.getImage());
    }
}
