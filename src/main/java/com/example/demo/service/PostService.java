package com.example.demo.service;
import com.example.demo.model.Post;
import  java.util.List;
public class PostService {
    public static List<Post> posts = List.of(

            new Post(1,"patate","moi","j'aime les patate"),
            new Post(2,"patate1","moi1","j'aime les patate1"),
            new Post(3,"patate2","moi2","j'aime les patate2")
    );

    public List<Post>  indexPosts(){
        return posts;
    }




}
