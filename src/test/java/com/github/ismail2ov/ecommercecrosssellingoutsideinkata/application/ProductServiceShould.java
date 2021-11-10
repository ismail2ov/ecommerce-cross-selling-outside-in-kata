package com.github.ismail2ov.ecommercecrosssellingoutsideinkata.application;

import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.Product;
import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceShould {

    @Test
    void return_empty_list_when_has_not_products() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<Product> actual = productService.getAllProducts();

        assertThat(actual).hasSize(0);
    }
}