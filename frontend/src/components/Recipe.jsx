import React from "react";
import Recipe from "../entities/Recipe";
import {Card, Content, Media, Table} from "react-bulma-components";
import {TableRowIngredient} from "./Ingredient";
import "../resources/css/recipe.scss";
import {Link} from "react-router-dom";
import {likeRecipe, removeRecipe, reportRecipe} from "../resources/api/recipe";
import CommentList from "./CommentList";
import {postComment} from "../resources/api/comment";

/**
 *
 * @param recipe
 * @param mainPath
 * @param user
 * @returns {*}
 * @constructor
 */
export function RecipeFullView({recipe, mainPath, user}) {

    function submitComment() {
        let text = document.getElementById("comment").value;
        let comment = {
            writerId: user.id,
            text: text,
            username: user.username,
            parentId: recipe.id
        };
        postComment(comment);
    }

    function deleteRecipe(id) {
        removeRecipe(id);
    }

    return (
        <Card>
            <Card.Content>
                <Card.Header>
                    <h2>{recipe.title}</h2>
                    {user && <>
                        <button className="button" onClick={() => likeRecipe(recipe.id)}>Like {recipe.likes}</button>
                        <button className="button" onClick={() => reportRecipe(recipe.id)}>Report</button>
                    </>}
                    {user.id === recipe.writerId && <>
                        <Link className="button" to={`${mainPath}/postRecipe`}>Edit</Link>
                        <button className="button" onClick={() => deleteRecipe(recipe.id)}>Remove Recipe</button>
                    </>}

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
                    <ol><ListRecipeInstructions recipe={recipe}/></ol>
                    <div>Comments</div>
                    <input className="input" type="text" placeholder="Say something..." id="comment"/>
                    <button className="button" onClick={submitComment}>Comment</button>
                    <CommentList recipeId={recipe.id}/>
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
                    <h2 onClick={() => setSelectedRecipe(recipe)}><Link
                        to={`${mainPath}/${recipe.title}`}>{recipe.title}</Link></h2>
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
        ingredients.push(<TableRowIngredient key={ingredient.name} ingredient={ingredient}/>)
    }
    return ingredients;
}

export function ListRecipeCategories({recipe}) {
    let categories = [];
    for (const category of recipe.categories) {
        categories.push(<li key={recipe.categories.indexOf(category)}>{category}</li>)
    }
    return categories;
}

export function ListRecipeInstructions({recipe}) {
    let instructions = [];
    for (const instruction of recipe.instructions) {
        instructions.push(<li key={`instruction-${recipe.instructions.indexOf(instruction)}`}>{instruction}</li>)
    }
    return instructions;
}
