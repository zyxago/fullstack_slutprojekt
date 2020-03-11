import React from "react";
import {Card, Content, Media, Table, Level} from "react-bulma-components";
import {TableRowIngredient} from "./Ingredient";
import "../resources/css/recipe.scss";
import {Link} from "react-router-dom";
import {likeRecipe, removeRecipe, reportRecipe} from "../resources/api/recipe";
import CommentList from "./CommentList";
import {submitComment} from "../logic/comment";

/**
 *
 * @param recipe
 * @param mainPath
 * @param user
 * @returns {*}
 * @constructor
 */
export function RecipeFullView({recipe, mainPath, user}) {

    if (user === undefined) {
        user = false;
    }

    function deleteRecipe(id) {
        removeRecipe(id);
    }

    return (
        <Card>
            <Card.Content>
                <Card.Header>
                    <Level renderAs="div">
                        <Level.Side align="left">
                            <Level.Item>
                                <h2>{recipe.title}</h2>
                            </Level.Item>
                        </Level.Side>
                        <Level.Side align="right">
                            {user && <>
                                <Level.Item>
                                    <button className="button"
                                            onClick={() => likeRecipe(recipe.id, user.id)}>Like {recipe.likes}</button>
                                </Level.Item>
                                <Level.Item>
                                    <button className="button"
                                            onClick={() => reportRecipe(recipe.id, user.id)}>Report
                                    </button>
                                </Level.Item>
                                {user.id === recipe.writerId && <>
                                    <Level.Item>
                                        <Link className="button" to={`${mainPath}/editRecipe`}>Edit</Link>
                                    </Level.Item>
                                    <Level.Item>
                                        <button className="button" onClick={() => deleteRecipe(recipe.id)}>Remove
                                            Recipe
                                        </button>
                                    </Level.Item>
                                </>}
                            </>}
                        </Level.Side>
                    </Level>
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
                    <h4>Comments</h4>
                    {user && <>
                        <input className="input" type="text" placeholder="Say something..." id="commentInput"/>
                        <button className="button"
                                onClick={() => submitComment(document.getElementById("commentInput").value, user, recipe.id)}>Comment
                        </button>
                    </>}
                    <CommentList parentId={recipe.id} user={user}/>
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
