package com.example.virtalStore.domain.repositories;

import com.example.virtalStore.domain.entities.Categories;
import com.example.virtalStore.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByCategories(Categories categories);
}
