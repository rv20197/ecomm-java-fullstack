package com.vatsalrajgor.eCommerce.service;

import com.vatsalrajgor.eCommerce.DTO.Product.ProductDTO;
import com.vatsalrajgor.eCommerce.DTO.Product.ProductResponse;
import com.vatsalrajgor.eCommerce.exceptions.APIException;
import com.vatsalrajgor.eCommerce.exceptions.ResourceNotFoundException;
import com.vatsalrajgor.eCommerce.mapper.ProductMapper;
import com.vatsalrajgor.eCommerce.model.Category;
import com.vatsalrajgor.eCommerce.model.Product;
import com.vatsalrajgor.eCommerce.repository.CategoryRepo;
import com.vatsalrajgor.eCommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(CategoryRepo categoryRepo, ProductRepo productRepo, ProductMapper productMapper){
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

    public ProductDTO addProduct(Product product, Long categoryId){
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId", categoryId));
        product.setCategory(category);
        double specialPrice = product.getPrice() - ((product.getDiscount()/100) * product.getPrice());
        product.setSpecialPrice(specialPrice);
        product.setImage("default.png");
        Product savedProduct = productRepo.save(product);
        return productMapper.toDTO(savedProduct);
    }

    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder){
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber-1, pageSize, sortByAndOrder);
        Page<Product> productPage = productRepo.findAll(pageDetails);
        List<Product> allProducts = productPage.getContent();
        if (allProducts.isEmpty()) throw new APIException("No products found!");
        List<ProductDTO> productDTOS = allProducts.stream().map(productMapper::toDTO).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageNumber(productPage.getNumber()+1);
        productResponse.setTotalElements(productPage.getTotalElements());
        productResponse.setTotalPages(productPage.getTotalPages());
        productResponse.setLastPage(productPage.isLast());
        productResponse.setPageSize(productPage.getSize());
        return productResponse;
    }
}
