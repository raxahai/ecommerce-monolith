package com.raza.ecommerce.service.impl;

import com.raza.ecommerce.entity.Product;
import com.raza.ecommerce.repository.ProductRepository;
import com.raza.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> fetchAll() {
        return productRepository.findAll();
    }
}
