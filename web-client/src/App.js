import React, { Component } from 'react';
import { Redirect, Route, Switch } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import AddApartment from "./components/apartments/addApartment";
import Apartments from "./components/apartments/apartments";
import ConfirmForm from "./components/auth/confirmForm";
import LoginForm from "./components/auth/loginForm";
import ResetPassword from "./components/auth/resetPassword";
import SignupForm from "./components/auth/signupForm";
import Home from "./components/pages/home";
import NotFound from "./components/pages/notFound";
import Footer from "./components/shared/footer";
import NavBar from "./components/shared/navBar";
import "react-toastify/dist/ReactToastify.css";
import './App.css';

class App extends Component {
    state = {
        navbar: true,
        footer: true
    };

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
        const clearFuncs = {
            hideNavbar: this.hideNavbar,
            showNavbar: this.showNavbar,
            hideFooter: this.hideFooter,
            showFooter: this.showFooter
        };

        return (
            <React.Fragment>
                <ToastContainer position="top-center" pauseOnHover/>
                { navbar && <NavBar/> }
                <main className="container">
                    <Switch>
                        <Route path="/apartments" component={ Apartments }/>
                        <Route path="/add-apartment" component={ AddApartment }/>
                        <Route path="/login"
                               render={ props => <LoginForm { ...props }{ ...clearFuncs }/> }/>
                        <Route path="/signup"
                               render={ props => <SignupForm { ...props }{ ...clearFuncs }/> }/>
                        <Route path="/confirm"
                               render={ props => <ConfirmForm { ...props }{ ...clearFuncs }/> }/>
                        <Route path="/reset-password"
                               render={ props => <ResetPassword { ...props }{ ...clearFuncs }/> }/>
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
