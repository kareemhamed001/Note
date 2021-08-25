package com.barmej.notesapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.barmej.notesapp.Constants;
import com.barmej.notesapp.R;

public class photoDetails extends AppCompatActivity {
    ImageView imageView;
    EditText editText;
    Uri image;
    String text;
    Button button;
    Uri mNewimage;
    static final int READ_PHOTO_FROM_GALLAREY=600;
    static final int ADD_PHOTO=601;

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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPicture();
            }
        });

    }


    private void onresult() {
        String text1 = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Constants.photo_result, mNewimage);
        intent.putExtra(Constants.photo_text_result, text1);
        setResult(Constants.photo_activity_result, intent);
        finishAndRemoveTask();
    }
    public void selectPicture() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_PHOTO_FROM_GALLAREY);

        } else {
            firePickPhoto();
        }
    }

    public void firePickPhoto() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(Intent.createChooser(intent, "اختر صوره"), ADD_PHOTO);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_PHOTO_FROM_GALLAREY) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                firePickPhoto();
            } else {
                Toast.makeText(this, "تحتاج الي صلاحيه الوصول الي الملفات", Toast.LENGTH_SHORT).show();
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PHOTO) {
            if (data != null && data.getData() != null) {
                setSelectedPhoto(data.getData());

                getContentResolver().takePersistableUriPermission(data.getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                Toast.makeText(this, "فشل في الوصول الي الصوره", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setSelectedPhoto(Uri data) {

        imageView.setImageURI(data);
        mNewimage = data;

    }

}