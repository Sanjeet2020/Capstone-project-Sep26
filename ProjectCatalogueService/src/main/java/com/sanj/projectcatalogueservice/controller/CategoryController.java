package com.sanj.projectcatalogueservice.controller;

import com.sanj.projectcatalogueservice.model.Category;
import com.sanj.projectcatalogueservice.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return List.of();
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return new Category();
    }

    public void deleteCategory(int id) {
        // Logic to delete a category by its ID
    }

    @GetMapping("/{category}")
    public ResponseEntity<Double> getAvgPriceForCategory(@PathVariable String category) {
        Double avg = categoryService.getAvgPriceForCategory(category);
        return new ResponseEntity<>(avg, HttpStatus.OK);
    }
}
