package com.freshvotes.repositories;

import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    //"findBy" prepended text to a method name that triggers spring data to do something
    //"findBy" is a special reserved sort of prefix
    //derived query
    //derive a SQL statement/query for us
    Product findByName(String name);

    List<Product> findByUser(User user);

    //user @Query annotation to tell Spring Data to do the given query
    //Spring Data will know that it doesn't need to derive a query by using the "findBy" convention.
//    @Query("select p from Product p"
//        +"join fetch p.user"
//        +"where p.id = :id")
//    Optional<Product> findByIdWithUser(Long id);

}
