package com.example.virtalStore.rest.controller;

import com.example.virtalStore.rest.dto.CategoryDto;
import com.example.virtalStore.rest.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @Operation(summary = "returns all categories")
    public ResponseEntity<List<CategoryDto>> findAllCategories(){
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @PostMapping
    @Operation(summary = "add a new category")
    public ResponseEntity<Void> insertNewCategory(@RequestBody @Valid CategoryDto categoryDto){
        categoryService.insertNewCategory(categoryDto);
        return ResponseEntity.noContent().build();
    }
}
