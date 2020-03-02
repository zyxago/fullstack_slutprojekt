import React from 'react';
import Header from "./Header";
import {BrowserRouter as Router, Route, Switch, Redirect} from "react-router-dom";
import {Content} from "react-bulma-components"
import Footer from "./Footer";
import "../resources/css/app.scss";
import RecipeTable from "./RecipeTable";
import PostRecipe from "./PostRecipe";

export default function App() {
    const mainPath = '/fullstack_slutprojekt-1.0';

    return (
        <div className="App">
            <Router>
                <Header title="Recipe List" mainPath={mainPath}/>
                <Content>
                    <Switch>
                        <Route exact path={mainPath}><RecipeTable/></Route>
                        <Route path={`${mainPath}/postRecipe`}><PostRecipe/></Route>
                        <Redirect to={mainPath}/>
                    </Switch>
                </Content>
            </Router>
            <Footer/>
        </div>
    );
}