import React from "react";
import {getRecipes} from "../resources/api/recipe";
import {RecipeBriefView} from "./Recipe";

export default function RecipeTable({mainPath, setSelectedRecipe}) {
    const [recipeList, setRecipeList] = React.useState([]);

    React.useEffect(() => {
        getRecipes(setRecipeList)
    }, []);//websocket dependency here maybe

    function PopulateTable({recipeList}) {
        const recipes = [];
        for (const recipe of recipeList) {
            recipes.push(<RecipeBriefView recipe={recipe} setSelectedRecipe={setSelectedRecipe} mainPath={mainPath}/>)
        }
        return recipes;
    }

    return (
        <div>
            <PopulateTable recipeList={recipeList}/>
        </div>
    );
}