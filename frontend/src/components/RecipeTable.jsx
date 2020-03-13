import React from "react";
import {getRecipes} from "../resources/api/recipe";
import {RecipeBriefView} from "./Recipe";

export default function RecipeTable({wsValue, setSelectedRecipe}) {
    const [recipeList, setRecipeList] = React.useState([]);

    React.useEffect(() => {
        console.log("Getting recipes");
        getRecipes(setRecipeList)
    }, [wsValue]);

    function PopulateTable({recipeList}) {
        const recipes = [];
        for (const recipe of recipeList) {
            recipes.push(<RecipeBriefView key={recipe.id} recipe={recipe} setSelectedRecipe={setSelectedRecipe}/>)
        }
        return recipes;
    }

    return (
        <div>
            <PopulateTable recipeList={recipeList}/>
        </div>
    );
}