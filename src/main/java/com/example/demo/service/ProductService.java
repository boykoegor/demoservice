package com.example.demo.service;

import java.util.Map;

public interface ProductService {

    void addNewMappings(Map<String, String> productMapping);

    String getActualProductByProvidedProduct(String providedProduct);

    Map<String, String> getProductsMapping();
}
