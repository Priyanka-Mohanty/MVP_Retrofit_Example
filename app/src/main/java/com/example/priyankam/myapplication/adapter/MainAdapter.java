package com.example.priyankam.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.priyankam.myapplication.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    public MainAdapter(List<String> items) {
        this.items = items;

    }

    interface Listener {
        void onItemClicked(String item);
    }

    private List<String> items;
 //   private Listener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_main_item, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final String item = items.get(position);
        viewHolder.textName.setText(item);
        //holder.textView.setOnClickListener(v -> listener.onItemClicked(item));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName;

        ViewHolder(View view) {
            super(view);
            textName = (TextView) itemView.findViewById(R.id.textName);

        }
    }
}
