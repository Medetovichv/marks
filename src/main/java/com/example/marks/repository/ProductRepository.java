package com.example.marks.repository;

import com.example.marks.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteById(Long l);

    List<Product> findByNameContaining(String name);

}
