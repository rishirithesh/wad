package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;

    public Product add(Product product) {
        return repo.save(product);
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product getOne(String id) {
        return repo.findById(id).orElse(null);
    }

    public Product update(String id, Product product) {
        product.setId(id);
        return repo.save(product);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
