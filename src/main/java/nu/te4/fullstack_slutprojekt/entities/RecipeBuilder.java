package nu.te4.fullstack_slutprojekt.entities;

import java.sql.Blob;
import java.util.List;

public class RecipeBuilder {
    private Blob image;
    private String information;
    private List<String> instructions;
    private List<Ingredient> ingredients;
    private List<String> categories;
    private String title;
    private int id;
    private int likes;
    private int repports;
    private int writerId;

    public RecipeBuilder() {
    }

    public Recipe build() {
        if (getId() < 0) {
            setId(0);
        }
        if (getWriterId() < 0) {
            setWriterId(0);
        }
        if (getInformation() == null) {
            setInformation("No Information available");
        }
        if (getInstructions() == null) {
            throw new IllegalStateException("A recipe must have instructions");
        }
        if (getTitle() == null) {
            throw new IllegalStateException("A recipe must have a title");
        }
        if (getIngredients() == null) {
            throw new IllegalStateException("A recipe must have ingredients");
        }
        if (getCategories() == null) {
            throw new IllegalStateException("A recipe must be in at least one category");
        }
        return new Recipe(this);
    }

    public Blob getImage() {
        return image;
    }

    public RecipeBuilder setImage(Blob image) {
        this.image = image;
        return this;
    }

    public String getInformation() {
        return information;
    }

    public RecipeBuilder setInformation(String information) {
        this.information = information;
        return this;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public RecipeBuilder setInstructions(List<String> instructions) {
        this.instructions = instructions;
        return this;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public RecipeBuilder setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public List<String> getCategories() {
        return categories;
    }

    public RecipeBuilder setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    public int getId() {
        return id;
    }

    public RecipeBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public RecipeBuilder setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public int getRepports() {
        return repports;
    }

    public RecipeBuilder setRepports(int repports) {
        this.repports = repports;
        return this;
    }

    public int getWriterId() {
        return writerId;
    }

    public RecipeBuilder setWriterId(int writerId) {
        this.writerId = writerId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RecipeBuilder setTitle(String title) {
        this.title = title;
        return this;
    }
}
