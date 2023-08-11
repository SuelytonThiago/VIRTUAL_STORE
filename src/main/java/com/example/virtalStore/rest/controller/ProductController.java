package com.example.virtalStore.rest.controller;

import com.example.virtalStore.rest.dto.ProductRequestDto;
import com.example.virtalStore.rest.dto.ProductResponseDto;
import com.example.virtalStore.rest.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "returns all products")
    public ResponseEntity<List<ProductResponseDto>> findAllProducts(){
        return  ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/findByCategory")
    @Operation(summary = "returns all products by category")
    public ResponseEntity<List<ProductResponseDto>> findProductsByCategory(@RequestParam String category){
        return ResponseEntity.ok(productService.findByCategory(category));
    }

    @PostMapping
    @Operation(summary = "add a new product")
    public ResponseEntity<Void> insertNewProduct(@RequestBody @Valid ProductRequestDto productRequestDto){
        productService.insertNewProduct(productRequestDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idProduct}")
    @Operation(summary = "update a product")
    public ResponseEntity<Void> updateProduct(@PathVariable Long idProduct,
                                              @RequestBody ProductRequestDto productRequestDto){
        productService.updateProduct(idProduct,productRequestDto);
        return ResponseEntity.noContent().build();
    }
}
