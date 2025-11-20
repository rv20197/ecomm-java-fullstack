package com.vatsalrajgor.eCommerce.service.Category;

import com.vatsalrajgor.eCommerce.model.Category;
import com.vatsalrajgor.eCommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceInterface{
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id: %s not found", categoryId)));
        categoryRepo.deleteById(category.getCategoryId());
        return "Category deleted successfully!";
    }

    @Override
    public Category updateCategory(Category category,  Long categoryId) {
        Category existingCategory = categoryRepo.findById(categoryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id: %s not found", categoryId)));
        existingCategory.setCategoryId(categoryId);
        existingCategory.setCategoryName(category.getCategoryName());
        return categoryRepo.save(existingCategory);

    }
}
