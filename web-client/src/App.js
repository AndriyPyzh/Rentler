import React, { Component } from 'react';
import { Redirect, Route, Switch } from "react-router-dom";
import './App.css';
import AddApartment from "./components/apartments/addApartment";
import Apartments from "./components/apartments/apartments";
import LoginForm from "./components/auth/loginForm";
import SignupForm from "./components/auth/signupForm";
import Home from "./components/pages/home";
import NotFound from "./components/pages/notFound";
import Footer from "./components/shared/footer";
import NavBar from "./components/shared/navBar";

class App extends Component {
    state = {
        navbar: true,
        footer: true
    };

    componentDidMount() {
        console.log("mount app");
    }

    hideNavbar = () => {
        this.setState({ navbar: false });
    };

    showNavbar = () => {
        this.setState({ navbar: true });
    };

    hideFooter = () => {
        this.setState({ footer: false });
    };

    showFooter = () => {
        this.setState({ footer: true });
    };


    render() {
        const { navbar, footer } = this.state;

        return (
            <React.Fragment>
                { navbar && <NavBar/> }
                <main className="container">
                    <Switch>
                        <Route path="/apartments" component={ Apartments }/>
                        <Route path="/add-apartment" component={ AddApartment }/>
                        <Route path="/login"
                               render={ props =>
                                   <LoginForm { ...props }
                                              hideNavbar={ this.hideNavbar }
                                              showNavbar={ this.showNavbar }
                                              hideFooter={ this.hideFooter }
                                              showFooter={ this.showFooter }
                                   /> }/>
                        <Route path="/signup"
                               render={ props =>
                                   <SignupForm { ...props }
                                               hideNavbar={ this.hideNavbar }
                                               showNavBar={ this.showNavbar }
                                               hideFooter={ this.hideFooter }
                                               showFooter={ this.showFooter }
                                   /> }/>/>
                        <Route path="/not-found" component={ NotFound }/>
                        <Route path="/" exact component={ Home }/>
                        <Redirect to="/not-found"/>
                    </Switch>
                </main>
                { footer && <Footer/> }
            </React.Fragment>
        );
    }
}

export default App;
