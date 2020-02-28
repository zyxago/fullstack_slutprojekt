import React from 'react';
import Header from "./Header";
import {BrowserRouter as Router, Route} from "react-router-dom";
import {Content} from "react-bulma-components"
import Footer from "./Footer";
import "../resources/css/app.scss";
import RecipeTable from "./RecipeTable";
import PostRecipe from "./PostRecipe";

export default function App() {
    return (
        <div className="App">
            <Router>
                <Header title="Recipe List"/>
                <Content>
                    <Route exact path="/"><RecipeTable/></Route>
                    <Route path="/postRecipe"><PostRecipe/></Route>
                </Content>
            </Router>
            <Footer/>
        </div>
    );
}