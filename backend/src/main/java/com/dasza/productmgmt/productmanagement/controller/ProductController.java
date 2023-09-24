package com.dasza.productmgmt.productmanagement.controller;

import com.dasza.productmgmt.auth.model.User;
import com.dasza.productmgmt.productmanagement.model.Product;
import com.dasza.productmgmt.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product, Authentication authentication) {
        Product savedProduct = productService.saveProduct(product, authentication);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(Authentication authentication) {
        List<Product> products = productService.getAllProducts(authentication);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id, Authentication authentication) {
        Product product = productService.getProductById(id);

        // Check if the authenticated user is the owner of the product
        User authenticatedUser = (User) authentication.getPrincipal();
        if (!product.getUser().equals(authenticatedUser)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id, Authentication authentication) {
        Product product = productService.getProductById(id);

        // Check if the authenticated user is the owner of the product
        User authenticatedUser = (User) authentication.getPrincipal();
        if (!product.getUser().equals(authenticatedUser)) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        // Proceed with deleting the product
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Product> editProduct(@RequestBody Product product, @PathVariable Integer id, Authentication authentication) {
        Product existingProduct = productService.getProductById(id);

        // Check if the authenticated user is the owner of the product
        User authenticatedUser = (User) authentication.getPrincipal();
        if (!existingProduct.getUser().equals(authenticatedUser)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Proceed with editing the product
        Product editedProduct = productService.editProduct(product, id);
        return new ResponseEntity<>(editedProduct, HttpStatus.OK);
    }
}
