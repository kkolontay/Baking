package com.kkolontay.baking.view.ingredientslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kkolontay.baking.R;
import com.kkolontay.baking.model.Ingredient;

import java.util.ArrayList;

public class IngredientsListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Ingredient> ingredients;

    public IngredientsListAdapter(Context context, ArrayList<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @Override
    public int getCount() {
        if (ingredients != null) {
            return ingredients.size();
        } else {
            return  0;
        }
    }

    @Override
    public Object getItem(int i) {
        if (ingredients != null) {
            return ingredients.get(i);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        if (ingredients != null) {
            return i;
        } else {
            return  0;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.ingredient_item,null);
        }

        TextView quantityTextView = view.findViewById(R.id.quantity_text_view);
        quantityTextView.setText(Float.toString(ingredients.get(i).getQuantity()));
        TextView measuareTextView = view.findViewById(R.id.measure_text_view);
        TextView ingredientDescriptionTextView = view.findViewById(R.id.ingredient_description_text_view);
        measuareTextView.setText(ingredients.get(i).getMeasure());
        ingredientDescriptionTextView.setText(ingredients.get(i).getIngredient());

        return view;
    }
}
