import React from "react";
import {getRecipes} from "../resources/api/recipe";
import {RecipeBriefView} from "./Recipe";

export default function RecipeTable({wsValue, mainPath, setSelectedRecipe}) {
    const [recipeList, setRecipeList] = React.useState([]);

    React.useEffect(() => {
        console.log("Getting recipes");
        getRecipes(setRecipeList, mainPath)
    }, [wsValue]);

    function PopulateTable({recipeList}) {
        const recipes = [];
        for (const recipe of recipeList) {
            recipes.push(<RecipeBriefView key={recipe.id} recipe={recipe} setSelectedRecipe={setSelectedRecipe}
                                          mainPath={mainPath}/>)
        }
        return recipes;
    }

    return (
        <div>
            <PopulateTable recipeList={recipeList}/>
        </div>
    );
}