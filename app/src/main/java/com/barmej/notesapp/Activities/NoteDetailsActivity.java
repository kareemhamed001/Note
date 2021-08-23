package com.barmej.notesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import com.barmej.notesapp.Data.TextDetails;
import com.barmej.notesapp.R;

public class NoteDetailsActivity extends AppCompatActivity {
    private EditText editText;
    private SharedPreferences sharedPreferences;
    TextDetails textDetails;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences.Editor meditTExt=sharedPreferences.edit();
        meditTExt.putString("details",editText.getText().toString());
        meditTExt.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        editText=findViewById(R.id.noteEditText);
        sharedPreferences=getSharedPreferences("mypref",MODE_PRIVATE);
        editText.setText(sharedPreferences.getString("details"," "));
       TextDetails textDetails=new TextDetails(editText.getText().toString());

    }


}