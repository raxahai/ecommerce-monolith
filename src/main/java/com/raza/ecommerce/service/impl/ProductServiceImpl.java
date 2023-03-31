package com.raza.ecommerce.service.impl;

import com.raza.ecommerce.dto.request.CreateProductDto;
import com.raza.ecommerce.entity.Product;
import com.raza.ecommerce.exception.ProductException;
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

    @Override
    public Product fetchById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductException("Product with ID not found"));
    }

    @Override
    public Product save(CreateProductDto createProductDto) {
        Product product = new Product();
        product.setName(createProductDto.getName());
        product.setDescription(createProductDto.getDescription());

        return productRepository.save(product);
    }
}
