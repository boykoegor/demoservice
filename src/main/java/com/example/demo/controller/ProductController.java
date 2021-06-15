package com.example.demo.controller;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "product-mapping")
    public void addNewMappings(@RequestBody Map<String, String> productMapping){
        productService.addNewMappings(productMapping);
    }

    @GetMapping(value = "actual-product")
    public String getActualProductByProvidedProduct(@RequestParam ("provided-product") String providedProduct){
        return productService.getActualProductByProvidedProduct(providedProduct);
    }

    @GetMapping(value = "product-mapping")
    public Map<String, String> getProductsMapping(){
        return productService.getProductsMapping();
    }
}
