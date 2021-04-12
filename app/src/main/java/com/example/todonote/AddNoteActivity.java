package com.example.todonote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.todonote.R.string.worning_fill_fills;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerDaysOfWeek;
    private RadioGroup radioGroupPriority;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextTextDescription);
        spinnerDaysOfWeek = findViewById(R.id.spinnerDaysOfWeek);
        radioGroupPriority = findViewById(R.id.radioGroupPriority);

    }

    public void onClickSavedNote(View view) {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        int dayOfWeek = spinnerDaysOfWeek.getSelectedItemPosition();
        int radioButtonId = radioGroupPriority.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        int priority = Integer.parseInt(radioButton.getText().toString());
        if (isFilled(title, description)) {
            Note note = new Note(title, description, dayOfWeek, priority);
            viewModel.insertNote(note);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, worning_fill_fills, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isFilled(String title, String description) {
        return !title.isEmpty() && !description.isEmpty();
    }

}