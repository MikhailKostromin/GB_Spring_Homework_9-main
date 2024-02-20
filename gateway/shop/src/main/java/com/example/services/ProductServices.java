package com.example.services;

import com.example.config.UrlProperties;
import com.example.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServices {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UrlProperties urlProperties;


    public List<Product> getAllProducts() {
        String warehouseUrl = urlProperties.getUriGetProducts(); // URL модуля warehouse
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                warehouseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }


    public Product getProduct(Long id) {
        String warehouseUrl = urlProperties.getUriGetProduct() + id; // URL модуля warehouse
        ResponseEntity<Product> response = restTemplate.exchange(
                warehouseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }


    public ResponseEntity<String> buyProduct(Long productId, int quantity) {
        String warehouseUrl = urlProperties.getReserveProduct(); // URL метода резервации
        MultiValueMap<String, Long> map = new LinkedMultiValueMap<>();
        map.add("productId", productId);
        map.add("quantity", (long) quantity);
        return restTemplate.postForEntity(warehouseUrl, map, String.class);
    }

}
