package com.lingc.readrecord;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ReadBookActivity extends AppCompatActivity {

    private List<ReadBook> bookList = new ArrayList<>();

   // private LinearLayout noany_ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.Readbook_Toolbar);
        ListView listView = (ListView) findViewById(R.id.ReadBook_ListView);
        LinearLayout noany_ly = (LinearLayout) findViewById(R.id.Readbook_Noany);
        ReadBookAdapter adapter = new ReadBookAdapter(ReadBookActivity.this, R.layout.readbook_list_item, bookList);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //载入ListView
        List<ReadBook> books = DataSupport.findAll(ReadBook.class);
        if (!books.toString().equals("[]")) {
            noany_ly.setVisibility(View.GONE);
            for (ReadBook book : books) {
                bookList.add(book);
                Log.d("ReadBook","数据存在");
            }
        } else {
            //listView.setVisibility(View.GONE);
            Log.d("ReadBook","没有数据");
            Log.d("ReadBook",books + "");
        }
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
