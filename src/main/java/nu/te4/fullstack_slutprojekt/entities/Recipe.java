/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.entities;

import java.util.List;

/**
 * @author erikh
 */
public class Recipe extends Post {
    private String image;
    private String information;
    private List<String> instructions;
    private List<Ingredient> ingredients;
    private List<String> categories;

    public Recipe(RecipeBuilder builder) {
        super(builder.getId(), builder.getLikes(), builder.getRepports(), builder.getWriterId());
        this.image = builder.getImage();
        this.information = builder.getInformation();
        this.instructions = builder.getInstructions();
        this.ingredients = builder.getIngredients();
        this.categories = builder.getCategories();
    }

    private Recipe(){}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
