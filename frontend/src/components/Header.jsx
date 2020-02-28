import React from "react";
import {Heading} from "react-bulma-components";
import "../resources/css/header.scss";
import Nav from "./Nav";

export default function Header({title, authorized}) {
    return <>
        <Heading size={2}>
            {title}
        </Heading>
        <Nav authorized={authorized}/>
    </>
}