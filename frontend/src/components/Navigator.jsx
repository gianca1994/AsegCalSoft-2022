import React from "react";
import {Container, Nav, Navbar} from "react-bootstrap";

const Navigator = () => {
    return (
        <Navbar bg="dark" variant="dark">
            <Container>
                <Navbar.Brand href="/">Home</Navbar.Brand>
                <Nav>
                    <Nav.Link className="me-4" href="/register">
                        Register Attendee
                    </Nav.Link>
                    <Nav.Link className="me-4" href="/attendee">
                        Search Attendee
                    </Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    );
};

export default Navigator;