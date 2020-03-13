import React from "react";
import {Heading, Box} from "react-bulma-components";
import Nav from "./Nav";
import "../resources/css/header.scss";

export default function Header({title, signedIn, setUser}) {
    return (
        <div className="header">
            <Box>
                <Heading size={2}>
                    {title}
                </Heading>
                <Nav authorized={signedIn} setUser={setUser}/>
            </Box>
        </div>
    )
}