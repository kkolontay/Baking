package com.kkolontay.baking.viewmodel.backingdetailviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kkolontay.baking.model.BakeModel;

public class BakeDetailViewModel extends AndroidViewModel {
    private MutableLiveData<BakeModel> bake;
    private MutableLiveData<Integer> selectedIndex;
    private Application application;

    public BakeDetailViewModel(@NonNull Application application) {
        super(application);
        bake = new MutableLiveData<>();
        this.application = application;
        selectedIndex = new MutableLiveData<>();
    }

    public MutableLiveData<BakeModel> getBake() {
        return bake;
    }

    public MutableLiveData<Integer> getSelectedIndex() {
        return selectedIndex;
    }

    public void setBake(BakeModel model) {
        bake.setValue(model);
    }

    public void setSelectedIndex(int index) {
        selectedIndex.setValue(Integer.valueOf(index));
    }
}
