package com.github.ismail2ov.ecommercecrosssellingoutsideinkata.acceptance;

import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.Product;
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

    @Test
    void should_return_empty_list_when_we_don_nott_have_any_product() {
        ResponseEntity<Product[]> result = testRestTemplate.getForEntity("/api/products", Product[].class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).hasSize(0);
    }
}
