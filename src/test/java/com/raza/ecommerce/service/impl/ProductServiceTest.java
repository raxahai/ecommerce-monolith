package com.raza.ecommerce.service.impl;

import com.raza.ecommerce.dto.request.CreateProductDto;
import com.raza.ecommerce.entity.Product;
import com.raza.ecommerce.exception.ProductException;
import com.raza.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository mockProductRepository;

    @InjectMocks
    private ProductServiceImpl productService;


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

    @Test
    void shouldFetch_ProductById() {
        Long productId = 1L;
        Product testProduct = new Product(1L, "test-product-1", "test description-1",
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        when(mockProductRepository.findById(productId)).thenReturn(Optional.of(testProduct));

        Product result = productService.fetchById(productId);

        assertEquals(result.getId(), testProduct.getId());
        assertEquals(result.getName(), testProduct.getName());
    }

    @Test()
    void shouldThrowException_FetchProductById() {
        assertThrows(ProductException.class, () -> productService.fetchById(1L));
    }

    @Test
    void givenProduct_thenReturnProduct_andSave() {
        CreateProductDto createProductDto = new CreateProductDto("test-product-1", "test description-1");
        Product testProduct = new Product();
        testProduct.setName("test-product-1");
        testProduct.setDescription("test description-1");

        when(mockProductRepository.save(any(Product.class))).thenReturn(testProduct);

        Product result = productService.save(createProductDto);

        assertEquals(result.getName(), testProduct.getName());
        assertEquals(result.getDescription(), testProduct.getDescription());
    }
}
