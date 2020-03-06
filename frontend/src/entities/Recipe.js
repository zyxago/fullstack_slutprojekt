export default class Recipe {

    constructor(recipeData) {
        if(recipeData) {
            this.id = recipeData.id;
            this.writerId = recipeData.writerId;
            this.image = recipeData.image;
            this.information = recipeData.information;
            this.instructions = recipeData.instructions;
            this.categories = recipeData.categories;
            this.ingredients = recipeData.ingredients;
            this.likes = recipeData.likes;
            this.reports = recipeData.reports;
            this.title = recipeData.title;
        } else{
            this.writerId = 0;
            this.image = "";
            this.information = "";
            this.instructions = [];
            this.categories = [];
            this.ingredients = [];
            this.likes = 0;
            this.reports = 0;
            this.title = "";
        }
    }
}