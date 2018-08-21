package com.lingc.readrecord;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.Book_Toolbar);
        TextView book_name_text = (TextView) findViewById(R.id.Book_Name_Text);
        TextView book_page_text = (TextView) findViewById(R.id.Book_Page_Text);
        final EditText book_readpage_edit = (EditText) findViewById(R.id.Book_Readpage_Edit);
        FloatingActionButton book_done_btn = (FloatingActionButton) findViewById(R.id.Floating_Done_Btn);
        Button book_delete_btn = (Button) findViewById(R.id.Book_Delete_Btn);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //获取Intent参数
        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String pages = intent.getStringExtra("pages");
        String readpage = intent.getStringExtra("readpage");
        final String image = intent.getStringExtra("image");
        final String author = intent.getStringExtra("author");
        book_name_text.setText(name);
        book_page_text.setText(pages + "/");
        book_readpage_edit.setText(readpage);

        final int intpage = Integer.parseInt(pages);

       book_delete_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //删除数据库数据
               Snackbar.make(v, "你确定要删除" + name + "吗？", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       DataSupport.deleteAll(Bookdata.class, "name == ?", name);
                       finish();
                   }
               }).show();
           }
       });

        book_done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (book_readpage_edit.getText().toString().equals("")) {
                    Snackbar.make(v, "请完整输入已读页数", Snackbar.LENGTH_SHORT).show();
                } else if (Integer.parseInt(book_readpage_edit.getText().toString()) >= intpage) {
                    Snackbar.make(v, "你确定要完成该书籍吗？", Snackbar.LENGTH_SHORT)
                            .setAction("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //将数据添加到ReadBook数据库
                                    ReadBook book = new ReadBook();
                                    book.setTitle(name);
                                    book.setAuthor(author);
                                    book.setImage(image);
                                    book.save();

                                    //删除Bookdata数据库中的数据
                                    DataSupport.deleteAll(Bookdata.class, "name == ?", name);
                                    Toast.makeText(BookActivity.this, "恭喜，该书籍已完成", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            })
                            .show();
                } else {
                    //数据库更新
                    Bookdata bookdata = new Bookdata();
                    String readpageStr = book_readpage_edit.getText().toString();
                    int readpage = Integer.parseInt(readpageStr);
                    bookdata.setReadpage(readpage);
                    bookdata.updateAll("name == ?", name);
                    //bookdata.updateAll();
                    //bookdata.update(intid);
                    finish();
                }
            }
        });


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
