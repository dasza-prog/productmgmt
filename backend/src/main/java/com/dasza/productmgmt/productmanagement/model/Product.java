package com.dasza.productmgmt.productmanagement.model;

import com.dasza.productmgmt.auth.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String productName;

    private String description;

    private Double price;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
