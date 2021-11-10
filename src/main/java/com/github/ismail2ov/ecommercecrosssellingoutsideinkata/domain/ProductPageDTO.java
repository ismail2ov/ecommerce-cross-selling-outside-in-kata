package com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain;

import lombok.Value;

import java.util.List;

@Value
public class ProductPageDTO {
    Product product;

    List<Product> crossSelling;
}
