package com.reciepefinder.service;

import com.reciepefinder.model.dto.RecipeDto;
import com.reciepefinder.model.entity.Recipe;
import com.reciepefinder.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeDto> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<RecipeDto> getAllRecipesForFridge() {
        return recipeRepository.findAllRecipesForFridge().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public RecipeDto getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + id));
        return mapToDto(recipe);
    }

    public RecipeDto saveRecipe(RecipeDto recipeDto) {
        Recipe recipe = mapToEntity(recipeDto);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return mapToDto(savedRecipe);
    }

    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RuntimeException("Recipe not found with ID: " + id);
        }
        recipeRepository.deleteById(id);
    }

    private Recipe mapToEntity(RecipeDto recipeDto) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeDto.getName());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setInstructions(recipeDto.getInstructions());
        return recipe;
    }

    private RecipeDto mapToDto(Recipe recipe) {
        RecipeDto dto = new RecipeDto();
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        dto.setDescription(recipe.getDescription());
        dto.setInstructions(recipe.getInstructions());
        return dto;
    }
}
