package com.freshvotes.repositories;

import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findByName(String name);
    List<Product> findAll();
}
