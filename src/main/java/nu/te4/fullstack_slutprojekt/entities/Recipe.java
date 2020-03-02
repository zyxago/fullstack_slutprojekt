/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.entities;

import java.sql.Blob;
import java.util.List;

/**
 * @author erikh
 */
public class Recipe extends Post {
    private Blob image;
    private String information;
    private String title;
    private List<String> instructions;
    private List<Ingredient> ingredients;
    private List<String> categories;

    public Recipe(RecipeBuilder builder) {
        super(builder.getId(), builder.getLikes(), builder.getRepports(), builder.getWriterId());
        this.image = builder.getImage();
        this.title = builder.getTitle();
        this.information = builder.getInformation();
        this.instructions = builder.getInstructions();
        this.ingredients = builder.getIngredients();
        this.categories = builder.getCategories();
    }

    public Recipe(){}

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
