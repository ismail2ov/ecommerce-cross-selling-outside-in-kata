package com.github.ismail2ov.ecommercecrosssellingoutsideinkata.application;

import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.Product;
import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
