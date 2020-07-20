package com.alexblackmore.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ThingToDoHolder> {
    private List<ThingToDo> toDoList = new ArrayList<>();

    @NonNull
    @Override
    public ThingToDoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View recyclerViewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new ThingToDoHolder(recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ThingToDoHolder holder, int position) {
        ThingToDo currentThingToDo = toDoList.get(position);
        holder.textViewTitle.setText(currentThingToDo.getTitle());
        holder.textViewDescription.setText(currentThingToDo.getDescription());
        holder.textViewImportance.setText(currentThingToDo.getImportance());
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public void setToDoList(List<ThingToDo> toDoList) {
        this.toDoList = toDoList;
        notifyDataSetChanged();
    }

    public class ThingToDoHolder extends  RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewImportance;

        public ThingToDoHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewImportance = itemView.findViewById(R.id.text_view_importance);
        }
    }

    public ThingToDo getThingAtPosition (int position) {
        return toDoList.get(position);
    }
}
