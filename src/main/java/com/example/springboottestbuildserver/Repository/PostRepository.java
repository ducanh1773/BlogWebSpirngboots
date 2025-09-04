package com.example.springboottestbuildserver.Repository;

import com.example.springboottestbuildserver.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
