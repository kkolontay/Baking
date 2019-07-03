package com.kkolontay.baking.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.kkolontay.baking.view.mainviewactive.MainActivity;
import com.kkolontay.baking.model.BakeModel;
import com.kkolontay.baking.services.JsonPlaceHolderApi;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<BakeModel>> bakes;
    private MutableLiveData<String> errorMessage;
    private static final  String TAG = MainActivity.class.getSimpleName();

    public MainViewModel(@NonNull Application application) {
        super(application);
        fetchData();
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ArrayList<BakeModel>> call = jsonPlaceHolderApi.fetchBakings();
        call.enqueue(new Callback<ArrayList<BakeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<BakeModel>> call, Response<ArrayList<BakeModel>> response) {
                if(!response.isSuccessful()) {
                    Log.v(TAG, "Code: " + response.code());
                    errorMessage.setValue("Code: " + response.code());
                    return;
                }
                ArrayList<BakeModel> bakings = response.body();
                bakes.setValue(bakings);

            }

            @Override
            public void onFailure(Call<ArrayList<BakeModel>> call, Throwable t) {
                Log.v(TAG, t.getMessage());
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    public MutableLiveData<String> getErrorMessage() {
        if (errorMessage == null) {
            errorMessage = new MutableLiveData<>();
        }
        return errorMessage;
    }

    public MutableLiveData<ArrayList<BakeModel>> getBakes() {
        if (bakes == null) {
            bakes = new MutableLiveData<>();
        }
        return bakes;
    }
}
