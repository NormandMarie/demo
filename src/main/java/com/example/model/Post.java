package com.example.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {
    private int id;
    private String title;
    private String author;
    private String content;

    private String createAt;

    private Category category;

    public Post(String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        String formattedDateTime = now.format(formatter);
        this.createAt = formattedDateTime;
    }
    public Post(int id,String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        String formattedDateTime = now.format(formatter);
        this.createAt = formattedDateTime;
    }

    public Post() {

    }

    public Post(String title, String author, String content, Category category) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.category = category;
    }

    public Post(int id, String title, String author, String content, Category category) {
        this.id =id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.category = category;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateAt() {
        return createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
