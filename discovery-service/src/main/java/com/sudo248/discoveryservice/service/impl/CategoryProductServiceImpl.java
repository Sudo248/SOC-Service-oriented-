package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.controller.dto.CategoryProductDto;
import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.repository.CategoryProductRepository;
import com.sudo248.discoveryservice.repository.entity.CategoryProduct;
import com.sudo248.discoveryservice.repository.entity.SupplierProduct;
import com.sudo248.discoveryservice.service.CategoryProductService;
import com.sudo248.discoveryservice.service.CategoryService;
import com.sudo248.discoveryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryProductServiceImpl implements CategoryProductService {
    @Autowired
    private CategoryProductRepository categoryProductRepository;
    @Autowired
    private ProductService productService;
    @Override
    public List<ProductDto> getProductByIdCategory(String id) {
        List<CategoryProduct> categoryProducts =  categoryProductRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(CategoryProduct s: categoryProducts){

            if(s.getCategory().getCategoryId().equals(id)){

                productDtos.add(productService.toDto(s.getProduct()));
            }

        }
        return productDtos;
    }

    @Override
    public CategoryProductDto toDto(CategoryProduct categoryProduct) {
        return null;
    }

    @Override
    public CategoryProduct toEntity(CategoryProductDto categoryProductDto) {
        return null;
    }
}
