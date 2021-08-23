package com.barmej.notesapp.Data;

import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoText {
    Uri imageView;
    String editText;

    public PhotoText(Uri imageView, String editText) {
        this.imageView = imageView;
        this.editText = editText;
    }

    public Uri getImageView() {
        return imageView;
    }

    public String getEditText() {
        return editText;
    }
}
