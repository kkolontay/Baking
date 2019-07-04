package com.kkolontay.baking.view.bakingdetail.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.kkolontay.baking.R;
import java.util.ArrayList;

public class RecipeListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> stepsName;

    public RecipeListAdapter(Context context, ArrayList<String> steps) {
        this.context = context;
        this.stepsName = steps;
    }

    @Override
    public int getCount() {
        if (stepsName != null) {
            return stepsName.size();
        } else {
            return  0;
        }
    }

    @Override
    public Object getItem(int i) {
        if (stepsName != null) {
            return stepsName.get(i);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        if (stepsName != null) {
            return i;
        } else {
            return  0;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {
           LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.recipe_list_item,null);
        }
        TextView textView = view.findViewById(R.id.step_preparing_text_view);
        textView.setText(stepsName.get(i));
        view.setTag(new Integer(i));

        return view;
    }
}
