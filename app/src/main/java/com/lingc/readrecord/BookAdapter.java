package com.lingc.readrecord;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private int resourceId;

    public BookAdapter(Context context, int ViewResourId, List<Book> books) {
        super(context, ViewResourId, books);
        resourceId = ViewResourId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Book book = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.ShowLv_Item_Image);
        TextView title_text = (TextView) view.findViewById(R.id.ShowLv_Item_Title);
        TextView author_text = (TextView) view.findViewById(R.id.ShowLv_Item_Author);
        TextView sub_text = (TextView) view.findViewById(R.id.ShowLv_Item_Sub);
        Glide.with(view)
                .load(book.getImage())
                .into(imageView);
        title_text.setText(book.getTitle());
        author_text.setText("作者：" + book.getAuthor());
        sub_text.setText(book.getSummary());
        return view;
    }
}
