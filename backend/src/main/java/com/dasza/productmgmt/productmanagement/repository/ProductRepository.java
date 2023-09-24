package com.dasza.productmgmt.productmanagement.repository;

import com.dasza.productmgmt.auth.model.User;
import com.dasza.productmgmt.productmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByUser(User user);
}
