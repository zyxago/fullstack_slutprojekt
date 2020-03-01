import Recipe from "../../entities/Recipe";

/**
 *
 * @param{Function} setRecipeList
 * @returns {Promise<void>}
 */
export async function getRecipes(setRecipeList) {
    const result = await fetch(`http://127.0.0.1:8080/fullstack_slutprojekt-1.0/api/recipes`);
    const data = await result.json();
    const recipes = [];
    if (data) {
        for (const recipeData of data) {
            recipes.push(new Recipe(recipeData))
        }
    }
    console.log("Recipes: " + recipes);
    setRecipeList(recipes);
}

/**
 *
 * @param{Number} id ID of recipe to get image for
 * @param {Function} setRecipeImage
 * @returns {Promise<void>}
 */
export async function getRecipeImage(id, setRecipeImage) {
    const result = await fetch(`${window.location.host}/api/recipe/${id}/image`);
    const data = await result.blob();
    console.log(data);
    setRecipeImage(data);
}

/**
 *
 * @param recipe
 * @returns {Promise<void>}
 */
export async function postRecipe(recipe) {
    const result = await fetch(`${window.location.host}/api/recipe`, {
        method: "POST",
        body: JSON.stringify(recipe)
    });
    const response = result.status;
    console.log("Response: " + response);
}

/**
 *
 * @param id
 * @param image
 * @returns {Promise<void>}
 */
export async function postRecipeImage(id, image) {
    const result = await fetch(`${window.location.host}/api/recipe/${id}/image`, {
        method: "POST",
        body: image
    });
    const response = result.status;
    console.log("Response: " + response);
}