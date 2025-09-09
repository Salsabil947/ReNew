package com.project.renew.controller;


import com.project.renew.model.Product;
import com.project.renew.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}

