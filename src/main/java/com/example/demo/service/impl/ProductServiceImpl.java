package com.example.demo.service.impl;

import com.example.demo.entity.ProductMapping;
import com.example.demo.repository.ProductMappingRepo;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMappingRepo productMappingRepo;

    @Override
    public void addNewMappings(Map<String, String> productMap) {
        productMap.forEach((key, value) ->{
            productMappingRepo.save(new ProductMapping(key, value));});
    }

    @Override
    public String getActualProductByProvidedProduct(String providedProduct) {
        ProductMapping actualProductEntity = productMappingRepo.findByProvidedProduct(providedProduct);
        return actualProductEntity.getActualProduct();
    }

    @Override
    public Map<String, String> getProductsMapping() {
        Map<String, String> productMapping = new HashMap<String, String>();
        for (ProductMapping productMap: productMappingRepo.findAll()) {
            productMapping.put(productMap.getActualProduct(), productMap.getProvidedProduct());
        }
        return productMapping;
    }
}
