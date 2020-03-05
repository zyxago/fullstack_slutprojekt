import Recipe from "../../entities/Recipe";

/**
 *
 * @param{Function} setRecipeList
 * @returns {Promise<void>}
 */
export async function getRecipes(setRecipeList) {
    const result = await fetch("/api/recipes");
    const data = await result.json();
    const recipes = [];
    if (data) {
        for (const recipeData of data) {
            let recipe = new Recipe(recipeData);
            await getRecipeImage(recipeData.id, (img)=>{recipe.image = img});
            recipes.push(recipe)
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
    const result = await fetch(`/api/recipe/${id}/img`);
    const data = await result.blob();
    setRecipeImage(URL.createObjectURL(data));
}

/**
 *
 * @param recipe
 * @returns {Promise<void>}
 */
export async function postRecipe(recipe) {
    //KOLLA I local storage efter användar token, skicka sedan med den i recipe
    const image = recipe.image;
    recipe.image = null;
    recipe.writerId = 1;//TEMP
    const result = await fetch("/api/recipe", {
        method: "POST",
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(recipe)
    });
    const data = await result.json();
    await postRecipeImage(data, image);
}

/**
 *
 * @param id
 * @param image
 * @returns {Promise<void>}
 */
export async function postRecipeImage(id, image) {
    const result = await fetch(`/api/recipe/${id}/img`, {
        method: "PUT",
        headers:{
            'Content-Type': 'application/octet-stream'
        },
        body: image
    });
    const response = result.status;
}