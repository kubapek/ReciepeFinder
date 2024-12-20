package com.reciepefinder.controller;

import com.reciepefinder.model.dto.FridgeDto;
import com.reciepefinder.service.FridgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fridge")
public class FridgeController {

    private final FridgeService fridgeService;

    public FridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    @PostMapping
    public ResponseEntity<String> addProductToFridge(@RequestBody FridgeDto product) {
        fridgeService.addProductToFridge(product);
        return ResponseEntity.ok("Products added to fridge.");
    }

    @GetMapping
    public ResponseEntity<List<FridgeDto>> getFridgeContents() {
        return ResponseEntity.ok(fridgeService.getAllFridgeItems());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<FridgeDto>> getProductsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(fridgeService.getFridgeItemsByStatus(status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProductFromFridge(@PathVariable Long id) {
        fridgeService.deleteProductFromFridge(id);
        return ResponseEntity.ok("Product removed from fridge.");
    }
}
