package com.example.rkjc.news_app_2;

public class NewsItem {
    private String author;
    private String url;
    private String title;
    private String description;
    private String image;
    private String publishDate;

    public NewsItem(String author, String title, String url, String description, String image, String publishDate) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.description = description;
        this.image = image;
        this.publishDate = publishDate;
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

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getPublishDate(){
        return publishDate;
    }

    public void setPublishDate(String publishDate){
        this.publishDate = publishDate;
    }
}
