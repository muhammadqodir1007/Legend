package com.example.legend.service;

import com.example.legend.entity.ApiResponse;
import com.example.legend.entity.Product;
import com.example.legend.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {


    ProductRepository productRepository;


    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(int id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.orElseThrow(NullPointerException::new);

    }

    public ApiResponse insert(Product product) {
        Product product1 = new Product();
        product1.setCost(product.getCost());
        product1.setName(product.getName());
        product1.setCategory(product.getCategory());
        product1.setCreatedAt(LocalDateTime.now());
        product1.setDescription(product.getDescription());
        product1.setQuantity(product.getQuantity());
        Product save = productRepository.save(product1);
        return new ApiResponse(true, "success", save);
    }


    public ApiResponse delete(int id) {
        productRepository.deleteById(id);
        return new ApiResponse(true, "success");
    }


}
