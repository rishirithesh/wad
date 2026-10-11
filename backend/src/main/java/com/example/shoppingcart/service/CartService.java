package com.example.shoppingcart.service;

import com.example.shoppingcart.model.CartItem;
import com.example.shoppingcart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository repo;

    public CartItem add(CartItem item) {
        return repo.save(item);
    }

    public List<CartItem> getAll() {
        return repo.findAll();
    }

    public void remove(String id) {
        repo.deleteById(id);
    }

    public void clear() {
        repo.deleteAll();
    }
}
