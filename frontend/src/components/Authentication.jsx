import React from "react";
import {Content, Modal} from "react-bulma-components";

export function SignIn() {

    const [show, setShow] = React.useState(false);

    return <><span onClick={()=>setShow(true)}>Sign In</span>
        <Modal show={show} onClose={() => setShow(false)}>
            <Modal.Card>
                <Modal.Card.Head>
                    <Modal.Card.Title>
                        Sign In
                    </Modal.Card.Title>
                </Modal.Card.Head>
                <Modal.Card.Body>
                    <Content>
                        <a href="https://github.com/login/oauth/authorize?client_id=3ddec322c9443ab7553b" className="button">Github</a>
                    </Content>
                </Modal.Card.Body>
            </Modal.Card>
        </Modal>
    </>
}

export function SignOut({setUser}) {

    function signOut(){
        window.localStorage.removeItem("token");
        setUser(undefined);
    }

    return <span onClick={()=>signOut()}>Sign Out</span>
}