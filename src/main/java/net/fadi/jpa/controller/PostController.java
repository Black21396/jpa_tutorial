package net.fadi.jpa.controller;


import net.fadi.jpa.entity.PostDTO;
import net.fadi.jpa.projection.EmployeeProjection;
import net.fadi.jpa.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping
    public List<PostDTO> getAll(){
        return postService.getPosts();
    }
    @GetMapping("/{id}")
    public PostDTO getById(@PathVariable long id){
       return postService.getPost(id);
    }

    @PostMapping
    public PostDTO addEmployee(@RequestBody PostDTO PostDTO){
        return postService.addPost(PostDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editEmployee(@PathVariable long id,@RequestBody PostDTO postDTO){
        postService.putPost(id, postDTO);
        return ResponseEntity.ok("successfully updated");
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteEmployee(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }
}
