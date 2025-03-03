package com.learnwithiftekhar.rbacDemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    @PreAuthorize("hasAuthority('product:read')")
    public ResponseEntity<?> getAllProducts() {
        // Implementation would go here
        return ResponseEntity.ok("List of all products - requires 'product:read' permission");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('product:read')")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        // Implementation would go here
        return ResponseEntity.ok("Product details for ID: " + id + " - Requires 'product:read' permission");
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('product:write')")
    public ResponseEntity<?> addProduct(@RequestBody Object productDto) {
        // Implementation would go here
        return ResponseEntity.ok("Product added successfully - Requires 'product:write' permission");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('product:write')")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Object productDto) {
        // Implementation would go here
        return ResponseEntity.ok("Product updated successfully - Requires 'product:write' permission");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('product:delete')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        // Implementation would go here
        return ResponseEntity.ok("Product deleted successfully - Requires 'product:delete' permission");
    }
}
