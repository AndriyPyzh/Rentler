import Joi from 'joi-browser';
import React from 'react';
import { Link } from "react-router-dom";
import Form from "../shared/form";
import AuthPage from "./authPage";

class SignupForm extends Form {
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
        password: Joi.string().min(8).max(30).required().label('Password')
    };

    doSubmit = () => {
        console.log('submitted');
        console.log(this.state.data);
        this.props.history.replace('/confirm');
    };

    render() {
        return (
            <AuthPage
                title={ "Sign Up" }
                footer={ <span>Already have Rentberry account? <Link to={ "/login" }
                                                                     className="text-purple">Log in</Link></span> }
                { ...this.props }
            >
                <form onSubmit={ this.handleSubmit }>
                    <div className="card-body" style={ { padding: 40 } }>
                        { super.renderInput("username", "Username", "enter username") }
                        { super.renderInput("email", "Email", "enter email", "email") }
                        { super.renderInput("password", "Password", "enter password", "password") }
                    </div>
                    { super.renderButton("Sign Up", "btn btn-primary auth-button") }
                </form>
            </AuthPage>

        );
    }
}

export default SignupForm;

