package com.github.ismail2ov.ecommercecrosssellingoutsideinkata.infrastructure;

import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.application.ProductService;
import com.github.ismail2ov.ecommercecrosssellingoutsideinkata.domain.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerShould {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Test
    void return_empty_list_when_do_not_have_any_product() throws Exception {
        when(productService.getAllProducts()).thenReturn(Collections.emptyList());

        this.mockMvc
                .perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));
    }

    @Test
    void return_not_found_if_the_product_does_not_exists() throws Exception {
        when(productService.getProductBy(1L)).thenThrow(ProductNotFoundException.class);

        this.mockMvc
                .perform(get("/api/products/1"))
                .andExpect(status().isNotFound());
    }
}
