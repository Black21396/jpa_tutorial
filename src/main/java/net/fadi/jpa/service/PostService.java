package net.fadi.jpa.service;

import jakarta.servlet.ServletRequestAttributeListener;
import net.fadi.jpa.entity.PostDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


/*
    * this class use "RestTemplate" to use another Web API project
    * URL of the another project: https://jsonplaceholder.typicode.com
    * we are using "PlaceHolder" Web API
 */
@Service
public class PostService {

     // define object to call the another Web API services
     private RestTemplate restTemplate = new RestTemplate();

     // define the base URL to calls services
     private static final String baseUrl = "https://jsonplaceholder.typicode.com/posts/";

     // get all posts
     public List<PostDTO> getPosts(){
        // getForEntity(string: url, Type of return value from service)
         ResponseEntity<List> posts = restTemplate.getForEntity(baseUrl,List.class);

         //return just the body(which contain data)
         return posts.getBody();
     }

     // get post by id
    public PostDTO getPost(long id){

        // getForEntity(string: url, Type of return value from service)
        ResponseEntity<PostDTO> posts = restTemplate.getForEntity(baseUrl+id,PostDTO.class);

        //return just the body(which contain data)
        return posts.getBody();
    }

    // add new post
    public PostDTO addPost(PostDTO post){
         // define the header of our request
         HttpHeaders headers = new HttpHeaders();
         headers.add("accept", "application/json");
         headers.add("accept-language", "en");

        // define the entity(request and data to post)
         HttpEntity<PostDTO> httpEntity = new HttpEntity<>(post, headers);

         // send the request
        ResponseEntity<PostDTO> newPost = restTemplate.postForEntity(baseUrl, headers, PostDTO.class);

        //return just the body(which contain data)
        return newPost.getBody();
    }

    // edit a post
    public void putPost(long id, PostDTO post){

         // get the post in database to edit it
        PostDTO oldPost = getPost(id);

        // update the post variables
        oldPost.setBody(post.getBody());
        oldPost.setTitle(post.getTitle());
        oldPost.setUserId(post.getUserId());

        // define the header of our request
         HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/json");
        headers.add("accept-language", "en");

        // define the entity(request and data to post)
        HttpEntity<PostDTO> httpEntity = new HttpEntity<>(oldPost,headers);

        // call the API service
        restTemplate.put(baseUrl, httpEntity,PostDTO.class);
    }

    // delete post
    public void deletePost(long id){
        // call the API service
         restTemplate.delete(baseUrl+id);
    }

}
