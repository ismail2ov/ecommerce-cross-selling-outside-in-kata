package com.github.ismail2ov.ecommercecrosssellingoutsideinkata.acceptance;

import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetProductsFeature {

    @Autowired
    public TestRestTemplate testRestTemplate;

    @Test
    void should_return_empty_list_when_we_don_nott_have_any_product() {
        ResponseEntity<Product[]> result = testRestTemplate.getForEntity("/api/products", Product[].class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
