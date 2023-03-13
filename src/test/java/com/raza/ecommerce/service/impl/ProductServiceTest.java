package com.raza.ecommerce.service.impl;

import com.raza.ecommerce.entity.Product;
import com.raza.ecommerce.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

import com.raza.ecommerce.service.ProductService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductServiceTest {
    @MockBean
    private ProductRepository mockProductRepository;

    @Autowired
    private ProductService productService;

    void setUp() {
        MockitoAnnotations.openMocks(ProductServiceTest.class);
    }

    final List<Product> testProducts = new ArrayList<>(Arrays.asList(
            new Product(1L, "test-product-1", "test description-1",
                    Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now())),
            new Product(2L, "test-product-2", "test description-2",
                    Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()))));

    @Test
    void shouldFetch_AllProducts() {
        when(mockProductRepository.findAll()).thenReturn(testProducts);
        List<Product> result = productService.fetchAll();
        assertEquals(testProducts, result);
    }
}
