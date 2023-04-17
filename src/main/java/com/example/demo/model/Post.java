package com.example.demo.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {
    private long id;
    private String title;
    private String author;
    private String content;

    private String createAt;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
