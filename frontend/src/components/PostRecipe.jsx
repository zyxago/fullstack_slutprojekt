import React from "react";
import {Table} from "react-bulma-components";

export default function PostRecipe() {
    return (
        <div>
            <form>
                <label className="label" htmlFor="title">Title: </label>
                <input type="text" name="title" placeholder="Recipe Title Here"/>
                <br/>
                <label className="label" htmlFor="info">Info Text: </label>
                <textarea name="info"/>
                <br/>
                <label className="label" htmlFor="instruction">Instructions: </label>
                <textarea name="instruction"/>
                <br/>
                <label className="label" htmlFor="image">Image: </label>
                <input type="file" name="image"/>
                <br/>
                <label className="label" htmlFor="ingredients">Ingredients: </label>
                <button className="button is-primary">Add Ingredient</button>
                <Table>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Amount</th>
                        <th>Measurement</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>ägg</td>
                        <td>3</td>
                        <td>st</td>
                    </tr>
                    </tbody>
                </Table>
                <label className="label" htmlFor="ingredients">Categories: </label>
                <button className="button is-primary">Add Category</button>
            </form>
        </div>
    )
}