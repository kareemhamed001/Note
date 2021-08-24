package com.barmej.notesapp.Activities;

import static com.barmej.notesapp.Constants.details_result;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.barmej.notesapp.Adapter.DataAdapter;
import com.barmej.notesapp.Listeners.ItemOnClickListener;
import com.barmej.notesapp.Listeners.ItemOnLongClick;
import com.barmej.notesapp.Constants;
import com.barmej.notesapp.Data.DataFather;
import com.barmej.notesapp.Data.PhotoText;
import com.barmej.notesapp.Data.TextCheck;
import com.barmej.notesapp.Data.TextDetails;
import com.barmej.notesapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButtonAdd;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mlinear;
    private RecyclerView.LayoutManager mgrid;
    private androidx.recyclerview.widget.StaggeredGridLayoutManager mstaggered;
    private ArrayList<DataFather> mItems;
    private DataAdapter mAdapter;
    int position1;
    int position2;
    int ADD_PHOTO = 123;
    private Menu mmenu;
    int ACTIVITY_CODE;
    final static int EDIT_PHOTO_NOTE = 301;
    Drawable color_code;
    Drawable color_code1;
    Drawable color_code2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view_photos);
        mItems = new ArrayList<DataFather>();

        mAdapter = new DataAdapter(mItems, new ItemOnLongClick() {
            @Override
            public void onItemClick(int position) {
                position1 = position;
                deleteItem(position);

            }
        }, new ItemOnClickListener() {
            @Override
            public void onItemClicked(int position) {
                position2 = position;
                openNote(position);
            }
        });

        mlinear = new LinearLayoutManager(this);
        mgrid = new GridLayoutManager(this, 2);
        mstaggered = new StaggeredGridLayoutManager(2, 1);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(mstaggered);
        floatingActionButtonAdd = findViewById(R.id.floating_button_add);
        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewNoteActivity1.class);
                startActivityForResult(intent, Constants.ADD_PHOTO);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == Constants.ADD_PHOTO) {
            if (resultCode == Constants.ADD_CHEKABLE) {
                String newText = data.getStringExtra(Constants.extra_text_check);
                boolean mNewCheck = data.getBooleanExtra(Constants.extra_bolean_check, false);
                int notestatue =
                        data.getIntExtra(Constants.note_color, R.drawable.checked_circle_blue);
                Drawable drawable = getDrawable(notestatue);
                color_code1 = drawable;
                TextCheck textCheck = new TextCheck(newText, mNewCheck, drawable);
                addItemCheck(textCheck);
                ACTIVITY_CODE = Constants.ADD_CHEKABLE;

            } else if (data != null && resultCode == RESULT_OK) {
                Uri pictureUri = data.getParcelableExtra(Constants.extra_photo_uri);
                String textDetails = data.getStringExtra(Constants.extra_text);
                int notestatue =
                        data.getIntExtra(Constants.note_color, R.drawable.checked_circle_blue);
                Drawable drawable = getDrawable(notestatue);
                color_code = drawable;
                PhotoText photoText = new PhotoText(pictureUri, textDetails, drawable);
                addItem(photoText);
                ACTIVITY_CODE = 5;
            } else if (resultCode == Constants.ADD_DETAILS) {
                ACTIVITY_CODE = Constants.ADD_DETAILS;
                String mnewtxt = data.getStringExtra(Constants.extra_text_details);
                int notestatue =
                        data.getIntExtra(Constants.note_color, R.drawable.checked_circle_blue);
                Drawable drawable = getDrawable(notestatue);
                color_code2 = drawable;
                TextDetails mnewtx = new TextDetails(mnewtxt, drawable);
                addItemDetails(mnewtx);

            }


        } else if (resultCode == Constants.photo_activity_result) {
            Uri pictureUri1 = data.getParcelableExtra(Constants.photo_result);
            String textDetails1 = data.getStringExtra(Constants.photo_text_result);

            PhotoText photoText = new PhotoText(pictureUri1, textDetails1, color_code);
            replaceItemPhoto(photoText);
        } else if (resultCode == Constants.check_result) {
            String textDetails1 = data.getStringExtra(Constants.check_edit_result);
            Boolean check = data.getBooleanExtra(Constants.check_check_result, false);

            TextCheck textCheck = new TextCheck(textDetails1, check, color_code1);
            replaceItemCheck(textCheck);
        } else if (resultCode == details_result) {
            String textDetails1 = data.getStringExtra(Constants.check_edit_result);

            TextDetails textDetails = new TextDetails(textDetails1, color_code2);
            replaceItemDetails(textDetails);
        }
    }

    private void addItem(PhotoText photoText) {
        mItems.add(photoText);
        mAdapter.notifyItemInserted(mItems.size() - 1);
    }

    private void replaceItemPhoto(PhotoText photoText) {
        mItems.add(photoText);
        mAdapter.notifyItemInserted(mItems.size());
        DeleteAfterAdd(position2);
    }

    private void replaceItemCheck(TextCheck textCheck) {
        mItems.add(textCheck);
        mAdapter.notifyItemInserted(mItems.size());
        DeleteAfterAdd(position2);
    }

    private void replaceItemDetails(TextDetails textDetails) {
        mItems.add(textDetails);
        mAdapter.notifyItemInserted(mItems.size());
        DeleteAfterAdd(position2);
    }

    private void DeleteAfterAdd(int position) {
        mItems.remove(position);
        mAdapter.notifyItemRemoved(position);
        position2 = position;
    }

    private void addItemCheck(TextCheck textCheck) {
        mItems.add(textCheck);
        mAdapter.notifyItemInserted(mItems.size() - 1);
    }

    public void addItemDetails(TextDetails textDetails) {
        mItems.add(textDetails);
        mAdapter.notifyItemInserted(mItems.size() - 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.mmenu = menu;
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actoin_grid) {
            recyclerView.setLayoutManager(mgrid);


            return true;
        } else if (item.getItemId() == R.id.actoin_list) {
            recyclerView.setLayoutManager(mlinear);

            return true;
        } else if (item.getItemId() == R.id.actoin_staggered) {
            recyclerView.setLayoutManager(mstaggered);
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteItem(final int position) {
        AlertDialog alertDialog =
                new AlertDialog.Builder(this).setMessage(R.string.delete_item_confirmation).setPositiveButton(R.string.Confrim_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mItems.remove(position);
                        mAdapter.notifyItemRemoved(position + 1);
                    }
                }).setNegativeButton(R.string.cancel_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        alertDialog.show();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("position", position2);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int position = savedInstanceState.getInt("position");
        position1 = position;
    }

    public void openNote(int position) {
        DataFather dataFather = mItems.get(position);
        switch (ACTIVITY_CODE) {

            case 5:
                Intent intent = new Intent(MainActivity.this, photoDetails.class);
                intent.putExtra(Constants.photo_edit, dataFather.getImageView());
                intent.putExtra(Constants.photo_text_edit, dataFather.getEditText());
                startActivityForResult(intent, Constants.photo_activity_result);
                break;
            case Constants.ADD_CHEKABLE:
                Intent intent1 = new Intent(MainActivity.this, CheckDetailsActivity.class);
                intent1.putExtra(Constants.check_text_edit, dataFather.getEditText());
                intent1.putExtra(Constants.check_check_edit, dataFather.getCheckBox());
                startActivityForResult(intent1, Constants.check_result);
                break;
            case Constants.ADD_DETAILS:
                Intent intent2 = new Intent(MainActivity.this, TextDetailsActivity.class);
                intent2.putExtra(Constants.details_note_edit, dataFather.getEditText());
                startActivityForResult(intent2, details_result);
                break;
        }
    }

}
