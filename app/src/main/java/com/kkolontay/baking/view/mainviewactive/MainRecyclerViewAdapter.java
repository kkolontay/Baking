package com.kkolontay.baking.view.mainviewactive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kkolontay.baking.R;
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
         void onItemClick( BakeModel model);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_list_item, parent, false);
        MainViewHolder viewHolder = new MainViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        if (bakeModels == null) {return;}
        holder.bindData(bakeModels.get(position), position);

    }

    @Override
    public int getItemCount() {
        if (bakeModels == null) {
            return 0;
        }
        return bakeModels.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private LinearLayout linearLayout;
        private int position;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.baking_image_view);
            textView = itemView.findViewById(R.id.baking_name_text_view);
            linearLayout = itemView.findViewById(R.id.place_holder_card_view_main_item);
        }
        public void bindData(BakeModel model, int position) {
            this.position = position;
            if (model.getImage() != null && !model.getImage().isEmpty()) {
                Glide.with(context)
                        .load(model.getImage())
                        .centerCrop()
                        .placeholder(R.drawable.baking_placeholder)
                        .error(R.drawable.baking_placeholder)
                        .into(imageView);
            } else {
                imageView.setImageResource(R.drawable.baking_placeholder);
            }
            textView.setText(model.getName());
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.get().onItemClick(bakeModels.get(position));
                }
            });
        }
    }
}
