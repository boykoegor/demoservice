package com.example.demo.service.impl;

import com.example.demo.entity.ProductMapping;
import com.example.demo.repository.ProductMappingRepo;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMappingRepo productMappingRepo;

    @Override
    public ResponseEntity addNewMappings(Map<String, String> productMap) {
        List<ProductMapping> list = new ArrayList<>();
        productMappingRepo.findAll().forEach(list::add);
        if (list.isEmpty()) {
            productMap.forEach((key, value) -> {
                productMappingRepo.save(new ProductMapping(key, value));
            });
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
    }

    @Override
    public ResponseEntity getActualProductByProvidedProduct(String providedProduct) {
        ProductMapping actualProductEntity = productMappingRepo.findByProvidedProduct(providedProduct);
        if(actualProductEntity == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(200).body(actualProductEntity.getActualProduct());
        }
    }

    @Override
    public ResponseEntity<Map<String, String>> getProductsMapping() {
        Map<String, String> productMapping = new HashMap<String, String>();
        for (ProductMapping productMap: productMappingRepo.findAll()) {
            productMapping.put(productMap.getActualProduct(), productMap.getProvidedProduct());
        }
        if(productMapping.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok().body(productMapping);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> addLine(String key, String value) {
        ProductMapping actualProductEntity = productMappingRepo.findByProvidedProduct(key);
        if(actualProductEntity == null) {
            ProductMapping productMapping = new ProductMapping(key, value);
            productMappingRepo.save(productMapping);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            actualProductEntity.setActualProduct(value);
            productMappingRepo.save(actualProductEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
