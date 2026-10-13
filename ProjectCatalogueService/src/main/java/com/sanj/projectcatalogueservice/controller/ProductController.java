package com.sanj.projectcatalogueservice.controller;

import com.sanj.projectcatalogueservice.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    //API to get all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return List.of();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(int id) {
        // Logic to retrieve a product by its ID
        return ResponseEntity.ok(new Product());
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(int id) {
        // Logic to delete a product by its ID
    }
}
