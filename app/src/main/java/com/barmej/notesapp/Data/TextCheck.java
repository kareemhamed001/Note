package com.barmej.notesapp.Data;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class TextCheck {
    EditText editText;
    CheckBox checkBox;

    public TextCheck(EditText editText, CheckBox checkBox) {
        this.editText = editText;
        this.checkBox = checkBox;
    }

    public EditText getEditText() {
        return editText;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
