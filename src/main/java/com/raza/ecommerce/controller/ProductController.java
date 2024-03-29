package com.raza.ecommerce.controller;

import com.raza.ecommerce.dto.request.CreateProductDto;
import com.raza.ecommerce.entity.Product;
import com.raza.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/product")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    private List<Product> fetchAllProducts() {
        return productService.fetchAll();
    }

    @GetMapping("/{id}")
    private Product fetchAllProducts(@PathVariable("id") Long id) {
        return productService.fetchById(id);
    }

    @PostMapping("/save")
    private Product saveProduct(@RequestBody CreateProductDto createProductDto) {
        return productService.save(createProductDto);
    }
}
