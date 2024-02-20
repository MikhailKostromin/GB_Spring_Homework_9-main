package com.example.controllers;

import com.example.model.Product;
import com.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private ProductService productService;


    /**
     * Получает список всех продуктов из сервиса productService и возвращает его в ответ на запрос.
     *
     * @return ResponseEntity с списком всех продуктов, если они есть, или пустой список.
     */
    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }


    /**
     * Получает детали продукта по его идентификатору из сервиса productService и возвращает его в ответ на запрос.
     *
     * @param id Идентификатор продукта.
     * @return ResponseEntity с деталями продукта, если он найден, или ошибку 404 Not Found, если продукт с указанным идентификатором не найден.
     */
    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProducts(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }


    /**
     * Резервирует указанное количество товара по его идентификатору.
     *
     * @param productId Идентификатор продукта для резервации.
     * @param quantity  Количество товара для резервации.
     * @return ResponseEntity с сообщением об успешном резервировании товара.
     */
    @PostMapping("/reserveProduct")
    @Transactional
    public ResponseEntity<String> reserveProduct(@RequestParam Long productId, @RequestParam int quantity) {
        productService.reserveProduct(productId, quantity);
        return ResponseEntity.ok("Product reserved successfully");
    }

}
