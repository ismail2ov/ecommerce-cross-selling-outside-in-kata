package com.github.ismail2ov.ecommercecrosssellingoutsideinkata.application;

import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.Product;
import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.ProductNotFoundException;
import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.ProductPageDTO;
import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductPageDTO getProductBy(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        return new ProductPageDTO(product, Collections.emptyList());
    }
}
