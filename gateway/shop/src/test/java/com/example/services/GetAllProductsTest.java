package com.example.services;

import com.example.config.UrlProperties;
import com.example.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllProductsTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private UrlProperties urlProperties;

    @InjectMocks
    private ProductServices productServices;

    @Test
    public void getAllProducts() {

        Product product1 = new Product(1L, "Laptop", 10, 1200);
        Product product2 = new Product(2L, "Laptop", 10, 1200);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        String mockUrl = "http://localhost:8081/warehouse/getProducts";

        // Подменяем url
        when(urlProperties.getUriGetProducts()).thenReturn(mockUrl);

        // Подменяем данные которые возвращаются с сервера
        when(restTemplate.exchange(mockUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
        }))
                .thenReturn(new ResponseEntity<>(products, HttpStatus.OK));

        // Выполнение тестируемого метода
        List<Product> actualProducts = productServices.getAllProducts();

        // Проверка результата
        assertEquals(products.size(), actualProducts.size());
        for (int i = 0; i < products.size(); i++) {
            assertEquals(products.get(i).getId(), actualProducts.get(i).getId());
            assertEquals(products.get(i).getName(), actualProducts.get(i).getName());
        }
    }

}
