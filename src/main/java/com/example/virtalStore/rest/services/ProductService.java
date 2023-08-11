package com.example.virtalStore.rest.services;

import com.example.virtalStore.domain.entities.Categories;
import com.example.virtalStore.domain.entities.Product;
import com.example.virtalStore.domain.repositories.CategoriesRepository;
import com.example.virtalStore.domain.repositories.ProductRepository;
import com.example.virtalStore.rest.dto.ProductRequestDto;
import com.example.virtalStore.rest.dto.ProductResponseDto;
import com.example.virtalStore.rest.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<ProductResponseDto> findAll(){
        return productRepository.findAll().stream().map( product ->{
            ProductResponseDto productDto = new ProductResponseDto();
            productDto.setProductName(product.getName());
            productDto.setPrice(product.getPrice());
            return  productDto;
        }).collect(Collectors.toList());
    }

    public List<ProductResponseDto> findByCategory(String category){
        Categories categories = categoriesRepository.findByCategoryName(category)
                .orElseThrow(() -> new ObjectNotFoundException("Category name not found"));
        return productRepository.findByCategories(categories).stream().map(product ->{
            ProductResponseDto productDto = new ProductResponseDto();
            productDto.setProductName(product.getName());
            productDto.setPrice(product.getPrice());
            return  productDto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void insertNewProduct(ProductRequestDto productDto){
        Categories categories = categoriesRepository.findByCategoryName(productDto.getCategoryName())
                .orElseThrow(() -> new ObjectNotFoundException("Category name not found"));
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getProductName());
        productRepository.save(product);
        product.getCategories().add(categories);
        productRepository.save(product);
    }

    public void updateProduct(Long id,ProductRequestDto productDto){
        Product newProdcut = productRepository.getReferenceById(id);
        updateData(productDto,newProdcut);
        productRepository.save(newProdcut);
    }

    private void updateData(ProductRequestDto productDto, Product newProdcut) {
        newProdcut.setName(productDto.getProductName());
        newProdcut.setPrice(productDto.getPrice());
    }


}
