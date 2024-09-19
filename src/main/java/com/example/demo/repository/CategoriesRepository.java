package com.example.demo.repository;

import com.example.demo.etity.categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<categories,Integer> {
    categories getById(Integer id);
}
