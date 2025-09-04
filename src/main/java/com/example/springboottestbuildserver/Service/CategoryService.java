package com.example.springboottestbuildserver.Service;

import com.example.springboottestbuildserver.Model.Category;
import com.example.springboottestbuildserver.Repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Lấy tất cả danh mục
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // Lấy danh mục theo ID
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    // Lưu một danh sách các danh mục mới
    @Transactional
    public List<Category> create(List<Category> categories) {
        for (Category category : categories) {
            if (category.getName().isEmpty()) {
                throw new RuntimeException("Cannot create comment");
            }
        }
        // ID sẽ được tự động tạo bởi cơ sở dữ liệu khi bạn lưu
        return categoryRepository.saveAll(categories);
    }

    // Cập nhật một danh sách các danh mục
    @Transactional
    public List<Category> update(List<Category> categories) {
        for (Category updatedCategory : categories) {

            Category existingCategory = categoryRepository.findById(updatedCategory.getId())
                    .orElseThrow(() -> new RuntimeException("Category with ID " + updatedCategory.getId() + " not found"));
            if (updatedCategory.getName().isEmpty()) {
                throw new RuntimeException("Cannot create comment");
            }
            existingCategory.setName(updatedCategory.getName());
        }
        return categoryRepository.saveAll(categories);
    }

    // Xóa một danh mục theo ID
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
