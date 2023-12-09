package com.example.legend.controller;

import com.example.legend.entity.ApiResponse;
import com.example.legend.entity.Product;
import com.example.legend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {


    ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Product product) {

        ApiResponse insert = productService.insert(product);
        if (insert.isSuccess()) {
            ResponseEntity.status(HttpStatus.CREATED).body((Product) insert.getData());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");

    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable int id) {
        ApiResponse delete = productService.delete(id);
        if (delete.isSuccess()) return ResponseEntity.ok("deleted");
        return ResponseEntity.badRequest().body("not deleted");
    }


}
