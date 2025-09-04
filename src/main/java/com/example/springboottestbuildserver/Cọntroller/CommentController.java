package com.example.springboottestbuildserver.Cọntroller;

import com.example.springboottestbuildserver.Model.Comment;
import com.example.springboottestbuildserver.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.findAll();
        return ResponseEntity.ok(comments);
    }

    // Lấy bình luận theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return commentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Lấy bình luận theo postId
    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.findByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    // Tạo một danh sách các bình luận mới
    @PostMapping("/create")
    public ResponseEntity<List<Comment>> createComments(@RequestBody List<Comment> comments) {
        List<Comment> createdComments = commentService.create(comments);
        return new ResponseEntity<>(createdComments, HttpStatus.CREATED);
    }

    // Cập nhật một danh sách các bình luận
    @PutMapping("/update")
    public ResponseEntity<List<Comment>> updateComments(@RequestBody List<Comment> comments) {
        List<Comment> updatedComments = commentService.update(comments);
        return ResponseEntity.ok(updatedComments);
    }

    // Xóa một bình luận
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
