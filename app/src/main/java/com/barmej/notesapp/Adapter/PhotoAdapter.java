package com.barmej.notesapp.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barmej.notesapp.Data.PhotoText;
import com.barmej.notesapp.R;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.photoAdapterViewHolder> {
    private ArrayList<PhotoText>photoTexts;

    public PhotoAdapter(ArrayList<PhotoText> photoTexts) {
        this.photoTexts = photoTexts;
    }

    @NonNull
    @Override
    public photoAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.item_note_photo,parent,false);
        photoAdapterViewHolder photoAdapterViewHolder=new photoAdapterViewHolder(view);
        return  photoAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull photoAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {

        PhotoText photoText=photoTexts.get(position);
        holder.imageView.setImageURI(photoText.getImageView());
        holder.textView.setText(photoText.getEditText());
        holder.position=position;

    }

    @Override
    public int getItemCount() {
        return photoTexts.size();
    }

    final class photoAdapterViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        int position;
        public photoAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
        }
    }
}
