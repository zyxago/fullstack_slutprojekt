import React from "react";
import {Table, Modal, Content} from "react-bulma-components";
import Recipe from "../entities/Recipe";
import Ingredient from "../entities/Ingredient";
import {TableRowRecipeIngredients} from "./Recipe";
import {postRecipe} from "../resources/api/recipe";
import {getCategories} from "../resources/api/category";
import {getIngredients, getMeasurements} from "../resources/api/ingredient";

let imageData;

export default function PostRecipe() {

    const [categories, setCategories] = React.useState([]);
    const [ingredients, setIngredients] = React.useState([]);
    const [measurements, setMeasurements] = React.useState([]);
    const [showInstruction, setShowInstruction] = React.useState(false);
    const [showIngredient, setShowIngredient] = React.useState(false);
    const [showCategory, setShowCategory] = React.useState(false);
    const [recipe, setRecipe] = React.useState(new Recipe());

    React.useEffect(() => getCategories(setCategories), []);
    React.useEffect(() => getIngredients(setIngredients), []);
    React.useEffect(() => getMeasurements(setMeasurements), []);

    function parseInstruction() {
        const text = document.getElementById("inputInstruction").value;
        let newRecipe = recipe;
        newRecipe.instructions.push(text);
        setRecipe(newRecipe);
        setShowInstruction(false);
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

    function parseCategory() {
        const name = document.getElementById("newCategory").value;
        let newRecipe = recipe;
        newRecipe.categories.push(name);
        setRecipe(newRecipe);
        setShowCategory(false);
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
                            <datalist id="ingredientList">
                                <FillDatalist list={ingredients}/>
                            </datalist>
                            <input className="input" type="number" placeholder="Amount..." step="0.1" min="0"
                                   max="10000" id="amount"/>
                            <input className="input" list="measurementList" id="newMeasurement"/>
                            <datalist id="measurementList">
                                <FillDatalist list={measurements}/>
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
                                <FillDatalist list={categories}/>
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

    function RecipeInstructionsToText() {
        let output = "";
        recipe.instructions.map((instruction) => output += `${instruction}\n`);
        let textarea = <textarea className="textarea" readOnly={true} id="instruction" value={output}/>;
        return textarea
    }

    function RecipeCategoriesToText() {
        let output = "";
        recipe.categories.map((category) => output += `${category}\n`);
        let textarea = <textarea className="textarea" readOnly={true} id="categories" value={output}/>;
        return textarea
    }

    function FillDatalist({list}) {
        return list.map((item) => {
            return <option>{item}</option>
        });
    }

    function sendRecipe() {
        recipe.title = document.getElementById("title").value;
        recipe.information = document.getElementById("info").value;
        recipe.image = imageData;
        postRecipe(recipe);
    }

    function openFile(e) {
        let file = e.target.files[0];
        let reader = new FileReader();
        reader.onload = function(){
            imageData = reader.result;
        };
        reader.readAsArrayBuffer(file);
    }

    return (
        <div>
            <AddInstruction/>
            <AddCategory/>
            <AddIngredient/>
            <form onSubmit={(e) => e.preventDefault()}>
                <label className="label" htmlFor="title">Title: </label>
                <input className="input" type="text" id="title" placeholder="Recipe Title Here"/>

                <br/>
                <label className="label" htmlFor="info">Info Text: </label>
                <textarea className="textarea" id="info"/>

                <br/>
                <label className="label" htmlFor="instruction">Instructions: </label>
                <button onClick={() => setShowInstruction(true)} className="button is-primary">Add Instruction:</button>
                {recipe.instructions && <RecipeInstructionsToText/>}

                <br/>
                <label className="label" htmlFor="image">Image: </label>
                <input onChange={openFile} type="file" id="image" accept="image/*"/>

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
                {recipe.categories && <RecipeCategoriesToText/>}
                <button onClick={() => setShowCategory(true)} className="button is-primary">Add Category</button>

                <br/>
                <button onClick={() => sendRecipe()} className="button is-primary">Post Recipe</button>
            </form>
        </div>
    )
}