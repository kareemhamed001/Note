package com.barmej.notesapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.barmej.notesapp.Constants;
import com.barmej.notesapp.R;

public class AddNewNoteActivity1 extends AppCompatActivity {
    RadioGroup colorRadioGroub;
    CardView cardViewPhoto;
    CardView cardViewdetails;
    CardView cardViewcheck;
    RadioButton noteStatue;
    ImageView imageView;
    EditText photoNoteEditText;
    EditText checkNoteEditText;
    EditText textViewdetails;
    RadioButton radioButton;
    CheckBox checkNoteCheckBox;
    int ADD_PHOTO = 123;
    RadioGroup typeRadioGroub;
    Button submit;
    Uri mNewimage;
    int READ_PHOTO_FROM_GALLAREY = 180;
    int colorcode = R.drawable.rounded_edges_white;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        colorRadioGroub = findViewById(R.id.colorRadioGroup);
        typeRadioGroub = findViewById(R.id.typeRadioGroup);
        cardViewPhoto = findViewById(R.id.cardViewPhoto);
        cardViewdetails = findViewById(R.id.cardViewNote);
        cardViewcheck = findViewById(R.id.cardViewCheckNote);
        photoNoteEditText = findViewById(R.id.photoNoteEditText);
        imageView = findViewById(R.id.photoImageView);
        checkNoteEditText = findViewById(R.id.checkNoteEditText);
        checkNoteCheckBox = findViewById(R.id.checkNoteCheckBox);
        textViewdetails = findViewById(R.id.noteEditText);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPicture();
            }
        });
        submit = findViewById(R.id.button_submit);

        colorRadioGroub.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = findViewById(i);
                switch (radioButton.getId()) {
                    case R.id.radioButton:

                        colorcode = R.drawable.rounded_edgesyellow;
                        System.out.println(colorcode);
                        break;
                    case R.id.radioButton2:

                        colorcode = R.drawable.rounded_edges_red;
                        System.out.println(colorcode);
                        break;
                    case R.id.radioButton3:

                        colorcode = R.drawable.rounded_edges_blue;
                        System.out.println(colorcode);
                        break;
                }
            }
        });
        typeRadioGroub.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = findViewById(i);
                switch (radioButton.getId()) {
                    case R.id.radioButton4:

                        cardViewPhoto.setVisibility(CardView.VISIBLE);
                        cardViewcheck.setVisibility(CardView.INVISIBLE);
                        cardViewdetails.setVisibility(CardView.INVISIBLE);
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                submitBt();
                            }
                        });
                        break;
                    case R.id.radioButton5:

                        cardViewcheck.setVisibility(CardView.VISIBLE);
                        cardViewPhoto.setVisibility(CardView.INVISIBLE);
                        cardViewdetails.setVisibility(CardView.INVISIBLE);

                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                submitBt1();
                            }
                        });
                        break;
                    case R.id.radioButton6:
                        cardViewdetails.setVisibility(CardView.VISIBLE);
                        cardViewcheck.setVisibility(CardView.INVISIBLE);
                        cardViewPhoto.setVisibility(CardView.INVISIBLE);
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                submitBt2();
                            }
                        });
                        break;
                }
            }
        });
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


    public void submitBt() {
        String mNewtext = photoNoteEditText.getText().toString();

        if (mNewimage != null && !mNewtext.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra(Constants.extra_photo_uri, mNewimage);
            intent.putExtra(Constants.extra_text, mNewtext);

            intent.putExtra(Constants.note_color, colorcode);
            setResult(RESULT_OK, intent);
            finish();

        } else {
            Toast.makeText(this, R.string.select_picture, Toast.LENGTH_LONG).show();
        }
    }

    public void submitBt1() {
        String mNewtext = checkNoteEditText.getText().toString();
        Boolean mNewCheck = checkNoteCheckBox.isChecked();
        if (!mNewtext.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra(Constants.extra_text_check, mNewtext);
            intent.putExtra(Constants.extra_bolean_check, mNewCheck);
            intent.putExtra(Constants.note_color, colorcode);
            setResult(Constants.ADD_CHEKABLE, intent);
            finish();

        } else {
            Toast.makeText(this, "ادخل نص", Toast.LENGTH_LONG).show();
        }
    }

    public void submitBt2() {
        String mNewtext = textViewdetails.getText().toString();
        if (!mNewtext.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra(Constants.extra_text_details, mNewtext);
            intent.putExtra(Constants.note_color, colorcode);
            setResult(Constants.ADD_DETAILS, intent);
            finish();

        } else {
            Toast.makeText(this, "ادخل نص", Toast.LENGTH_LONG).show();
        }
    }

}
