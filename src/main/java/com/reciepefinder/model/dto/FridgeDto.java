package com.reciepefinder.model.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class FridgeDto {
    private Long id;
    private Long productId;
    private LocalDate addedDate;
    private String status;
    private Float quantity;
}
