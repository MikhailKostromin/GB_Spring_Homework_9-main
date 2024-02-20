package com.example.services;

import com.example.model.Product;
import com.example.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;

    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findProductById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new IllegalArgumentException("Product not found with id: " + id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }


    /**
     * Резервирует указанное количество товара на складе по его идентификатору.
     *
     * @param productId Идентификатор продукта для резервации.
     * @param quantity  Количество товара для резервации.
     * @throws RuntimeException если количество доступного товара меньше указанного количества.
     */
    @Transactional
    public void reserveProduct(Long productId, int quantity) {
        Product product = getProductById(productId);
        if (product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
        } else {
            throw new RuntimeException("Not enough quantity available for reservation");
        }
    }

}
