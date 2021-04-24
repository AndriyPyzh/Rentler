import React, { Component } from 'react';
import { Redirect, Route, Switch } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import './App.css';
import AddApartment from "./components/apartments/addApartment";
import Apartments from "./components/apartments/apartments";
import Applications from "./components/apartments/applications";
import Properties from "./components/apartments/properties";
import AddInfoForm from "./components/auth/addInfoForm";
import LoginForm from "./components/auth/loginForm";
import ResetPassword from "./components/auth/resetPassword";
import SignupForm from "./components/auth/signupForm";
import Home from "./components/pages/home";
import NotFound from "./components/pages/notFound";
import Profile from "./components/pages/profile";
import Footer from "./components/shared/footer";
import NavBar from "./components/shared/navBar";
import authService from "./services/authService";

class App extends Component {
    state = {
        navbar: true,
        footer: true
    };

    componentDidMount() {
        const user = authService.getCurrentUser();
        this.setState({ user });
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
        const clearFuncs = {
            hideNavbar: this.hideNavbar,
            showNavbar: this.showNavbar,
            hideFooter: this.hideFooter,
            showFooter: this.showFooter
        };

        return (
            <React.Fragment>
                <ToastContainer position="top-center" pauseOnHover/>
                { navbar && <NavBar user={ this.state.user }/> }
                <main className="container">
                    <Switch>
                        <Route path="/apartments" component={ Apartments }/>
                        <Route path="/add-apartment" component={ AddApartment }/>
                        <Route path="/profile" component={ Profile }/>
                        <Route path="/applications" component={ Applications }/>
                        <Route path="/properties" component={ Properties }/>
                        <Route path="/login"
                               render={ props => <LoginForm { ...props }{ ...clearFuncs }/> }/>
                        <Route path="/signup"
                               render={ props => <SignupForm { ...props }{ ...clearFuncs }/> }/>
                        <Route path="/confirm"
                               render={ props => <AddInfoForm { ...props }{ ...clearFuncs }/> }/>
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
