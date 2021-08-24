package com.barmej.notesapp.Data;

import android.graphics.drawable.Drawable;
import android.net.Uri;

public class DataFather {
    Uri imageView;
    String editText;
    Boolean checkBox;
    Drawable color;

    public DataFather(Uri imageView, String editText, Drawable color) {
        this.imageView = imageView;
        this.editText = editText;
        this.color = color;
    }

    public DataFather(String editText, Boolean checkBox, Drawable color) {
        this.editText = editText;
        this.checkBox = checkBox;
        this.color = color;
    }

    public DataFather(String editText, Drawable color) {
        this.editText = editText;
        this.color = color;
    }

    public Drawable getColor() {
        return color;
    }

    public Uri getImageView() {
        return imageView;
    }

    public String getEditText() {
        return editText;
    }

    public Boolean getCheckBox() {
        return checkBox;
    }
}
