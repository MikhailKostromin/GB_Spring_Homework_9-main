package com.example.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("api.storage")
public class UrlProperties {
    private String uriGetProducts;
    private String uriGetProduct;
    private String reserveProduct;

}
