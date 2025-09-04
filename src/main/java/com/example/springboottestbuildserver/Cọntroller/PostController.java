package com.example.springboottestbuildserver.Cọntroller;

import com.example.springboottestbuildserver.Model.Post;
import com.example.springboottestbuildserver.Service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/post")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public ResponseEntity<List<Post>> getAllPost() {
        return ResponseEntity.ok(postService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<List<Post>> createPosts(@RequestBody List<Post> posts) {
        List<Post> createdPosts = postService.create(posts);
        return new ResponseEntity<>(createdPosts, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<List<Post>> updatePosts(@RequestBody List<Post> posts) {
        List<Post> updatedPosts = postService.update(posts);
        return new ResponseEntity<>(updatedPosts, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
