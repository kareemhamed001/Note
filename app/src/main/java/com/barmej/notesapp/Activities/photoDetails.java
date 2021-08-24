package com.barmej.notesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.barmej.notesapp.Constants;
import com.barmej.notesapp.R;

public class photoDetails extends AppCompatActivity {
    ImageView imageView;
    EditText editText;
    Uri image;
    String text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_photo_details);
        imageView = findViewById(R.id.photoImageView);
        editText = findViewById(R.id.photoNoteEditText);
        image = getIntent().getParcelableExtra(Constants.photo_edit);
        text = getIntent().getStringExtra(Constants.photo_text_edit);
        imageView.setImageURI(image);
        editText.setText(text);
        button = findViewById(R.id.button2);
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
        intent.putExtra(Constants.photo_result, image);
        intent.putExtra(Constants.photo_text_result, text1);
        setResult(Constants.photo_activity_result, intent);
        finish();

    }
}