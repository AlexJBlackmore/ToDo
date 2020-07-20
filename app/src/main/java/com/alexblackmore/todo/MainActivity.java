package com.alexblackmore.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ThingToDoViewModel thingToDoViewModel;
    public static final int NEW_THING_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView set up
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

        // Setting its adapter
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        // Observing the LiveData
        thingToDoViewModel = ViewModelProviders.of(this).get(ThingToDoViewModel.class);
        thingToDoViewModel.getAll().observe(this, new Observer<List<ThingToDo>>() {
            @Override
            public void onChanged(@Nullable List<ThingToDo> thingsToDoList) {
                adapter.setToDoList(thingsToDoList);
            }
        });

        // Add the functionality to swipe items in the recycler view to delete that item
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ThingToDo thingToDo = adapter.getThingAtPosition(position);
                Toast.makeText(MainActivity.this, "Deleting " +
                        thingToDo.getTitle(), Toast.LENGTH_LONG).show();

                // Delete the word
                thingToDoViewModel.deleteThingToDo(thingToDo);
            }
        });

        helper.attachToRecyclerView(recyclerView);

        // Fab(ulous) stuff
        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, NEW_THING_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_THING_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ThingToDo thingToDo = new ThingToDo(data.getStringExtra(AddActivity.EXTRA_TITLE),
                    data.getStringExtra(AddActivity.EXTRA_DESC),
                    data.getStringExtra(AddActivity.EXTRA_IMPORTANCE));
            thingToDoViewModel.insert(thingToDo);
        } else {
            Toast.makeText(this, "Bloop?", Toast.LENGTH_SHORT).show();
        }
    }
}