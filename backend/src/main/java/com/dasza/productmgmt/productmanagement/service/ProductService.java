package com.dasza.productmgmt.productmanagement.service;

import com.dasza.productmgmt.productmanagement.model.Product;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProductService {
    public Product saveProduct(Product product, Authentication authentication);

    public List<Product> getAllProducts(Authentication authentication);

    public Product getProductById(Integer id);

    public String deleteProduct(Integer id);

    public Product editProduct(Product product,Integer id);

}
