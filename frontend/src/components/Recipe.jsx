import React from "react";
import Recipe from "../entities/Recipe";
import {Card, Content, Media, Table} from "react-bulma-components";
import {TableRowIngredient} from "./Ingredient";
import "../resources/css/recipe.scss";
import {Link} from "react-router-dom";
import {likeRecipe, reportRecipe} from "../resources/api/recipe";

/**
 *
 * @param{Recipe} recipe
 * @returns {*}
 */
export function RecipeFullView({recipe, mainPath}) {
    console.log(recipe);
    return (
        <Card>
            <Card.Content>
                <Card.Header>
                    <h2>{recipe.title}</h2>
                    <button className="button" onClick={()=>likeRecipe(recipe.id)}>Like {recipe.likes}</button>
                    <button className="button" onClick={()=>reportRecipe(recipe.id)}>Report</button>
                    <Link to={`${mainPath}/postRecipe`}>Edit</Link>
                </Card.Header>
                <Content>
                    <Media>
                        <Media.Item>
                            <img src={recipe.image} href={"No image found"}/>
                        </Media.Item>
                    </Media>
                    <p>Description: {recipe.information}</p>
                    <Table>
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Amount</th>
                            <th>Measurement</th>
                        </tr>
                        </thead>
                        <tbody>
                        <TableRowRecipeIngredients recipe={recipe}/>
                        </tbody>
                    </Table>
                    <p>Categories</p>
                    <ul><ListRecipeCategories recipe={recipe}/></ul>
                    <ul><ListRecipeInstructions recipe={recipe}/></ul>
                </Content>
            </Card.Content>
        </Card>
    );
}

export function RecipeBriefView({recipe, setSelectedRecipe, mainPath}) {
    return (
        <Card>
            <Card.Content>
                <Content>
                    <h2 onClick={()=>setSelectedRecipe(recipe)}><Link to={`${mainPath}/${recipe.title}`}>{recipe.title}</Link></h2>
                    <Media>
                        <Media.Item>
                            <img src={recipe.image} href={"No image found"}/>
                        </Media.Item>
                    </Media>
                    <p>Description: {recipe.information}</p>
                    <p>Categories</p>
                    <ul><ListRecipeCategories recipe={recipe}/></ul>
                </Content>
            </Card.Content>
        </Card>
    )
}

export function TableRowRecipeIngredients({recipe}) {
    let ingredients = [];
    for (const ingredient of recipe.ingredients) {
        ingredients.push(<TableRowIngredient ingredient={ingredient}/>)
    }
    return ingredients;
}

function ListRecipeCategories({recipe}) {
    let categories = [];
    for (const category of recipe.categories) {
        categories.push(<li key={categories.length}>{category}</li>)
    }
    return categories;
}

function ListRecipeInstructions({recipe}) {
    let instructions = [];
    for (const instruction of recipe.instructions) {
        instructions.push(<li key={recipe.instructions.indexOf(instruction)}>{`${recipe.instructions.indexOf(instruction)+1}: ${instruction}`}</li>)
    }
    return instructions;
}
