package com.reciepefinder.model.entity;

import jakarta.persistence.*;
@Entity
public class RecipeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Float quantity;

    private String unit;

    // Getters and Setters
}
