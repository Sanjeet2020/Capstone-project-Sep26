package com.sanj.projectcatalogueservice.controller;

import com.sanj.projectcatalogueservice.dto.ProductDto;
import com.sanj.projectcatalogueservice.model.Product;
import com.sanj.projectcatalogueservice.service.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

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
    public void deleteProduct(@PathVariable long id) {
        // Logic to delete a product by its ID
        boolean isDeleted = productService.deleteProduct(id);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto request) {
        // Logic to create a new product
        return ResponseEntity.ok(new Product());
    }
}
