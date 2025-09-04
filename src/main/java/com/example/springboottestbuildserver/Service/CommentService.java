package com.example.springboottestbuildserver.Service;


import com.example.springboottestbuildserver.Model.Comment;
import com.example.springboottestbuildserver.Model.Post;
import com.example.springboottestbuildserver.Model.Users;
import com.example.springboottestbuildserver.Repository.CommentRepository;
import com.example.springboottestbuildserver.Repository.PostRepository;
import com.example.springboottestbuildserver.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    // Lấy tất cả bình luận
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    // Lấy bình luận theo ID
    public Optional<Comment> findById(Long id) {
        if (id == null){
            throw new RuntimeException("cannot find comment");
        }
            return commentRepository.findById(id);
    }

    // Lấy bình luận theo postId
    public List<Comment> findByPostId(Long postId) {
        if(postId.equals(null)){
            throw new RuntimeException("cannot find comment");
        }
        return commentRepository.findByPostId(postId);
    }

    // Lưu một danh sách các bình luận mới
    @Transactional
    public List<Comment> create(List<Comment> comments) {
        for (Comment comment : comments) {
            Optional<Users> users = userRepository.findById(comment.getUserId());
            Optional<Post> post = postRepository.findById(comment.getPostId());
            if (users.isEmpty() || post.isEmpty() || comment.getContent().isEmpty()) {
                throw new RuntimeException("Cannot create comment");
            }
        }
        return commentRepository.saveAll(comments);
    }

    // Cập nhật một danh sách các bình luận
    @Transactional
    public List<Comment> update(List<Comment> comments) {
        for (Comment updatedComment : comments) {
            Optional<Users> users = userRepository.findById(updatedComment.getUserId());
            Optional<Post> post = postRepository.findById(updatedComment.getPostId());
            if (users.isEmpty() || post.isEmpty() || updatedComment.getContent().isEmpty()) {
                throw new RuntimeException("Cannot create comment");
            }
            Comment existingComment = commentRepository.findById(updatedComment.getId())
                    .orElseThrow(() -> new RuntimeException("Comment with ID " + updatedComment.getId() + " not found"));

            existingComment.setContent(updatedComment.getContent());
            existingComment.setPostId(updatedComment.getPostId());
            existingComment.setUserId(updatedComment.getUserId());
            // createdAt không cần cập nhật
        }
        return commentRepository.saveAll(comments);
    }

    // Xóa một bình luận theo ID
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
