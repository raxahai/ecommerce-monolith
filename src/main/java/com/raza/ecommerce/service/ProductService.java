package com.raza.ecommerce.service;

import com.raza.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> fetchAll();
    Product fetchById(Long id);
}
