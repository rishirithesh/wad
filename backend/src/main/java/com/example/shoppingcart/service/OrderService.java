package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Order;
import com.example.shoppingcart.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repo;

    public Order place(Order order) {
        order.setStatus("Placed");
        return repo.save(order);
    }

    public List<Order> getAll() {
        return repo.findAll();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
