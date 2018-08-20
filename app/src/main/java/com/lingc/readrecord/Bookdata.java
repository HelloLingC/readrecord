package com.lingc.readrecord;

import org.litepal.crud.DataSupport;

public class Bookdata extends DataSupport {
    //又一个实体类，用于数据库
    private int id;

    private String bookid;

    private String name;

    private String author;

    private String image;

    private int pages;

    private int readpage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getReadpage() {
        return readpage;
    }

    public void setReadpage(int readpage) {
        this.readpage = readpage;
    }

}
