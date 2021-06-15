package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String actualProduct;
    private String providedProduct;


    public void setActualProduct(String actualProduct){
        this.actualProduct = actualProduct;
   }

    public String getActualProduct(){
        return actualProduct;
   }

    public void setProvidedProduct(String providedProduct){
        this.providedProduct = providedProduct;
    }

    public String getProvidedProduct(){
        return providedProduct;
    }


    public  ProductMapping(){}

    public ProductMapping(String key, String value){
        this.actualProduct = value;
        this.providedProduct = key;
    }
}
