package com.vatsalrajgor.eCommerce.service.Category;

import com.vatsalrajgor.eCommerce.exceptions.APIException;
import com.vatsalrajgor.eCommerce.exceptions.ResourceNotFoundException;
import com.vatsalrajgor.eCommerce.model.Category;
import com.vatsalrajgor.eCommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService{
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category createCategory(Category category) {
        Category existingCategory = categoryRepo.findByCategoryName(category.getCategoryName());
        if (existingCategory != null) {
            throw new APIException(String.format("Category with name %s already exists", category.getCategoryName()));
        }
        return categoryRepo.save(category);
    }

    public String deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        categoryRepo.deleteById(category.getCategoryId());
        return "Category deleted successfully!";
    }

    public Category updateCategory(Category category,  Long categoryId) {
        Category existingCategory = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        existingCategory.setCategoryId(categoryId);
        existingCategory.setCategoryName(category.getCategoryName());
        return categoryRepo.save(existingCategory);
    }
}
