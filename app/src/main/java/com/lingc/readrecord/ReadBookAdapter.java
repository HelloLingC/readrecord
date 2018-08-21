package com.lingc.readrecord;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class ReadBookAdapter extends ArrayAdapter<ReadBook> {

    private int resourceId;

    public ReadBookAdapter(Context context, int ViewResourId, List<ReadBook> books) {
        super(context, ViewResourId, books);
        resourceId = ViewResourId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ReadBook book = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.Readbook_Item_Image);
        TextView title_text = (TextView) view.findViewById(R.id.Readbook_Item_Title);
        TextView author_text = (TextView) view.findViewById(R.id.Readbook_Item_Author);
        title_text.setText(book.getTitle());
        author_text.setText(book.getAuthor());
        Glide.with(view)
                .load(book.getImage())
                .into(imageView);
        return view;
    }
}
