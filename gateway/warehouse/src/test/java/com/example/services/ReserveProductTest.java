package com.example.services;


import com.example.model.Product;
import com.example.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReserveProductTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void reserveProduct_EnoughQuantity() {
        long productId = 123L;
        int quantity = 5;
        // Устанавливаем количество меньше требуемого
        Product product = new Product("Laptop", 6, 1200);
        product.setId(productId);

        // Мокируем getProductById для возвращения продукта с достаточным количеством
        when(productRepository.findProductById(productId)).thenReturn(Optional.of(product));

        // Act
        productService.reserveProduct(productId, quantity);

        // Assert
        verify(productRepository).save(product); // Проверяем, что метод save был вызван с правильным продуктом
        assertEquals(product.getQuantity(), 1); // Проверяем, что количество уменьшилось на требуемую величину
    }

    @Test
    public void reserveProduct_NotEnoughQuantity() {
        long productId = 123L;
        int quantity = 5;
        // Устанавливаем количество меньше требуемого
        Product product = new Product("Laptop", 4, 1200);
        product.setId(productId);

        // Мокируем getProductById для возвращения продукта с недостаточным количеством
        when(productRepository.findProductById(productId)).thenReturn(Optional.of(product));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> productService.reserveProduct(productId, quantity));
    }

}
