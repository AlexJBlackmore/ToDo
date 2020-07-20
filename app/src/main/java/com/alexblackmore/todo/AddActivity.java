package com.alexblackmore.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_TITLE =
            "com.example.android.roomwordssample.TITLE";
    public static final String EXTRA_DESC =
            "com.example.android.roomwordssample.DESC";
    public static final String EXTRA_IMPORTANCE =
            "com.example.android.roomwordssample.IMPORTANCE";

    public Spinner importanceSpinner;

    private EditText editTextTitle;
    private EditText editTextDesc;
    private String importance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextTitle = findViewById(R.id.editTextTitleInput);
        editTextDesc = findViewById(R.id.editTextDescInput);

        // Spinner adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.importance_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Spinner
        importanceSpinner = findViewById(R.id.spinner);
        if (importanceSpinner != null) {
            importanceSpinner.setAdapter(adapter);
            importanceSpinner.setOnItemSelectedListener(this);
        }

        // Button
        final Button button = findViewById(R.id.buttonAdd);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (editTextTitle.equals("") || editTextDesc.equals("") || (importance == null)) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String title = editTextTitle.getText().toString();
                    String desc = editTextDesc.getText().toString();
                    replyIntent.putExtra(EXTRA_TITLE, title);
                    replyIntent.putExtra(EXTRA_DESC, desc);
                    replyIntent.putExtra(EXTRA_IMPORTANCE, importance);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Add importance string to intent with putString
        importance = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}