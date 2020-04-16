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
import WsListener from "../resources/websocket/WsListener";

const mainPath = "/slutprojekt-erikh";

export default function App() {

    const [selectedRecipe, setSelectedRecipe] = React.useState("");
    const [user, setUser] = React.useState(undefined);
    const [refresh, setRefresh] = React.useState(0);
    const ws = React.useRef({});

    //Eftersom detta bara skall köras en gång behöver du skicka in en tom
    //list som andra argument till useEffect för att indikera att effekten
    //inte är har några beroenden till stat eller props.
    React.useEffect(() => {
        ws.current = new WsListener(mainPath);
        ws.current.ws.addEventListener("message", (e) => {
            setRefresh(oldValue => oldValue + 1);
        });
    }, []);

    if (window.location.href.includes("?code=") && user === undefined) {
        console.log(window.location.href);
        let path = window.location.href;
        let code = path.substring(path.indexOf('=') + 1);
        getToken(code, setUser);
        window.history.replaceState({}, document.title, "./");
    }

    return (
        <Container>
            <Router basename={mainPath}>
                <Header title="Recipe List" signedIn={user} setUser={setUser}/>
                <Content>
                    <Switch>
                        <Route exact path="/"><RecipeTable wsValue={refresh} setSelectedRecipe={setSelectedRecipe} signedIn={user}/></Route>
                        {user &&
                        <Route path={`/postRecipe`}><PostRecipe user={user}/></Route>}
                        {user && <Route path={`/editRecipe`}><PostRecipe modifyRecipe={selectedRecipe} user={user}/></Route>}
                        <Route path={`/${selectedRecipe.title}`}><RecipeFullView recipe={selectedRecipe} user={user}/></Route>
                        <Redirect to="/"/>
                    </Switch>
                </Content>
            </Router>
            <Footer/>
        </Container>
    );
}