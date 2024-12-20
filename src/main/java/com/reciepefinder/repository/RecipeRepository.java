package com.reciepefinder.repository;

import com.reciepefinder.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value = "select * from get_all_recipes_for_fridge()", nativeQuery = true)
    List<Recipe> findAllRecipesForFridge();
}

