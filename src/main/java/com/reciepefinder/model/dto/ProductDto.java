package com.reciepefinder.model.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ProductDto {
    private Long id;
    private String name;
    private String category;
    private LocalDate addedDate;
}
