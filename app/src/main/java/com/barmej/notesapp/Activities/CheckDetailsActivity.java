package com.barmej.notesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.barmej.notesapp.Constants;
import com.barmej.notesapp.R;

public class CheckDetailsActivity extends AppCompatActivity {
    EditText editText;
    CheckBox checkBox;
    String text;
    Boolean check;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_check_details);
        editText = findViewById(R.id.checkNoteEditText);
        checkBox = findViewById(R.id.checkNoteCheckBox);
        button = findViewById(R.id.button3);
        text = getIntent().getStringExtra(Constants.check_text_edit);
        check = getIntent().getBooleanExtra(Constants.check_check_edit, false);
        editText.setText(text);
        checkBox.setChecked(check);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onresult();
            }
        });
    }

    private void onresult() {
        String text1 = editText.getText().toString();
        Boolean ck = checkBox.isChecked();
        Intent intent = new Intent();
        intent.putExtra(Constants.check_check_result, ck);
        intent.putExtra(Constants.check_edit_result, text1);
        setResult(Constants.check_result, intent);
        finish();


    }
}