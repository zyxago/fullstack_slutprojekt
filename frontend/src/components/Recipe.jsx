import React from "react";
import Recipe from "../entities/Recipe";

/**
 *
 * @param{Recipe} recipe
 * @returns {*}
 */
export function RecipeFullView(recipe) {
    //TEMP
    return (
        <div>
            <ul>
                <li>name of recipe here</li>
                <li>likes: ${recipe.likes}</li>
                <li>repports: ${recipe.repports}</li>
                <li>info here: ${recipe.information}</li>
                <li>image here: ${recipe.image}</li>
                <li>ingredients: ${recipe.ingredients}</li>{/*make react component for ingredients*/}
                <li>categories: ${recipe.categories}</li>
            </ul>
        </div>
    )
}

/**
 *
 * @param{Recipe} recipe
 * @returns {*}
 */
export function RecipeBriefView(recipe) {
    //TEMP
    return (
        <div>
            <ul>
                <li>name of recipe here</li>
                <li>image here: ${recipe.image}</li>
                <li>categories: ${recipe.categories}</li>
            </ul>
        </div>
    )
}