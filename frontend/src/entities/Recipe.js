export default class Recipe {

    constructor(recipeData) {
        if(recipeData) {
            this.writerId = recipeData.writerId;
            this.image = recipeData.image;
            this.information = recipeData.information;
            this.instructions = recipeData.instructions;
            this.categories = recipeData.categories;
            this.ingredients = recipeData.ingredients;
            this.likes = recipeData.likes;
            this.repports = recipeData.repports;
            this.title = recipeData.title;
        } else{
            this.writerId = 0;
            this.image = "";
            this.information = "";
            this.instructions = [];
            this.categories = [];
            this.ingredients = [];
            this.likes = 0;
            this.repports = 0;
            this.title = "";
        }
    }
}