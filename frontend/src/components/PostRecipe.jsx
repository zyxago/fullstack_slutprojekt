import React from "react";
import {Table, Modal, Content, Box} from "react-bulma-components";
import Recipe from "../entities/Recipe";
import Ingredient from "../entities/Ingredient";
import {ListRecipeCategories, ListRecipeInstructions, TableRowRecipeIngredients} from "./Recipe";
import {postRecipe, updateRecipe} from "../resources/api/recipe";
import {getCategories} from "../resources/api/category";
import {getIngredients, getMeasurements} from "../resources/api/ingredient";

let imageData = undefined;

export default function PostRecipe({modifyRecipe, user}) {

    const [categories, setCategories] = React.useState([]);
    const [ingredients, setIngredients] = React.useState([]);
    const [measurements, setMeasurements] = React.useState([]);
    const [showInstruction, setShowInstruction] = React.useState(false);
    const [showIngredient, setShowIngredient] = React.useState(false);
    const [showCategory, setShowCategory] = React.useState(false);
    const [recipe, setRecipe] = React.useState(modifyRecipe || new Recipe());

    React.useEffect(() => {getCategories(setCategories)}, []);
    React.useEffect(() => {getIngredients(setIngredients)}, []);
    React.useEffect(() => {getMeasurements(setMeasurements)}, []);


    function parseInstruction() {
        const text = document.getElementById("inputInstruction").value;
        let newRecipe = recipe;
        newRecipe.instructions.push(text);
        setRecipe(newRecipe);
        setShowInstruction(false);
    }

    function parseIngredient() {
        const name = document.getElementById("newIngredient").value;
        const amount = document.getElementById("amount").valueAsNumber;
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

    function FillDatalist({list}) {
        return list.map((item) => {
            return <option>{item}</option>
        });
    }

    function sendRecipe() {
        document.getElementById("submitButton").disabled = true;
        let title = document.getElementById("title");
        let information = document.getElementById("info");
        recipe.title = title.value !== title.placeholder && title.value !== "" ? title.value : title.placeholder;
        recipe.information = information.value !== information.placeholder && information.value !== "" ? information.value : information.placeholder;
        recipe.writerId = user.id;
        if (imageData !== undefined) {
            recipe.image = imageData;
        }
        if (recipe.id) {
            updateRecipe(recipe);
        } else {
            postRecipe(recipe);
        }
    }

    function openFile(e) {
        let file = e.target.files[0];
        let reader = new FileReader();
        reader.onload = function () {
            imageData = reader.result;
        };
        reader.readAsArrayBuffer(file);
    }

    return (
        <Box>
            <AddInstruction/>
            <AddCategory/>
            <AddIngredient/>
            <form onSubmit={(e) => e.preventDefault()}>
                <label className="label" htmlFor="title">Title: </label>
                <input className="input" type="text" id="title" placeholder={recipe.title}/>
                <br/>
                <label className="label" htmlFor="info">Info Text: </label>
                <textarea className="textarea" id="info" placeholder={recipe.information}/>

                <br/>
                <label className="label" htmlFor="instruction">Instructions: </label>
                <button onClick={() => setShowInstruction(true)} className="button is-primary">Add Instruction:</button>
                {recipe.instructions && <Box><ol><ListRecipeInstructions recipe={recipe}/></ol></Box>}

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
                {recipe.categories && <Box><li><ListRecipeCategories recipe={recipe}/></li></Box>}
                <button onClick={() => setShowCategory(true)} className="button is-primary">Add Category</button>

                <br/>
                <button onClick={() => sendRecipe()} id="submitButton" className="button is-primary">Post Recipe</button>
            </form>
        </Box>
    )
}