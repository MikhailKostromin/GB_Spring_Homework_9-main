package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GateWay {
    public static void main(String[] args) {
        SpringApplication.run(GateWay.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("Shop", r -> r.path("/shop/**")
                        .uri("http://localhost:8080/"))
                .route("Warehouse", r -> r.path("/warehouse/**")
                        .uri("http://localhost:8081/")).build();
    }

}