package com.reciepefinder.service;

import com.reciepefinder.model.dto.FridgeDto;
import com.reciepefinder.model.entity.Fridge;
import com.reciepefinder.repository.FridgeRepository;
import com.reciepefinder.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FridgeService {
    private final FridgeRepository fridgeRepository;
    private final ProductRepository productRepository;

    public FridgeService(FridgeRepository fridgeRepository, ProductRepository productRepository) {
        this.fridgeRepository = fridgeRepository;
        this.productRepository = productRepository;
    }

    public List<FridgeDto> getAllFridgeItems() {
        return fridgeRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<FridgeDto> getFridgeItemsByStatus(String status) {
        return fridgeRepository.findByStatus(status).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<FridgeDto> getFridgeItemsByProductId(Long productId) {
        return fridgeRepository.findByProductId(productId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public FridgeDto addProductToFridge(FridgeDto fridgeDto) {
        Fridge fridge = mapToEntity(fridgeDto);
        fridge.setAddedDate(LocalDate.now());
        Fridge saved = fridgeRepository.save(fridge);
        return mapToDto(saved);
    }

    public void updateProductStatus(Long id, String status) {
        Fridge fridge = fridgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fridge item not found with ID: " + id));
        fridge.setStatus(status);
        fridgeRepository.save(fridge);
    }

    public void deleteProductFromFridge(Long id) {
        if (!fridgeRepository.existsById(id)) {
            throw new RuntimeException("Fridge item not found with ID: " + id);
        }
        fridgeRepository.deleteById(id);
    }

    private Fridge mapToEntity(FridgeDto fridgeDto) {
        Fridge fridge = new Fridge();
        fridge.setProduct(productRepository.findById(fridgeDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + fridgeDto.getProductId())));
        fridge.setQuantity(fridgeDto.getQuantity());
        fridge.setStatus(fridgeDto.getStatus());
        return fridge;
    }

    private FridgeDto mapToDto(Fridge fridge) {
        FridgeDto dto = new FridgeDto();
        dto.setId(fridge.getId());
        dto.setProductId(fridge.getProduct().getId());
        dto.setQuantity(fridge.getQuantity());
        dto.setStatus(fridge.getStatus());
        dto.setAddedDate(fridge.getAddedDate());
        return dto;
    }
}
