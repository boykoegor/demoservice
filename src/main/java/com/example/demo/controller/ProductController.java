package com.example.demo.controller;

import com.example.demo.repository.ProductMappingRepo;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMappingRepo productMappingRepo;

    @PostMapping(value = "product-mapping", consumes = "application/json")
    public ResponseEntity addNewMappings(@RequestBody Map<String, String> productMapping){
        return productService.addNewMappings(productMapping);
    }

    //общение при помощи json, методы должны давать разные статусы, если не найдено 404, если все хорошо 200, add-line делает update,

    @PostMapping(value = "actual-product", produces = "application/json")
    public ResponseEntity<String> getActualProductByProvidedProduct(@RequestParam ("provided-product") String providedProduct){
        return productService.getActualProductByProvidedProduct(providedProduct);
    }

    @GetMapping(value = "product-mapping",  produces = "application/json")
    public ResponseEntity<Map<String, String>> getProductsMapping(){
        return productService.getProductsMapping();
    }

    @PostMapping(value = "add-line")
    public ResponseEntity<HttpStatus> addLine(@RequestParam String key, @RequestParam String value){
        return productService.addLine(key, value);
    }

    @DeleteMapping(value = "product-mapping")
    public ResponseEntity deleteProductMapping(){
        productMappingRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
