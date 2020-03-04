import React from "react";
import Recipe from "../entities/Recipe";
import {Card, Content, Media} from "react-bulma-components";
import {TableRowIngredient} from "./Ingredient";

/**
 *
 * @param{Recipe} recipe
 * @returns {*}
 */
export function RecipeFullView({recipe}) {
    return (
        <Card>
            <Card.Content>
                <Media>
                    <Media.Item>
                        {recipe.image}
                    </Media.Item>
                </Media>
                <Content>
                    <div>name of recipe here: {recipe.title}</div>
                    <div>categories: {recipe.categories}</div>
                    <div>likes: {recipe.likes}</div>
                    <div>repports: {recipe.repports}</div>
                    <div>info here: {recipe.information}</div>
                    <div>ingredients: {recipe.ingredients}</div>{/*make react component for ingredients*/}
                </Content>
            </Card.Content>
        </Card>
    )
}

/**
 *
 * @param{Recipe} recipe
 * @returns {*}
 */
export function RecipeBriefView({recipe}) {
    return (
        <Card>
            <Card.Content onClick={()=>console.log("He clicked on me :O")}>
                <Media>
                    <Media.Item>
                        <img src={recipe.image} href={"No image found"}/>
                    </Media.Item>
                </Media>
                <Content>
                    <div>name of recipe here: {recipe.title}</div>
                    <div>categories: {recipe.categories}</div>
                </Content>
            </Card.Content>
        </Card>
    )
}

export function TableRowRecipeIngredients({recipe}){
    let ingredients = [];
    for(const ingredient of recipe.ingredients){
        ingredients.push(<TableRowIngredient ingredient={ingredient}/>)
    }
    return ingredients;
}