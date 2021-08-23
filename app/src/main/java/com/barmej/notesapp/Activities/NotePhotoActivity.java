package com.barmej.notesapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.barmej.notesapp.R;

import java.util.ArrayList;

public class NotePhotoActivity extends AppCompatActivity {
    ImageView mNewPhotoIV;
    private Uri photos[] ;
    EditText editText;
    BitmapDrawable bitmapDrawable;
    final static int Read_photo_from_gallery_permition=120;
    final static int PICK_IMAGE=130;
    Uri mselectedPhotoURI;
    SharedPreferences sharedPreferences;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor mPhoto=sharedPreferences.edit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_photo_details);
        mNewPhotoIV=findViewById(R.id.photoImageView);
        editText=findViewById(R.id.photoNoteEditText);
        sharedPreferences=getSharedPreferences("mypref",MODE_PRIVATE);


        mNewPhotoIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPhoto();
            }
        });


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == Read_photo_from_gallery_permition) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                firePickPhotoIntent();
            } else {
                Toast.makeText(this, R.string.Read_permition_needed_access_files, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {
            if (data != null && data.getData() != null) {
                setSelectedPhoto(data.getData());

                getContentResolver().takePersistableUriPermission(data.getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                Toast.makeText(this, "فشل في الوصول الي الصوره", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void selectPhoto() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Read_photo_from_gallery_permition);
        } else {
            firePickPhotoIntent();
        }

    }

    private void setSelectedPhoto(Uri data) {
        mNewPhotoIV.setImageURI(data);
        mselectedPhotoURI = data;
    }


    private void firePickPhotoIntent() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_picture)), PICK_IMAGE);
    }

}