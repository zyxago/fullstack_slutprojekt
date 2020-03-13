import React from "react";
import {Card, Content, Media, Table} from "react-bulma-components";
import {TableRowIngredient} from "./Ingredient";
import "../resources/css/recipe.scss";
import {Link} from "react-router-dom";
import {likeRecipe, removeRecipe, reportRecipe} from "../resources/api/recipe";
import CommentList from "./CommentList";
import {submitComment} from "../logic/comment";

/**
 *
 * @param recipe
 * @param user
 * @returns {*}
 * @constructor
 */
export function RecipeFullView({recipe, user}) {

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
                    <h2 className="header-left"><strong>{recipe.title}</strong>{` av ${recipe.writer}`}</h2>
                    <div className="header-right">
                    {user && <>
                        <button className="button"
                                onClick={() => likeRecipe(recipe.id, user.id)}>Like {recipe.likes}</button>
                        <button className="button"
                                onClick={() => reportRecipe(recipe.id, user.id)}>Report
                        </button>
                        {user.id === recipe.writerId && <>
                            <Link className="button" to={`/editRecipe`}>Edit</Link>
                            <button className="button" onClick={() => deleteRecipe(recipe.id)}>Remove
                                Recipe
                            </button>
                        </>}
                    </>}
                    </div>
                </Card.Header>
                <Content>
                    <Media>
                        <Media.Item>
                            <img src={recipe.image} alt=""/>
                        </Media.Item>
                    </Media>
                    <p><strong>Description: </strong>{recipe.information}</p>
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
                    <p><strong>Categories</strong></p>
                    <ul><ListRecipeCategories recipe={recipe}/></ul>
                    <p><strong>Instructions</strong></p>
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

export function RecipeBriefView({recipe, setSelectedRecipe}) {
    return (
        <Card>
            <Card.Content>
                <Content>
                    <h2 onClick={() => setSelectedRecipe(recipe)}><Link
                        to={`/${recipe.title}`}><strong>{recipe.title}</strong>{` av ${recipe.writer}`}
                    </Link>
                    </h2>
                    <Media>
                        <Media.Item>
                            <img src={recipe.image} alt=""/>
                        </Media.Item>
                    </Media>
                    <p><strong>Description: </strong>{recipe.information}</p>
                    <p><strong>Categories</strong></p>
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
