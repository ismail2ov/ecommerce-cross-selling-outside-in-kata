package com.github.ismail2ov.ecommercecrosssellingoutsideinkata.acceptance;

import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.ProductPageDTO;
import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetProductByIdFeature {

    static final PostgreSQLContainer postgreSQLContainer;

    static {
        postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:14")
                .withUsername("james")
                .withPassword("bond")
                .withDatabaseName("ecommerce")
                .withReuse(true);

        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }

    @Autowired
    public TestRestTemplate testRestTemplate;

    @Autowired
    ProductRepository productRepository;

    @Test
    void should_return_not_found_if_the_product_does_not_exists() {
        ResponseEntity<ProductPageDTO> result = testRestTemplate.getForEntity("/api/products/1", ProductPageDTO.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
