package com.kkolontay.baking.view.mainviewactive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kkolontay.baking.R;
import com.kkolontay.baking.databinding.ActivityMainListItemBinding;
import com.kkolontay.baking.model.BakeModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder> {


    private WeakReference<MainRecyclerViewAdapter.OnMainItemClickListener> listener;


    private ArrayList<BakeModel> bakeModels;
    private Context context;

    public MainRecyclerViewAdapter(Context context, MainRecyclerViewAdapter.OnMainItemClickListener listener) {
        this.context = context;
        this.listener = new WeakReference<>(listener);
    }

    public void setBakeModels(ArrayList<BakeModel> bakeModels) {
        this.bakeModels = bakeModels;
    }

    public interface OnMainItemClickListener {
        void onItemClick(BakeModel model);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ActivityMainListItemBinding activityMainListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.activity_main_list_item, parent, false);
        return new MainViewHolder(activityMainListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        if (bakeModels == null) {
            return;
        }
        BakeModel bake = bakeModels.get(position);
        holder.getActivityMainListItemBinding().setBakeCard(bake);
        holder.getActivityMainListItemBinding().setImageUrl(bake.getImage());
        MainViewHolderClickListener mainListener = new MainViewHolderClickListener(position);
        holder.getActivityMainListItemBinding().setClickListenerObject(mainListener);

    }

    @Override
    public int getItemCount() {
        if (bakeModels == null) {
            return 0;
        }
        return bakeModels.size();
    }


    public class MainViewHolder extends RecyclerView.ViewHolder {
        private ActivityMainListItemBinding activityMainListItemBinding;


        public ActivityMainListItemBinding getActivityMainListItemBinding() {
            return activityMainListItemBinding;
        }

        public MainViewHolder(@NonNull ActivityMainListItemBinding activityMainListItemBinding) {
            super(activityMainListItemBinding.getRoot());
            this.activityMainListItemBinding = activityMainListItemBinding;

        }
    }

    public class MainViewHolderClickListener {
        private int position;

        public MainViewHolderClickListener(int position) {
            this.position = position;
        }

        public void clickListener(View view) {
            listener.get().onItemClick(bakeModels.get(position));
        }
    }
}

