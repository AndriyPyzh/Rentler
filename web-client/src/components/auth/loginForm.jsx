import Joi from 'joi-browser';
import React from 'react';
import { Link } from "react-router-dom";
import Form from "../shared/form";
import AuthPage from "./authPage";

class LoginForm extends Form {
    state = {
        data: {
            username: '',
            email: '',
            password: ''
        },
        errors: {},
        showMessages: {}
    };

    schema = {
        username: Joi.string().alphanum().min(4).max(15).required().label('Username'),
        email: Joi.string().email().required().label('Email'),
        password: Joi.string().alphanum().min(8).max(30).required().label('Password')
    };

    doSubmit = () => {
        console.log('submitted');
        console.log(this.state.data);
    };

    render() {
        const { data, errors, showMessages } = this.state;

        return (
            <AuthPage
                title={ "Log In" }
                footer={ <span>Don’t have Rentler account? <Link to={ "/signup" }>Sign up</Link></span> }
                hideNavbar={ this.props.hideNavbar }
                showNavbar={ this.props.showNavbar }
                hideFooter={ this.props.hideFooter }
                showFooter={ this.props.showFooter }
            >
                <form onSubmit={ this.handleSubmit }>
                    <div className="card-body" style={ { padding: 40 } }>
                        { super.renderInput("username", "Username", "enter username") }
                        { super.renderInput("email", "Email", "enter email", "email") }
                        { super.renderInput("password", "Password", "enter password", "password") }
                    </div>
                    { super.renderButton("Log In", "btn btn-primary auth-button") }
                </form>
            </AuthPage>

        );
    }
}

export default LoginForm;

