import Recipe from "../../entities/Recipe";

/**
 *
 * @param setRecipeList
 * @param mainPath
 * @returns {Promise<void>}
 */
export async function getRecipes(setRecipeList, mainPath) {
    const res = await fetch(`http://${window.location.host}${mainPath}/api/recipes`);
    if (res.status === 200) {
        const data = await res.json();
        const recipes = [];
        if (data) {
            for (const recipeData of data) {
                let recipe = new Recipe(recipeData);
                await getRecipeImage(recipeData.id, (img) => {
                    recipe.image = img
                }, mainPath);
                recipes.push(recipe)
            }
        }
        setRecipeList(recipes);
    }
}

export async function likeRecipe(id, userId, mainPath) {
    const res = await fetch(`http://${window.location.host}${mainPath}/api/recipe/like/${id}/${userId}`, {
        method: "PUT",
        headers: {
            'Authorization': window.localStorage.getItem("token"),
            'Content-Type': 'application/json'
        }
    });
    const data = res.status;
    console.log(data);
}

export async function reportRecipe(id, userId, mainPath) {
    const res = await fetch(`http://${window.location.host}${mainPath}/api/recipe/report/${id}/${userId}`, {
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
 * @param id
 * @param setRecipeImage
 * @param mainPath
 * @returns {Promise<void>}
 */
export async function getRecipeImage(id, setRecipeImage, mainPath) {
    const res = await fetch(`http://${window.location.host}${mainPath}/api/recipe/${id}/img`);
    const data = await res.blob();
    setRecipeImage(URL.createObjectURL(data));
}

/**
 *
 * @param recipe
 * @param mainPath
 * @returns {Promise<void>}
 */
export async function postRecipe(recipe, mainPath) {
    const image = recipe.image;
    recipe.image = null;
    const res = await fetch(`http://${window.location.host}${mainPath}/api/recipe`, {
        method: "POST",
        headers: {
            'Authorization': window.localStorage.getItem("token"),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(recipe)
    });
    const data = await res.json();
    await updateRecipeImage(data, image, mainPath);
}


export async function updateRecipe(recipe, mainPath) {
    const image = recipe.image;
    recipe.image = null;
    const res = await fetch(`http://${window.location.host}${mainPath}/api/recipe`, {
        method: "PUT",
        headers: {
            'Authorization': window.localStorage.getItem("token"),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(recipe)
    });
    await updateRecipeImage(recipe.id, image, mainPath)
}

/**
 *
 * @param id
 * @param image
 * @returns {Promise<void>}
 */
export async function updateRecipeImage(id, image, mainPath) {
    const res = await fetch(`http://${window.location.host}${mainPath}/api/recipe/${id}/img`, {
        method: "PUT",
        headers: {
            'Authorization': window.localStorage.getItem("token"),
            'Content-Type': 'application/octet-stream'
        },
        body: image
    });
    let status = res.status;
    if (status === 201) {
        alert("Recipe Posted!");
    } else {
        alert(("Failed to post recipe!"));
    }
}

export async function removeRecipe(id, mainPath) {
    const res = await fetch(`http://${window.location.host}${mainPath}/api/recipe/${id}/`, {
        method: "DELETE",
        headers: {
            'Authorization': window.localStorage.getItem("token")
        }
    });
    let status = res.status;
    if (status === 200) {
        alert("Recipe Removed!");
    } else {
        alert(("Failed to remove recipe!"));
    }
}