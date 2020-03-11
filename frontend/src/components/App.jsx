import React from 'react';
import Header from "./Header";
import {BrowserRouter as Router, Route, Switch, Redirect} from "react-router-dom";
import {Content, Container} from "react-bulma-components"
import Footer from "./Footer";
import "../resources/css/app.scss";
import RecipeTable from "./RecipeTable";
import PostRecipe from "./PostRecipe";
import {RecipeFullView} from "./Recipe";
import {getToken} from "../resources/api/authentication";

const mainPath = '/fullstack_slutprojekt-1.0';

export default function App() {

    const [selectedRecipe, setSelectedRecipe] = React.useState("");
    const [user, setUser] = React.useState(undefined);

    if(window.location.href.includes("?code=") && user === undefined){
        console.log(window.location.href);
        let path = window.location.href;
        let code = path.substring(path.indexOf('=')+1);
        getToken(code, setUser);
        window.history.replaceState({}, document.title, "./");
    }

    return (
        <Container>
            <Router>
                <Header title="Recipe List" mainPath={mainPath} signedIn={user} setUser={setUser}/>
                <Content>
                    <Switch>
                        <Route exact path={mainPath}><RecipeTable mainPath={mainPath} setSelectedRecipe={setSelectedRecipe} signedIn={user}/></Route>
                        {user && <Route path={`${mainPath}/postRecipe`}><PostRecipe user={user}/></Route>}
                        {user && <Route path={`${mainPath}/editRecipe`}><PostRecipe modifyRecipe={selectedRecipe} user={user}/></Route>}
                        <Route path={`${mainPath}/${selectedRecipe.title}`}><RecipeFullView recipe={selectedRecipe} mainPath={mainPath} user={user}/></Route>
                        <Redirect to={mainPath}/>
                    </Switch>
                </Content>
            </Router>
            <Footer/>
        </Container>
    );
}