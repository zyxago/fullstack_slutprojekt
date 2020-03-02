import Recipe from "../../entities/Recipe";

/**
 *
 * @param{Function} setRecipeList
 * @returns {Promise<void>}
 */
export async function getRecipes(setRecipeList) {
    const result = await fetch("api/recipes");
    const data = await result.json();
    const recipes = [];
    if (data) {
        for (const recipeData of data) {
            recipes.push(new Recipe(recipeData))
        }
    }
    setRecipeList(recipes);
}

/**
 *
 * @param{Number} id ID of recipe to get image for
 * @param {Function} setRecipeImage
 * @returns {Promise<void>}
 */
export async function getRecipeImage(id, setRecipeImage) {
    const result = await fetch("api/image");
    const data = await result.blob();
    setRecipeImage(data);
}

/**
 *
 * @param recipe
 * @returns {Promise<void>}
 */
export async function postRecipe(recipe) {
    const result = await fetch("api/recipe", {
        method: "POST",
        body: JSON.stringify(recipe)
    });
    const response = result.status;
}

/**
 *
 * @param id
 * @param image
 * @returns {Promise<void>}
 */
export async function postRecipeImage(id, image) {
    const result = await fetch("api/image", {
        method: "POST",
        body: image
    });
    const response = result.status;
}