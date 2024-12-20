package com.reciepefinder.model.dto;

import lombok.Data;

@Data
public class RecipeDto {
    private Long id;
    private String name;
    private String description;
    private String instructions;
}
