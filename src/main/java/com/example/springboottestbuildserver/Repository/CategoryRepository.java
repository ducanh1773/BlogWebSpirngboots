package com.example.springboottestbuildserver.Repository;

import com.example.springboottestbuildserver.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Long> {
}
