package com.example.springboottestbuildserver.Cọntroller;

import com.example.springboottestbuildserver.Model.Category;
import com.example.springboottestbuildserver.Service.CategoryService;
import com.example.springboottestbuildserver.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Lấy tất cả danh mục
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    // Lấy danh mục theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Tạo một danh sách các danh mục mới
    @PostMapping("/create")
    public ResponseEntity<List<Category>> createCategories(@RequestBody List<Category> categories) {
        List<Category> createdCategories = categoryService.create(categories);
        return new ResponseEntity<>(createdCategories, HttpStatus.CREATED);
    }

    // Cập nhật một danh sách các danh mục
    @PutMapping("/update")
    public ResponseEntity<List<Category>> updateCategories(@RequestBody List<Category> categories) {
        List<Category> updatedCategories = categoryService.update(categories);
        return ResponseEntity.ok(updatedCategories);
    }

    // Xóa một danh mục
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
