package com.lingc.readrecord;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private final static int SET_LISTVIEW = 0;

    private DrawerLayout main_drl;

    private LinearLayout main_noany_ly;

    private ListView main_listview;

    private List<Bookdata> bookList = new ArrayList<>();

    private MainAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == SET_LISTVIEW) {
                //adapter.clear();
                main_listview.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar main_toolbar = (Toolbar) findViewById(R.id.Main_Toolbar);
        NavigationView main_nav = (NavigationView) findViewById(R.id.Main_Nav);
        FloatingActionButton floating_add_btn = (FloatingActionButton) findViewById(R.id.Floating_Add_Btn);
        main_listview = (ListView) findViewById(R.id.Main_ListView);
        main_noany_ly = (LinearLayout) findViewById(R.id.Main_Noany_Ly);
        main_drl = (DrawerLayout) findViewById(R.id.Main_DrL);

        adapter = new MainAdapter(MainActivity.this, R.layout.main_listview_item, bookList);
        main_listview.setAdapter(adapter);

        //ListView点击事件
        main_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Bookdata book = bookList.get(position);
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                intent.putExtra("name", book.getName());
                intent.putExtra("pages", book.getPages() + "");
                intent.putExtra("readpage", book.getReadpage() + "");
                intent.putExtra("image", book.getImage());
                intent.putExtra("author", book.getAuthor());
                startActivity(intent);
            }
        });


        /*判断是否第一次使用
        IOUitl ioUitl = new IOUitl();
        if (ioUitl.load().equals("")) {
            Log.d("MainAcitivity","第一次使用");
        }*/
        LitePal.getDatabase();

        setSupportActionBar(main_toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.baseline_list_24);
        }

        main_nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //侧滑栏事件
                switch (menuItem.getItemId()) {
                    case R.id.nav_add: {
                        Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.nav_book: {
                        Intent intent = new Intent(MainActivity.this, ReadBookActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.nav_about: {
                        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
                return true;
            }
        });

        floating_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                main_drl.openDrawer(GravityCompat.START);
                break;
            }
            case R.id.toolbal_seach: {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.toolbar_about: {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            }
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        List<Bookdata> books = DataSupport.findAll(Bookdata.class);
        if (!books.toString().equals("[]")) {
            Log.d("MainActivity","数据存在");
            main_noany_ly.setVisibility(View.GONE);
            for (Bookdata bookdata : books) {
                bookList.add(bookdata);
            }
        }
        //adapter.clear();
        main_listview.setAdapter(adapter);
    }

    //已弃用的方法
    /*private void setListView(Bookdata bookdata) {
        Log.d("MainActivity", "====setListView====");
        bookList.add(bookdata);
        Message message = new Message();
        message.what = SET_LISTVIEW;
        handler.sendMessage(message);
    }*/

}
