import React from "react";
import {getRecipes} from "../resources/api/recipe";
import {RecipeBriefView} from "./Recipe";

export default function RecipeTable() {
    const [recipeList, setRecipeList] = React.useState([]);
    React.useEffect(() => {
        getRecipes(setRecipeList)
    }, []);//websocket dependency here maybe

    function populateTable(recipeList) {
        const htmlRecipes = [];
        for (const recipe of recipeList) {
            htmlRecipes.push(RecipeBriefView(recipe))
        }
        return htmlRecipes;
    }

    return (
        <div>
            <ul>
                {populateTable(recipeList)}
            </ul>
        </div>
    );
}