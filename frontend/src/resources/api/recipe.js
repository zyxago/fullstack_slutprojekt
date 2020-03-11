import Recipe from "../../entities/Recipe";

/**
 *
 * @param{Function} setRecipeList
 * @returns {Promise<void>}
 */
export async function getRecipes(setRecipeList) {
    const res = await fetch("/fullstack_slutprojekt-1.0/api/recipes");
    const data = await res.json();
    const recipes = [];
    if (data) {
        for (const recipeData of data) {
            let recipe = new Recipe(recipeData);
            await getRecipeImage(recipeData.id, (img) => {
                recipe.image = img
            });
            recipes.push(recipe)
        }
    }
    setRecipeList(recipes);
}

export async function likeRecipe(id, userId) {
    const res = await fetch(`/fullstack_slutprojekt-1.0/api/recipe/like/${id}/${userId}`, {
        method: "PUT",
        headers: {
            'Authorization': window.localStorage.getItem("token"),
            'Content-Type': 'application/json'
        }
    });
    const data = res.status;
    console.log(data);
}

export async function reportRecipe(id, userId) {
    const res = await fetch(`/fullstack_slutprojekt-1.0/api/recipe/report/${id}/${userId}`, {
        method: "PUT",
        headers: {
            'Authorization': window.localStorage.getItem("token"),
            'Content-Type': 'application/json'
        }
    });
    const data = res.status;
    console.log(data);
}

/**
 *
 * @param{Number} id ID of recipe to get image for
 * @param {Function} setRecipeImage
 * @returns {Promise<void>}
 */
export async function getRecipeImage(id, setRecipeImage) {
    const res = await fetch(`/fullstack_slutprojekt-1.0/api/recipe/${id}/img`);
    const data = await res.blob();
    setRecipeImage(URL.createObjectURL(data));
}

/**
 *
 * @param recipe
 * @returns {Promise<void>}
 */
export async function postRecipe(recipe) {
    const image = recipe.image;
    recipe.image = null;
    const res = await fetch("/fullstack_slutprojekt-1.0/api/recipe", {
        method: "POST",
        headers: {
            'Authorization': window.localStorage.getItem("token"),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(recipe)
    });
    const data = await res.json();
    await postRecipeImage(data, image);
}

export async function updateRecipe(recipe){
    recipe.image = null;
    const res = await fetch(`/fullstack_slutprojekt-1.0/api/recipe`, {
        method: "PUT",
        headers: {
            'Authorization': window.localStorage.getItem("token"),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(recipe)
    });
    let status = res.status;
    if(status === 200){
        alert("Recipe Updated!");
    }else{
        alert(("Failed to update recipe!"));
    }
}

/**
 *
 * @param id
 * @param image
 * @returns {Promise<void>}
 */
export async function postRecipeImage(id, image) {
    const res = await fetch(`/fullstack_slutprojekt-1.0/api/recipe/${id}/img`, {
        method: "PUT",
        headers: {
            'Authorization': window.localStorage.getItem("token"),
            'Content-Type': 'application/octet-stream'
        },
        body: image
    });
    let status = res.status;
    if(status === 201){
        alert("Recipe Posted!");
    }else{
        alert(("Failed to post recipe!"));
    }
}

export async function removeRecipe(id){
    const res = await fetch(`/fullstack_slutprojekt-1.0/api/recipe/${id}/`, {
        method: "DELETE",
        headers:{
            'Authorization': window.localStorage.getItem("token")
        }
    });
    let status = res.status;
    if(status === 200){
        alert("Recipe Removed!");
    }else{
        alert(("Failed to remove recipe!"));
    }
}