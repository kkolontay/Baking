package com.kkolontay.baking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.kkolontay.baking.Extension.AlertMessageDialog;
import com.kkolontay.baking.ViewModel.MainViewModel;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String ERROR = "Error";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModel  viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getBakes().observe(this, bakes -> {

        });

        viewModel.getErrorMessage().observe(this, error -> {
            AlertMessageDialog messageDialog = new AlertMessageDialog(ERROR, error);
            messageDialog.show(getSupportFragmentManager(), ERROR);
        });

    }
}
