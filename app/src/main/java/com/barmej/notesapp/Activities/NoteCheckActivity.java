package com.barmej.notesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import com.barmej.notesapp.R;

public class NoteCheckActivity extends AppCompatActivity {
    EditText editText;
    CheckBox checkBox;
   public SharedPreferences sharedPreferences;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor meditTExt=sharedPreferences.edit();
        meditTExt.putString("edit_text_chck_note",editText.getText().toString());
        meditTExt.putBoolean("checkbox_check_note",checkBox.isChecked());
        meditTExt.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_check_details);
        editText=findViewById(R.id.checkNoteEditText);
        checkBox=findViewById(R.id.checkNoteCheckBox);
        sharedPreferences=getSharedPreferences("mypref",MODE_PRIVATE);
        editText.setText(sharedPreferences.getString("edit_text_chck_note",""));
        checkBox.setChecked(sharedPreferences.getBoolean("checkbox_check_note",false));


    }
}