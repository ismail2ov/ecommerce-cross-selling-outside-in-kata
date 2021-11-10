package com.github.ismail2ov.ecommercecrosssellingoutsideinkata.infrastructure;

import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.application.ProductService;
import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }
}
