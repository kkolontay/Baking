package com.kkolontay.baking.services;

import com.kkolontay.baking.model.BakeModel;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<BakeModel>> fetchBakings();
}
