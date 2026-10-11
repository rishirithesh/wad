package com.example.shoppingcart.repository;

import com.example.shoppingcart.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<CartItem, String> {
}
