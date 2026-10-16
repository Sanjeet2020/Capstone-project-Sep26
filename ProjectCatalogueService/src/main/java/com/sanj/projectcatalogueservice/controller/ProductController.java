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
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        // Logic to delete a product by its ID
        boolean isDeleted = productService.deleteProduct(id);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto request) {
        // Logic to create a new product
        Product createdProduct = productService.createProduct(request);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody ProductDto request) {
        // Logic to update an existing product
        Product updatedProduct = productService.updateProduct(id, request);
        return ResponseEntity.ok(updatedProduct);
    }
}
