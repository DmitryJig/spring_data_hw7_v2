package com.example.spring_hw7_v2.controllers;

import com.example.spring_hw7_v2.model.Product;
import com.example.spring_hw7_v2.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Если параметры приходят фильтруем по ним, если нет то выводим все товары,
    // Не знаю как правильней, может надо было для минимальной и максимальной цены
    // установить дефолтные значения Double.MIN_VALUE и DOUBLE.MAX_VALUE?
    // http://localhost:8189/app/products
    // http://localhost:8189/app/products?minCost=500&maxCost=2000
    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam (name = "minCost", required = false) Double minCost,
            @RequestParam (name = "maxCost", required = false) Double maxCost){
        return productService.getAllProducts(minCost, maxCost);
    }

    // http://localhost:8189/app/products/1
    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id){
        return productService.findProductById(id).get();
    }

    // POST JSON {"title": "soup", "cost": 50.0}
    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return productService.save(product);
    }

    // DELETE http://localhost:8189/app/products?id=21
    @DeleteMapping
    public void normalDeleteById(@RequestParam(name = "id") Long id){
        productService.deleteById(id);
    }
}
