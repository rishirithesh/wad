package com.example.shoppingcart.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cart")
public class CartItem {
    @Id
    private String id;
    private String productName;
    private int quantity;
}
