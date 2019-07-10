package com.kkolontay.baking.extension;

import com.kkolontay.baking.model.Ingredient;

import java.util.ArrayList;

public class ChoosenRecipeIngredientList {

    private static ChoosenRecipeIngredientList sSoleInstance;
    private ArrayList<Ingredient> ingredients;

    private ChoosenRecipeIngredientList() {
    }

    public static ChoosenRecipeIngredientList getInstance() {
        if (sSoleInstance == null) {
            sSoleInstance = new ChoosenRecipeIngredientList();
        }
        return sSoleInstance;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setData(ArrayList<Ingredient> ingredientsObject) {
        if(ingredientsObject != null) {
            ingredients = ingredientsObject;
        }
    }
}
