package com.kkolontay.baking.extension;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.kkolontay.baking.R;
import com.kkolontay.baking.model.Ingredient;
import com.kkolontay.baking.view.mainviewactive.MainActivity;

import java.util.ArrayList;

public class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private ArrayList<Ingredient> ingredients;
    private Context context;

    public ListRemoteViewsFactory(Context context) {
        this.context = context;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        ingredients = ChoosenRecipeIngredientList.getInstance().getIngredients();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (ingredients == null) {
            return 0;
        }

        return ChoosenRecipeIngredientList.getInstance().getIngredients().size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredient_item);
        views.setTextViewText(R.id.ingredient_description_text_view, ingredients.get(i).getIngredient());
        views.setTextViewText(R.id.measure_text_view, ingredients.get(i).getMeasure());
        views.setTextViewText(R.id.quantity_text_view, Float.toString(ingredients.get(i).getQuantity()));
        Intent intent = new Intent(context, MainActivity.class);
        views.setOnClickFillInIntent(R.id.relative_layout_item_widget, intent);
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
