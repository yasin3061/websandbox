package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.Optional;

public interface ProductService {

    Optional<Product> findProductById(Long id);

    Optional<Product> findProductByName(String name);

}
