package com.example.services;

import com.example.config.UrlProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuyProductTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private UrlProperties urlProperties;

    @InjectMocks
    private ProductServices productServices;

    @Test
    public void byProductTest() {
        Long productId = 123L;
        int quantity = 5;
        String mockUrl = "http://localhost:8081/warehouse/reserveProduct";

        // Подменяем url
        when(urlProperties.getReserveProduct()).thenReturn("http://localhost:8081/warehouse/reserveProduct");

        // Создание ожидаемого ответа от сервера
        String expectedResponse = "Product reserved successfully";
        ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

        when(restTemplate.postForEntity(
                mockUrl,
                createRequestBody(productId, quantity),
                String.class)
        ).thenReturn(expectedResponseEntity);

        // Выполнение тестируемого метода
        ResponseEntity<String> actualResponseEntity = productServices.buyProduct(productId, quantity);

        // Проверка результата
        assertEquals(expectedResponseEntity.getStatusCode(), actualResponseEntity.getStatusCode());
        assertEquals(expectedResponseEntity.getBody(), actualResponseEntity.getBody());
    }

    // Метод для создания тела запроса
    private MultiValueMap<String, Long> createRequestBody(Long productId, int quantity) {
        MultiValueMap<String, Long> map = new LinkedMultiValueMap<>();
        map.add("productId", productId);
        map.add("quantity", (long) quantity);
        return map;
    }

}
