package com.barmej.notesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.barmej.notesapp.Constants;
import com.barmej.notesapp.R;

public class TextDetailsActivity extends AppCompatActivity {
    EditText editText;
    String text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        editText = findViewById(R.id.noteEditText);
        button = findViewById(R.id.button4);
        text = getIntent().getStringExtra(Constants.details_note_edit);
        editText.setText(text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onresult();
            }
        });
    }

    private void onresult() {
        String text1 = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Constants.check_edit_result, text1);
        setResult(Constants.details_result, intent);
        finish();
        finishAndRemoveTask();

    }
}