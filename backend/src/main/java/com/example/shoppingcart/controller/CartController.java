package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.CartItem;
import com.example.shoppingcart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @PostMapping
    public CartItem add(@RequestBody CartItem item) {
        return service.add(item);
    }

    @GetMapping
    public List<CartItem> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable String id) {
        service.remove(id);
    }

    @DeleteMapping
    public void clear() {
        service.clear();
    }
}
