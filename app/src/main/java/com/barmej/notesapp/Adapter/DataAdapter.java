
package com.barmej.notesapp.Adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.barmej.notesapp.Data.DataFather;
import com.barmej.notesapp.Data.PhotoText;
import com.barmej.notesapp.Data.TextCheck;
import com.barmej.notesapp.Listeners.ItemOnClickListener;
import com.barmej.notesapp.Listeners.ItemOnLongClick;
import com.barmej.notesapp.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    static final int PHOTO_TEXT = 0;
    static final int TEXT_CHECK = 1;
    static final int TEXT_DETAILS = 2;

    private ItemOnLongClick mitemOnLongClick;
    private ItemOnClickListener mitemOnClickListener;
    private ArrayList<DataFather> dataFathers;

    public DataAdapter(ArrayList<DataFather> dataFathers, ItemOnLongClick mitemOnLongClick, ItemOnClickListener mitemOnClickListener) {
        this.dataFathers = dataFathers;
        this.mitemOnLongClick = mitemOnLongClick;
        this.mitemOnClickListener = mitemOnClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataFathers.get(position) instanceof PhotoText) {
            return PHOTO_TEXT;
        } else if (dataFathers.get(position) instanceof TextCheck) {
            return TEXT_CHECK;
        }
        return TEXT_DETAILS;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case TEXT_DETAILS:
                view = inflater.inflate(R.layout.item_note, parent, false);
                return new textDetailsViewHolder(view, mitemOnLongClick, mitemOnClickListener);
            case TEXT_CHECK:
                view = inflater.inflate(R.layout.item_note_check, parent, false);
                return new textCheckViewHolder(view, mitemOnLongClick, mitemOnClickListener);
            case PHOTO_TEXT:
                view = inflater.inflate(R.layout.item_note_photo, parent, false);
                return new photoAdapterViewHolder(view, mitemOnLongClick, mitemOnClickListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        switch (holder.getItemViewType()) {
            case PHOTO_TEXT:
                DataFather dataFather = dataFathers.get(position);
                photoAdapterViewHolder photoAdapterViewHolder = (photoAdapterViewHolder) holder;
                photoAdapterViewHolder.imageView.setImageURI(dataFather.getImageView());
                photoAdapterViewHolder.textView.setText(dataFather.getEditText());

                photoAdapterViewHolder.layout.setBackground(dataFather.getColor());

                photoAdapterViewHolder.position = position;
                break;
            case TEXT_CHECK:
                DataFather dataFather1 = dataFathers.get(position);
                DataAdapter.textCheckViewHolder textCheckViewHolder = (textCheckViewHolder) holder;
                textCheckViewHolder.textView.setText(dataFather1.getEditText());
                textCheckViewHolder.layout.setBackground(dataFather1.getColor());
                textCheckViewHolder.checkBox.setChecked(dataFather1.getCheckBox());
                textCheckViewHolder.position = position;
                break;
            case TEXT_DETAILS:
                DataFather dataFather2 = dataFathers.get(position);
                textDetailsViewHolder textDetailsViewHolder = (textDetailsViewHolder) holder;
                textDetailsViewHolder.textView.setText(dataFather2.getEditText());
                textDetailsViewHolder.layout.setBackground(dataFather2.getColor());
                textDetailsViewHolder.position = position;
                break;

        }

    }


    @Override
    public int getItemCount() {
        return dataFathers.size();
    }

    final class photoAdapterViewHolder extends RecyclerView.ViewHolder {


        LinearLayoutCompat layout;
        ImageView imageView;
        ImageView imageView2;
        TextView textView;
        int position;

        public photoAdapterViewHolder(@NonNull View itemView, final ItemOnLongClick itemOnLongClick, final ItemOnClickListener mitemOnClickListener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            imageView2 = itemView.findViewById(R.id.imageViewPhoto);
            layout=itemView.findViewById(R.id.linearPhoto);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mitemOnClickListener.onItemClicked(position);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    itemOnLongClick.onItemClick(position);
                    return true;
                }
            });
        }
    }

    final class textCheckViewHolder extends RecyclerView.ViewHolder {
        LinearLayoutCompat layout;
        TextView textView;
        CheckBox checkBox;
        ImageView imageView;
        int position;

        public textCheckViewHolder(@NonNull View itemView, final ItemOnLongClick itemOnLongClick, final ItemOnClickListener mitemOnClickListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewchck);
            checkBox = itemView.findViewById(R.id.checkBoxCheck);
            imageView = itemView.findViewById(R.id.statueCheck);
            layout=itemView.findViewById(R.id.linearcheck);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mitemOnClickListener.onItemClicked(position);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    itemOnLongClick.onItemClick(position);
                    return true;
                }
            });
        }
    }

    final class textDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayoutCompat layout;
        ImageView imageView1;
        int position;

        public textDetailsViewHolder(@NonNull View itemView, final ItemOnLongClick itemOnLongClick, final ItemOnClickListener mitemOnClickListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewchck);
            imageView1 = itemView.findViewById(R.id.imageViewDetails);
            layout=itemView.findViewById(R.id.lineardetails);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mitemOnClickListener.onItemClicked(position);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    itemOnLongClick.onItemClick(position);
                    return true;
                }
            });
        }
    }

}
