package com.freshvotes.repositories;

import com.freshvotes.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    // spring Data Jpa will implement
    // Don't annotate it with @Repository

    User findByUsername(String username);
}
