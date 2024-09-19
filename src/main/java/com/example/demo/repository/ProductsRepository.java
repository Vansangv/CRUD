package com.example.demo.repository;

import com.example.demo.etity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products,Integer> {
       boolean existsByProductName(String productName);
}
