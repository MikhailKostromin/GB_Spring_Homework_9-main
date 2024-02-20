package com.example.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private long id;

    private String name;

    private int quantity;

    private int price;

}
