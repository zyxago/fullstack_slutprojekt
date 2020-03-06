import React from 'react';
import Header from "./Header";
import {BrowserRouter as Router, Route, Switch, Redirect} from "react-router-dom";
import {Content, Container} from "react-bulma-components"
import Footer from "./Footer";
import "../resources/css/app.scss";
import RecipeTable from "./RecipeTable";
import PostRecipe from "./PostRecipe";
import {RecipeFullView} from "./Recipe";

const mainPath = '/fullstack_slutprojekt-1.0';

export default function App() {

    const [selectedRecipe, setSelectedRecipe] = React.useState("");

    return (
        <Container>
            <Router>
                <Header title="Recipe List" mainPath={mainPath}/>
                <Content>
                    <Switch>
                        <Route exact path={mainPath}><RecipeTable mainPath={mainPath} setSelectedRecipe={setSelectedRecipe}/></Route>
                        <Route path={`${mainPath}/postRecipe`}><PostRecipe modifyRecipe={selectedRecipe}/></Route>
                        <Route path={`${mainPath}/${selectedRecipe.title}`}><RecipeFullView recipe={selectedRecipe} mainPath={mainPath}/></Route>
                        <Redirect to={mainPath}/>
                    </Switch>
                </Content>
            </Router>
            <Footer/>
        </Container>
    );
}