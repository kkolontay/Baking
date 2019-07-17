package com.kkolontay.baking.viewmodel.backingdetailviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.kkolontay.baking.model.BakeModel;

public class BakeDetailViewModel extends AndroidViewModel {
    private MutableLiveData<BakeModel> bake;
    private MutableLiveData<Integer> selectedIndex;

    public BakeDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<BakeModel> getBake() {
        if (bake == null) {
            bake = new MutableLiveData<>();
        }
        return bake;
    }

    public MutableLiveData<Integer> getSelectedIndex() {

        if (selectedIndex == null) {
            selectedIndex = new MutableLiveData<>();
        }
        return selectedIndex;
    }

    public void setBake(BakeModel model) {
        if (bake == null) {
            bake = new MutableLiveData<>();
        }
        bake.setValue(model);
    }

    public void setSelectedIndex(int index) {
        if (selectedIndex == null) {
            selectedIndex = new MutableLiveData<>();
        }
        selectedIndex.setValue(index);
    }
}
