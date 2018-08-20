package com.lingc.readrecord;

import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddBookActivity extends AppCompatActivity {

    private final static int SET_ADAPTER = 0;

    private EditText input_book_ed;

    private ListView show_book_lv;

    private List<Book> bookList;

    private BookAdapter adapter;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == SET_ADAPTER) {
                show_book_lv.setAdapter(adapter);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Button add_book_btn = (Button) findViewById(R.id.Add_Book_Btn);
        input_book_ed = (EditText) findViewById(R.id.Input_Book_Ed);
        show_book_lv = (ListView) findViewById(R.id.Show_Book_Lv);
        Toolbar add_book_toolbar = (Toolbar) findViewById(R.id.Add_Book_Toolbar);
        setSupportActionBar(add_book_toolbar);
        //inputEdit = input_book_ed.getText().toString();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        bookList = new ArrayList<>();
        adapter = new BookAdapter(this, R.layout.show_listview_item, bookList);
        show_book_lv.setAdapter(adapter);

        show_book_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final Book book = bookList.get(position);
                Snackbar.make(view, "你选择了" + book.getTitle(), Snackbar.LENGTH_LONG)
                        .setAction("添加", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                List<Bookdata> books = DataSupport.where("bookid == ?",book.getId()).find(Bookdata.class);
                                Log.d("AddBook",books.toString());

                                /*判断书籍是否已经添加
                                for (Bookdata bookdata : books) {
                                    Log.d("AddBook","For");
                                    if (book.getId() == bookdata.getId()) {
                                        Snackbar.make(v, "该书籍已添加", Snackbar.LENGTH_SHORT).show();
                                        return;
                                    }
                                }*/
                                if (books.toString() != "[]") {
                                    Snackbar.make(v, "添加失败，该书籍已存在", Snackbar.LENGTH_SHORT).show();
                                    return;
                                }

                                Bookdata bookdata = new Bookdata();
                                bookdata.setBookid(book.getId());
                                bookdata.setAuthor(book.getAuthor());
                                bookdata.setPages(book.getPages());
                                bookdata.setName(book.getTitle());
                                bookdata.setImage(book.getImage());
                                bookdata.setReadpage(0);
                                bookdata.save();
                                Snackbar.make(v, "添加成功", Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        show_book_lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_IDLE:
                        if (isListViewReachBottomEdge(view)) {
                            Snackbar.make(view,"到底啦",Snackbar.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) { }
        });


        add_book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断输入框是否为空
                if (input_book_ed.getText().toString().equals("")) {
                    Snackbar.make(v, "请输入书名", Snackbar.LENGTH_SHORT).show();
                } else {
                    //清空操作
                    //bookList = null;
                    adapter.clear();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                OkHttpClient client = new OkHttpClient();
                                Request request = new Request.Builder()
                                        .url("https://api.douban.com/v2/book/search?q=" + input_book_ed.getText())
                                        .build();
                                Log.d("AddBookActivity", "https://api.douban.com/v2/book/search?q=" + input_book_ed.getText() + "&count=6");
                                Response response = client.newCall(request).execute();
                                String responseData = response.body().string();
                                parseJSONForBooks(responseData);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
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


    private void parseJSONForBooks(String jsonData) {
        Log.d("AddBook", "====parseJSONForBooks====");
        //Gson gson = new Gson();

        /*ArrayList<Book> list = new ArrayList<Book>();
        Type listType = new TypeToken<List<Book>>() {}.getType();
        list = gson.fromJson(jsonData, listType);*/

        /*List<Book> bookList = gson.fromJson(jsonData, new TypeToken<List<Book>>(){}.getType());
        for (Book book : bookList) {
            Log.d("AddBook", "Name:" + book.getTitle());
        }*/

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String books = jsonObject.getString("books");

            //Object object = jsonObject.getJSONObject("books");

            JSONArray jsonArray = new JSONArray(books);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                if (jsonObject1 != null) {
                    String author = jsonObject1.optString("author");

                    String images = jsonObject1.optString("images");
                    JSONObject jsonObject2 = new JSONObject(images);
                    String image = jsonObject2.getString("medium");

                    String id = jsonObject1.optString("id");
                    String title = jsonObject1.optString("title");
                    int pages = jsonObject1.optInt("pages");
                    String summary = jsonObject1.optString("summary");
                    //子线程不能直接操作UI
                    setListView(id, title, author, image, summary, pages);
                }
            }
            Log.d("AddBook","====Over====");

            //String auther = jsonObject.getString("auther");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void setListView(String id, String title, String author, String image, String summary, int pages) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setImage(image);
        book.setPages(pages);
        book.setSummary(summary);
        bookList.add(book);
        Message message = new Message();
        message.what = SET_ADAPTER;
        handler.sendMessage(message);
    }

    //判断是否到达底部
    public boolean isListViewReachBottomEdge(final AbsListView listView) {
        boolean result = false;
        if (listView.getLastVisiblePosition() == (listView.getCount() - 1)) {
            final View bottomChildView = listView.getChildAt(listView.getLastVisiblePosition() - listView.getFirstVisiblePosition());
            result = (listView.getHeight() >= bottomChildView.getBottom());
        };
        return result;
    }

}
