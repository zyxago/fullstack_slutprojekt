import React from "react";
import {Table, Modal, Content} from "react-bulma-components";
import Recipe from "../entities/Recipe";
import Ingredient from "../entities/Ingredient";
import {TableRowRecipeIngredients} from "./Recipe";

export default function PostRecipe() {

    const [showInstruction, setShowInstruction] = React.useState(false);
    const [showIngredient, setShowIngredient] = React.useState(false);
    const [showCategory, setShowCategory] = React.useState(false);
    const [recipe, setRecipe] = React.useState(new Recipe());

    function parseInstruction(id) {

    }

    function parseIngredient() {
        const name = document.getElementById("newIngredient").value;
        const amount = document.getElementById("amount").value;
        const measurement = document.getElementById("newMeasurement").value;
        let newRecipe = recipe;
        newRecipe.ingredients.push(new Ingredient(name, amount, measurement));
        setRecipe(newRecipe);
        setShowIngredient(false);
    }

    function parseCategory(id) {

    }

    function AddInstruction() {
        return (
            <Modal show={showInstruction} onClose={() => setShowInstruction(false)}>
                <Modal.Card>
                    <Modal.Card.Head>
                        <Modal.Card.Title>
                            Add Instruction
                        </Modal.Card.Title>
                    </Modal.Card.Head>
                    <Modal.Card.Body>
                        <Content>
                            <label className={"label"} htmlFor={"inputInstruction"}>Instruction: </label>
                            <textarea className="textarea" id="inputInstruction"/>

                            <button className="button is-primary"
                                    onClick={() => parseInstruction("inputInstruction")}>Add
                            </button>
                        </Content>
                    </Modal.Card.Body>
                </Modal.Card>
            </Modal>
        )
    }

    function AddIngredient() {
        return (
            <Modal show={showIngredient} onClose={() => setShowIngredient(false)}>
                <Modal.Card>
                    <Modal.Card.Head>
                        <Modal.Card.Title>
                            Add Ingredient
                        </Modal.Card.Title>
                    </Modal.Card.Head>
                    <Modal.Card.Body>
                        <Content>
                            <label className="label" htmlFor="newIngredient">Ingredient: </label>
                            <input className="input" id="newIngredient" placeholder="New Ingredient..."
                                   list="ingredientList"/>
                            <datalist className="select" id="ingredientList">
                                <option value="Mjölk"/>
                                <option value="Ägg"/>
                                <option value="Salad"/>
                            </datalist>
                            <input className="input" type="number" placeholder="Amount..." step="0.1" min="0"
                                   max="10000" id="amount"/>
                            <input className="input" list="measurementList" id="newMeasurement"/>
                            <datalist className="select" id="measurementList">
                                <option value="st"/>
                                <option value="ml"/>
                                <option value="dl"/>
                                <option value="l"/>
                                <option value="g"/>
                                <option value="hg"/>
                                <option value="kg"/>
                            </datalist>
                            <button className="button is-primary"
                                    onClick={() => parseIngredient()}>Add
                            </button>
                        </Content>
                    </Modal.Card.Body>
                </Modal.Card>
            </Modal>
        )
    }

    function AddCategory() {
        return (
            <Modal show={showCategory} onClose={() => setShowCategory(false)}>
                <Modal.Card>
                    <Modal.Card.Head>
                        <Modal.Card.Title>
                            Add Category
                        </Modal.Card.Title>
                    </Modal.Card.Head>
                    <Modal.Card.Body>
                        <Content>
                            <label className="label" htmlFor="newCategory">Category: </label>
                            <input className="input" list="categoryList" id="newCategory"/>
                            <datalist id="categoryList">
                                <option value="Kött"/>
                                <option value="Fisk"/>
                            </datalist>
                            <button className="button is-primary"
                                    onClick={() => parseCategory("newCategory")}>Add
                            </button>
                        </Content>
                    </Modal.Card.Body>
                </Modal.Card>
            </Modal>
        )
    }

    return (
        <div>
            <AddInstruction/>
            <AddCategory/>
            <AddIngredient/>
            <form onSubmit={(e) => e.preventDefault()}>
                <label className="label" htmlFor="title">Title: </label>
                <input className="input" type="text" name="title" placeholder="Recipe Title Here"/>
                <br/>
                <label className="label" htmlFor="info">Info Text: </label>
                <textarea className="textarea" name="info"/>
                <br/>
                <label className="label" htmlFor="instruction">Instructions: </label>
                <button onClick={() => setShowInstruction(true)} className="button is-primary">Add Instruction:</button>
                <textarea className="textarea" readOnly={true} id="instruction"/>
                <br/>
                <label className="label" htmlFor="image">Image: </label>
                <input type="file" name="image"/>
                <br/>
                <label className="label" htmlFor="ingredients">Ingredients: </label>
                <button onClick={() => setShowIngredient(true)} className="button is-primary">Add Ingredient</button>
                <Table>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Amount</th>
                        <th>Measurement</th>
                    </tr>
                    </thead>
                    <tbody>
                        {recipe.ingredients && <TableRowRecipeIngredients recipe={recipe}/>}
                    </tbody>
                </Table>
                <label className="label" htmlFor="ingredients">Categories: </label>
                <textarea className="textarea" readOnly={true} id="categories"/>
                <button onClick={() => setShowCategory(true)} className="button is-primary">Add Category</button>
            </form>
        </div>
    )
}