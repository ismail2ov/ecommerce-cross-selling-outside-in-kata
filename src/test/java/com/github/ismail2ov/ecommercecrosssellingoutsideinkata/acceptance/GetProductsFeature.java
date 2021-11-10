package com.github.ismail2ov.ecommercecrosssellingoutsideinkata.acceptance;

import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.Product;
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
public class GetProductsFeature {

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
    void should_return_empty_list_when_we_don_not_have_any_product() {
        ResponseEntity<Product[]> result = testRestTemplate.getForEntity("/api/products", Product[].class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).hasSize(0);
    }

    @Test
    void should_return_list_with_products_when_we_have_products() {
        productRepository.save(new Product("Dell Latitude 3301 Intel Core i7-8565U/8GB/512GB SSD/13.3", "999,00 €"));
        productRepository.save(new Product("Samsonite Airglow Laptop Sleeve 13.3", "41,34 €"));
        productRepository.save(new Product("Logitech Wireless Mouse M185", "10,78 €"));
        productRepository.save(new Product("Fellowes Mouse Pad Black", "1,34 €"));

        ResponseEntity<Product[]> result = testRestTemplate.getForEntity("/api/products", Product[].class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result).isNotNull();
        assertThat(result.getBody()).hasSize(4);
    }
}
