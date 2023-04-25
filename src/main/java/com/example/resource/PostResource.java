package com.example.resource;
import com.example.dao.PostDao;
import com.example.model.Post;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.io.StringReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import static com.example.service.DDBconnect.*;


@Path("/posts")
public class PostResource {

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Post> getAllPost() throws SQLException {

        List<Post> posts = PostDao.findAll();
        System.out.println(posts);
        return posts;
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post getPostById(@PathParam("id") int id) {
        List<Post> posts = PostDao.findAll();
        Post post = null;
        for (Post p : posts) {
            if (p.getId() == id) {
                post = p;
                break;
            }
        }
        return post;
    }
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePostweb(@PathParam("id") int id) {
        Post postToDelete = getPostById(id);
        if (postToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PostDao.deletePost(postToDelete.getId());
        return Response.status(Response.Status.NO_CONTENT).build();
    }
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createPostweb(Post post, Categories categoty) {
//        Post newPost = com.example.dao.postDao.createPostapi(post,categoty);
//
//        return Response.status(Response.Status.CREATED).entity(newPost).build();
//    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePost(@PathParam("id") int id, String data) {
        Post post = getPostById(id);
        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        // récupère les data que l'on a écrit
        JsonReader reader = Json.createReader(new StringReader(data));
        //lis les données data
        JsonObject jsonPost = reader.readObject();
        // ferme le reader c'est comme un scanner en faite
        reader.close();
        // prend la key "title" et vérifie si celle si est vide ou non pour l'update ou non
        if (jsonPost.containsKey("title")) {
            post.setTitle(jsonPost.getString("title"));
        }
        if (jsonPost.containsKey("author")) {
            post.setAuthor(jsonPost.getString("author"));
        }
        if (jsonPost.containsKey("content")) {
            post.setContent(jsonPost.getString("content"));
        }

        PostDao.updatePostApi(post);

        return Response.status(Response.Status.NO_CONTENT).entity(post).build();
    }
}

