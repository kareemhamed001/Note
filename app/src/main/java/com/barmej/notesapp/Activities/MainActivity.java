package com.barmej.notesapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.barmej.notesapp.Adapter.PhotoAdapter;
import com.barmej.notesapp.Constants;
import com.barmej.notesapp.Data.PhotoText;
import com.barmej.notesapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButtonAdd;
    private RecyclerView recyclerView;
    LinearLayout linear;
    Menu mmenu;
    int position;
    private RecyclerView.LayoutManager mlinear;
    private RecyclerView.LayoutManager mgrid;
    private ArrayList<PhotoText> mItems;
    private PhotoAdapter mAdapter;
    int ADD_PHOTO = 123;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor e=sharedPreferences.edit();
        e.putInt("key",position);
        e.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view_photos);
        mItems = new ArrayList<PhotoText>();
        mAdapter = new PhotoAdapter(mItems);
        mlinear = new LinearLayoutManager(this);
        mgrid = new GridLayoutManager(this, 2);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        position=sharedPreferences.getInt("key",0);
        recyclerView.scrollToPosition(position);

        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                position=layoutManager.findFirstVisibleItemPosition();
            }
        });
        linear=new LinearLayout(this);
        linear=findViewById(R.id.linear);
        floatingActionButtonAdd = findViewById(R.id.floating_button_add);
        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewNoteActivity1.class);
                startActivityForResult(intent, ADD_PHOTO);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PHOTO) {
            if (data != null && resultCode == RESULT_OK) {
                Uri pictureUri = data.getParcelableExtra(Constants.extra_photo_uri);
                String textDetails = data.getStringExtra(Constants.extra_text);

                PhotoText photoText = new PhotoText(pictureUri, textDetails);
                addItem(photoText);

            } else {
                Toast.makeText(this, "لم تقم باختيار صوره", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addItem(PhotoText photoText) {
        mItems.add(photoText);
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
            item.setVisible(false);
            mmenu.findItem(R.id.actoin_list).setVisible(true);

            return true;
        } else if (item.getItemId() == R.id.actoin_list) {
            recyclerView.setLayoutManager(mlinear);
            item.setVisible(false);
            mmenu.findItem(R.id.actoin_grid).setVisible(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
