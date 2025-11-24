package com.vatsalrajgor.eCommerce.controller;

import com.vatsalrajgor.eCommerce.DTO.Category.CategoryDTO;
import com.vatsalrajgor.eCommerce.DTO.Category.CategoryResponse;
import com.vatsalrajgor.eCommerce.model.Category;
import com.vatsalrajgor.eCommerce.service.Category.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber, @RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize){
        return new ResponseEntity<>(categoryService.getAllCategories(pageNumber,pageSize), HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO category){
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@Valid @PathVariable Long categoryId){
        return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.OK);
    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO category, @PathVariable Long categoryId){
        return new ResponseEntity<>(categoryService.updateCategory(category, categoryId), HttpStatus.OK);
    }
}
