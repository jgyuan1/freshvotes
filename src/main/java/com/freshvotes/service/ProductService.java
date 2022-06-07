package com.freshvotes.service;

import com.freshvotes.domain.Product;
import com.freshvotes.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createANewProduct(){
        Product product = new Product();
        return product;
    }
    public Long saveProduct(Product product){

        Product dbProduct = productRepository.save(product);
        System.out.println("dbProduct Id = " + dbProduct.getId());
        return productRepository.findByName(product.getName()).getId();
    }
    public Product findProductById(Long productId){
        return productRepository.getReferenceById(productId);

    }

    public Product findProductByName(String name){
        return productRepository.findByName(name);
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

}
