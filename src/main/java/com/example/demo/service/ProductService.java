package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ProductService {

    ResponseEntity addNewMappings(Map<String, String> productMapping);

    ResponseEntity getActualProductByProvidedProduct(String providedProduct);

    ResponseEntity<Map<String, String>> getProductsMapping();


    ResponseEntity<HttpStatus> addLine(String key, String value);
}
