package com.lingc.readrecord;

import org.litepal.crud.DataSupport;

public class Book {
    //一个实体类，用于Adapter

    private int id;

    private String image;

    private String title;

    private String author;

    private String summary;

    private int pages;

    private int readpage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReadpage() {
        return readpage;
    }

    public void setReadpage(int readpage) {
        this.readpage = readpage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
