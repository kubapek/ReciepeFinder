package com.reciepefinder.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "added_date", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate addedDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Float quantity;
}
