package com.example.shoppingcart.repository;

import com.example.shoppingcart.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
