import React, { Component } from 'react';
import { Redirect, Route, Switch } from "react-router-dom";
import './App.css';
import AddApartment from "./components/addApartment";
import Apartments from "./components/apartments";
import Home from "./components/home";
import LoginForm from "./components/loginForm";
import NavBar from "./components/navBar";
import NotFound from "./components/notFound";
import SignupForm from "./components/signupForm";

class App extends Component {
    render() {
        return (
            <React.Fragment>
                <NavBar/>
                <main className="container">
                    <Switch>
                        <Route path="/apartments" component={ Apartments }/>
                        <Route path="/add-apartment" component={ AddApartment }/>
                        <Route path="/login" component={ LoginForm }/>
                        <Route path="/signup" component={ SignupForm }/>
                        <Route path="/not-found" component={ NotFound }/>
                        <Route path="/" exact component={ Home }/>
                        <Redirect to="/not-found"/>
                    </Switch>
                </main>
            </React.Fragment>
        );
    }
}

export default App;
