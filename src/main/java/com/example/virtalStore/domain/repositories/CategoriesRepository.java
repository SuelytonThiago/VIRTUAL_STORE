package com.example.virtalStore.domain.repositories;

import com.example.virtalStore.domain.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {

    Optional<Categories> findByCategoryName(String name);
}
