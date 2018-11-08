package com.example.rkjc.news_app_2;

public class NewsItem {
    private String author;
    private String url;
    private String title;
    private String description;

    public NewsItem(String author, String title, String url, String description) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setName(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
