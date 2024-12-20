package com.reciepefinder.repository;

import com.reciepefinder.model.entity.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FridgeRepository extends JpaRepository<Fridge, Long> {
    List<Fridge> findByStatus(String status);
    List<Fridge> findByProductId(Long productId);
}
