import React from "react";
import {Navbar} from "react-bulma-components";
import {Link} from "react-router-dom";
import {SignIn, SignOut, SignUp} from "./Authentication";

export default function Nav(props){
    return (
        <Navbar>
            <Navbar.Menu className="is-active">
                <Navbar.Container position="end">
                    <Navbar.Item>
                        <Link to={`${props.mainPath}/postRecipe`}>Post Recipe</Link>
                    </Navbar.Item>
                    {props.authorized ?
                        <Navbar.Item>
                            <SignOut/>
                        </Navbar.Item>
                        : <>
                            <Navbar.Item>
                                <SignIn/>
                            </Navbar.Item>
                            <Navbar.Item>
                                <SignUp/>
                            </Navbar.Item>
                        </>}
                </Navbar.Container>
            </Navbar.Menu>
        </Navbar>
    )
}