package com.example.spring_hw7_v2.services;

import com.example.spring_hw7_v2.model.Product;
import com.example.spring_hw7_v2.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(Double minCost, Double maxCost) {
        if (minCost == null && maxCost == null) {
            return productRepository.findAll();
        } else if (minCost == null && maxCost != null) {
            return productRepository.findAllByCostIsLessThan(maxCost);
        } else if (minCost != null && maxCost == null) {
            return productRepository.findAllByCostGreaterThan(minCost);
        } else {
            return productRepository.findAllByCostBetween(minCost, maxCost);
        }
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
