import React from "react";
import {Navbar} from "react-bulma-components";
import {Link} from "react-router-dom";
import {SignIn, SignOut} from "./Authentication";
import "../resources/css/nav.scss";

export default function Nav(props) {
    return (
        <Navbar>
            <Navbar.Menu className="is-active">
                <Navbar.Container position="end">
                    <Navbar.Item>
                        <Link to={`/`}>Home</Link>
                    </Navbar.Item>
                    {props.authorized && <Navbar.Item>
                        <Link to={`/postRecipe`}>Post Recipe</Link>
                    </Navbar.Item>}
                    {props.authorized ?
                        <Navbar.Item>
                            <SignOut setUser={props.setUser}/>
                        </Navbar.Item>
                        : <>
                            <Navbar.Item>
                                <SignIn/>
                            </Navbar.Item>
                        </>}
                </Navbar.Container>
            </Navbar.Menu>
        </Navbar>
    )
}