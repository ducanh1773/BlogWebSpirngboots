package com.example.springboottestbuildserver.Service;

import com.example.springboottestbuildserver.Model.Category;
import com.example.springboottestbuildserver.Model.Post;
import com.example.springboottestbuildserver.Model.Users;
import com.example.springboottestbuildserver.Repository.CategoryRepository;
import com.example.springboottestbuildserver.Repository.PostRepository;
import com.example.springboottestbuildserver.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Transactional
    public List<Post> create(List<Post> posts) {
        // Gán thời gian tạo cho tất cả các đối tượng trong danh sách
        for (Post post : posts) {
            Optional<Users> users = userRepository.findById(post.getUserId());
            Optional<Category> category = categoryRepository.findById(post.getCategoryId());
            if (users.isEmpty() || category.isEmpty()) {
                throw new RuntimeException("Cannot find category or users");
            }
            post.setCreatedAt(LocalDateTime.now());
        }
        return postRepository.saveAll(posts); // Sử dụng saveAll để lưu danh sách
    }

    @Transactional
    public List<Post> update(List<Post> posts) {
        // Lặp qua danh sách để cập nhật từng bài viết
        posts.forEach(updatedPost -> {
            Optional<Post> existingPostOptional = postRepository.findById(updatedPost.getId());
            Optional<Users> users = userRepository.findById(updatedPost.getUserId());
            Optional<Category> category = categoryRepository.findById(updatedPost.getCategoryId());
            if (users.isEmpty() || category.isEmpty() || existingPostOptional.isEmpty()) {
                throw new RuntimeException("Cannot find category or users");
            }
            Post existingPost = existingPostOptional.get();
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());
            existingPost.setUserId(updatedPost.getUserId());
            existingPost.setCategoryId(existingPostOptional.get().getCategoryId());
            // Tùy chọn: ném ngoại lệ hoặc tạo bài viết mới nếu không tìm thấy
        });
        return postRepository.saveAll(posts);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
