package com.example.demo.repository;

import com.example.demo.entity.ProductMapping;
import org.springframework.data.repository.CrudRepository;

public interface ProductMappingRepo extends CrudRepository<ProductMapping, Long> {
    ProductMapping findByProvidedProduct(String providedProduct);
}
