package com.example.virtalStore.rest.services;

import com.example.virtalStore.domain.entities.Categories;
import com.example.virtalStore.domain.repositories.CategoriesRepository;
import com.example.virtalStore.rest.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public void insertNewCategory(CategoryDto categoryDto){
        Categories categories = new Categories();
        categories.setCategoryName(categoryDto.getName());
        categoriesRepository.save(categories);
    }

    public List<CategoryDto> findAllCategories(){
        return categoriesRepository.findAll().stream().map(category ->{
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category.getCategoryName());
            return categoryDto;
        }).collect(Collectors.toList());
    }
}
