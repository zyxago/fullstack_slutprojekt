export default class Recipe {

    constructor(recipeData) {
        this.writerId = recipeData.writerId;
        this.image = recipeData.image;
        this.information = recipeData.information;
        this.instructions = recipeData.instructions;
        this.categories = recipeData.categories;
        this.ingredients = recipeData.ingredients;
        this.likes = recipeData.likes;
        this.repports = recipeData.repports;
        this.title = recipeData.title;
    }
}