package com.dasza.productmgmt.productmanagement.service;

import com.dasza.productmgmt.auth.model.User;
import com.dasza.productmgmt.productmanagement.model.Product;
import com.dasza.productmgmt.productmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public Product saveProduct(Product product, Authentication authentication) {
        // Get the authenticated user
        User authenticatedUser = (User) authentication.getPrincipal();

        // Set the authenticated user as the owner of the product
        product.setUser(authenticatedUser);

        return repository.save(product);
    }

    @Override
    public List<Product> getAllProducts(Authentication authentication) {
        // Get the authenticated user
        User authenticatedUser = (User) authentication.getPrincipal();

        // Fetch products associated with the authenticated user
        return repository.findByUser(authenticatedUser);
    }

    @Override
    public Product getProductById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public String deleteProduct(Integer id) {
        Product product = repository.findById(id).get();

        if (product != null) {
            repository.delete(product);
            return "Product Delete Sucessfully";
        }

        return "Something wrong on server";
    }

    @Override
    public Product editProduct(Product p, Integer id) {

        Product oldProduct = repository.findById(id).get();

        oldProduct.setProductName(p.getProductName());
        oldProduct.setDescription(p.getDescription());
        oldProduct.setPrice(p.getPrice());
        oldProduct.setStatus(p.getStatus());

        return repository.save(oldProduct);
    }

}
