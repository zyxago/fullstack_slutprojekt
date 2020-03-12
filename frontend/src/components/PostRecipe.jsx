import React from "react";
import {Table, Modal, Content, Box} from "react-bulma-components";
import Recipe from "../entities/Recipe";
import Ingredient from "../entities/Ingredient";
import {ListRecipeCategories, ListRecipeInstructions, TableRowRecipeIngredients} from "./Recipe";
import {postRecipe, updateRecipe} from "../resources/api/recipe";
import {getCategories} from "../resources/api/category";
import {getIngredients, getMeasurements} from "../resources/api/ingredient";

let imageData = undefined;

export default function PostRecipe({modifyRecipe, user, mainPath}) {

    const [categories, setCategories] = React.useState([]);
    const [ingredients, setIngredients] = React.useState([]);
    const [measurements, setMeasurements] = React.useState([]);

    const [showAddInstruction, setShowAddInstruction] = React.useState(false);
    const [showRemoveInstruction, setShowRemoveInstruction] = React.useState(false);

    const [showAddIngredient, setShowAddIngredient] = React.useState(false);
    const [showRemoveIngredient, setShowRemoveIngredient] = React.useState(false);

    const [showAddCategory, setShowAddCategory] = React.useState(false);
    const [showRemoveCategory, setShowRemoveCategory] = React.useState(false);

    const [recipe, setRecipe] = React.useState(modifyRecipe || new Recipe());

    React.useEffect(() => {
        getCategories(setCategories, mainPath)
    }, []);
    React.useEffect(() => {
        getIngredients(setIngredients, mainPath)
    }, []);
    React.useEffect(() => {
        getMeasurements(setMeasurements, mainPath)
    }, []);

    function checkInput(e) {
        let element = e.target;
        if (element.value !== "") {
            element.classList.remove("is-danger");
        } else {
            element.classList.add("is-danger");
        }
    }

    function parseInstruction() {
        const text = document.getElementById("inputInstruction").value;
        let newRecipe = recipe;
        newRecipe.instructions.push(text);
        setRecipe(newRecipe);
        setShowAddInstruction(false);
    }

    function parseIngredient() {
        const name = document.getElementById("newIngredient").value;
        const amount = document.getElementById("amount").valueAsNumber;
        const measurement = document.getElementById("newMeasurement").value;
        let newRecipe = recipe;
        newRecipe.ingredients.push(new Ingredient(name, amount, measurement));
        setRecipe(newRecipe);
        setShowAddIngredient(false);
    }

    function parseCategory() {
        const name = document.getElementById("newCategory").value;
        let newRecipe = recipe;
        newRecipe.categories.push(name);
        setRecipe(newRecipe);
        setShowAddCategory(false);
    }

    function AddInstruction() {
        return (
            <Modal show={showAddInstruction} onClose={() => setShowAddInstruction(false)}>
                <Modal.Card>
                    <Modal.Card.Head>
                        <Modal.Card.Title>
                            Add Instruction
                        </Modal.Card.Title>
                    </Modal.Card.Head>
                    <Modal.Card.Body>
                        <Content>
                            <label className={"label"} htmlFor={"inputInstruction"}>Instruction: </label>
                            <textarea className="textarea is-danger" id="inputInstruction" onChange={checkInput}/>

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
            <Modal show={showAddIngredient} onClose={() => setShowAddIngredient(false)}>
                <Modal.Card>
                    <Modal.Card.Head>
                        <Modal.Card.Title>
                            Add Ingredient
                        </Modal.Card.Title>
                    </Modal.Card.Head>
                    <Modal.Card.Body>
                        <Content>
                            <label className="label" htmlFor="newIngredient">Ingredient: </label>
                            <input onChange={checkInput} className="input is-danger" id="newIngredient"
                                   placeholder="New Ingredient..."
                                   list="ingredientList"/>
                            <datalist id="ingredientList">
                                <FillDatalist list={ingredients}/>
                            </datalist>
                            <input onChange={checkInput} className="input is-danger" type="number"
                                   placeholder="Amount..." step="0.1" min="0"
                                   max="10000" id="amount"/>
                            <input onChange={checkInput} className="input is-danger" list="measurementList"
                                   id="newMeasurement"/>
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
            <Modal show={showAddCategory} onClose={() => setShowAddCategory(false)}>
                <Modal.Card>
                    <Modal.Card.Head>
                        <Modal.Card.Title>
                            Add Category
                        </Modal.Card.Title>
                    </Modal.Card.Head>
                    <Modal.Card.Body>
                        <Content>
                            <label className="label" htmlFor="newCategory">Category: </label>
                            <input onChange={checkInput} className="input is-danger" list="categoryList"
                                   id="newCategory"/>
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

    function RemoveInstruction() {
        return (
            <Modal show={showRemoveInstruction} onClose={() => setShowRemoveInstruction(false)}>
                <Modal.Card>
                    <Modal.Card.Head>
                        <Modal.Card.Title>
                            Remove Instruction
                        </Modal.Card.Title>
                    </Modal.Card.Head>
                    <Modal.Card.Body>
                        <Content>
                            <ul>
                                {recipe.instructions.map((instruction, index) => {
                                    return <li key={index}><input type="checkbox"
                                                                  id={`instruction${index}`}/>{instruction}</li>
                                })}
                            </ul>
                            <button className="button" onClick={() => removeMarkedUnits("instruction")}>Remove marked
                                Instructions
                            </button>
                        </Content>
                    </Modal.Card.Body>
                </Modal.Card>
            </Modal>
        )
    }

    function RemoveIngredient() {
        return (
            <Modal show={showRemoveIngredient} onClose={() => setShowRemoveIngredient(false)}>
                <Modal.Card>
                    <Modal.Card.Head>
                        <Modal.Card.Title>
                            Remove Ingredients
                        </Modal.Card.Title>
                    </Modal.Card.Head>
                    <Modal.Card.Body>
                        <Content>
                            <ul>
                                {recipe.ingredients.map((ingredient, index) => {
                                    return <li key={index}><input type="checkbox"
                                                                  id={`ingredient${index}`}/>{ingredient.name}</li>
                                })}
                            </ul>
                            <button className="button" onClick={() => removeMarkedUnits("ingredient")}>Remove marked
                                Ingredients
                            </button>
                        </Content>
                    </Modal.Card.Body>
                </Modal.Card>
            </Modal>
        )
    }

    function RemoveCategory() {
        return (
            <Modal show={showRemoveCategory} onClose={() => setShowRemoveCategory(false)}>
                <Modal.Card>
                    <Modal.Card.Head>
                        <Modal.Card.Title>
                            Remove Categories
                        </Modal.Card.Title>
                    </Modal.Card.Head>
                    <Modal.Card.Body>
                        <Content>
                            <ul>
                                {recipe.categories.map((category, index) => {
                                    return <li key={index}><input type="checkbox" id={`category${index}`}/>{category}
                                    </li>
                                })}
                            </ul>
                            <button className="button" onClick={() => removeMarkedUnits("category")}>Remove marked
                                Categories
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

    function removeMarkedUnits(identifier) {
        if (identifier === "instruction") {
            for (let i = 0; i < recipe.instructions.length; i++) {
                if (document.getElementById(`${identifier}${i}`).checked) {
                    recipe.instructions.splice(i, 1);
                }
            }
            setShowRemoveInstruction(false);
        } else if (identifier === "ingredient") {
            for (let i = 0; i < recipe.ingredients.length; i++) {
                if (document.getElementById(`${identifier}${i}`).checked) {
                    recipe.ingredients.splice(i, 1);
                }
            }
            setShowRemoveIngredient(false);
        } else if (identifier === "category") {
            for (let i = 0; i < recipe.categories.length; i++) {
                if (document.getElementById(`${identifier}${i}`).checked) {
                    recipe.categories.splice(i, 1);
                }
            }
            setShowRemoveCategory(false);
        }
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
            updateRecipe(recipe, mainPath);
        } else {
            postRecipe(recipe, mainPath);
        }
    }

    function openFile(e) {
        let file = e.target.files[0];
        let reader = new FileReader();
        const fileName = document.getElementsByClassName("file-name")[0];
        fileName.textContent = file.name;
        reader.onload = function () {
            imageData = reader.result;
        };
        reader.readAsArrayBuffer(file);
    }

    return (
        <Box>
            <AddInstruction/>
            <RemoveInstruction/>
            <AddCategory/>
            <RemoveCategory/>
            <AddIngredient/>
            <RemoveIngredient/>
            <form onSubmit={(e) => e.preventDefault()}>
                <label className="label" htmlFor="title">Title: </label>
                <input onChange={checkInput} className="input is-danger" type="text" id="title"
                       placeholder={recipe.title}/>
                <br/>
                <label className="label" htmlFor="info">Info Text: </label>
                <textarea onChange={checkInput} className="textarea is-danger" id="info"
                          placeholder={recipe.information}/>

                <br/>
                <label className="label" htmlFor="instruction">Instructions: </label>
                <button onClick={() => setShowAddInstruction(true)} className="button is-primary">Add Instruction
                </button>
                <button onClick={() => setShowRemoveInstruction(true)} className="button is-primary">Remove
                    Instructions
                </button>
                {recipe.instructions && <Box>
                    <ol><ListRecipeInstructions recipe={recipe}/></ol>
                </Box>}

                <br/>
                <div id="imageInput" className="file has-name">
                    <label className="file-label" htmlFor="image">
                        <input className="file-input" onChange={openFile} type="file" id="image" accept="image/*"/>
                        <span className="file-cta">
                            <span className="file-label">
                                Choose a file...
                            </span>
                        </span>
                        <span className="file-name">
                            File name
                        </span>
                    </label>
                </div>

                <br/>
                <label className="label" htmlFor="ingredients">Ingredients: </label>
                <button onClick={() => setShowAddIngredient(true)} className="button is-primary">Add Ingredient</button>
                <button onClick={() => setShowRemoveIngredient(true)} className="button is-primary">Remove
                    Ingredients
                </button>
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
                {recipe.categories && <Box>
                    <li><ListRecipeCategories recipe={recipe}/></li>
                </Box>}
                <button onClick={() => setShowAddCategory(true)} className="button is-primary">Add Category</button>
                <button onClick={() => setShowRemoveCategory(true)} className="button is-primary">Remove
                    Categories
                </button>
                <br/>
                <button onClick={() => sendRecipe()} id="submitButton" className="button is-primary">Post Recipe
                </button>
            </form>
        </Box>
    )
}