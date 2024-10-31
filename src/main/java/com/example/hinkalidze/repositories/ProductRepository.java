package com.example.hinkalidze.repositories;

import com.example.hinkalidze.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByTitle(String title);
}
