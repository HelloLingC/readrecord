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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends ArrayAdapter<Bookdata> {

    private int resourceId;


    public MainAdapter(Context context, int ViewResourId, List<Bookdata> book) {
        super(context, ViewResourId, book);
        resourceId = ViewResourId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Bookdata book = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView main_item_image = (ImageView) view.findViewById(R.id.Main_Item_Image);
        TextView main_item_name = (TextView) view.findViewById(R.id.Main_Item_Name);
        TextView main_item_author = (TextView) view.findViewById(R.id.Main_Item_Author);
        TextView main_item_progress = (TextView) view.findViewById(R.id.Main_Item_Progress);
        ProgressBar main_item_pbar = (ProgressBar) view.findViewById(R.id.Main_Item_PBar);
        Glide.with(view)
                .load(book.getImage())
                .into(main_item_image);
        main_item_name.setText(book.getName());
        main_item_author.setText("作者：" + book.getAuthor());
        double a = book.getReadpage();
        double b = book.getPages();
        double end = a / b * 100;
        int progress = (int) end;
        Log.d("Progress","Progress:" + progress + ";Read:" + book.getReadpage() + ";Pages:" + book.getPages());
        main_item_pbar.setProgress(progress);
        main_item_progress.setText("进度：" + book.getPages() + "/" + book.getReadpage() + " (" + progress + "%)");
        return view;
    }
}
