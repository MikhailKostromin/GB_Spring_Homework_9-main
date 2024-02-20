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

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GetProductTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private UrlProperties urlProperties;

    @InjectMocks
    private ProductServices productServices;


    @Test
    public void getProduct() {
        long productId = 123L;
        Product expectedProduct = new Product(productId, "Laptop", 10, 1200);
        String mockUrl = "http://localhost:8081/warehouse/getProduct/" + productId;

        //  Подменяем url
        when(urlProperties.getUriGetProduct()).thenReturn("http://localhost:8081/warehouse/getProduct/");
        when(restTemplate.exchange(mockUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Product>() {}))
                .thenReturn(new ResponseEntity<>(expectedProduct, HttpStatus.OK));

        // Выполнение тестируемого метода
        Product actualProduct = productServices.getProduct(productId);

        // Проверка результата
        assertEquals(expectedProduct.getId(), Objects.requireNonNull(actualProduct).getId());
        assertEquals(expectedProduct.getName(), actualProduct.getName());
        assertEquals(expectedProduct.getQuantity(), actualProduct.getQuantity());
        assertEquals(expectedProduct.getPrice(), actualProduct.getPrice());
    }

}
