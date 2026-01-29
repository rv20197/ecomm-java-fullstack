package com.vatsalrajgor.eCommerce.repository;

import com.vatsalrajgor.eCommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByProductName(String productName);
}
