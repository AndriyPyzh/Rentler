import Joi from 'joi-browser';
import React from 'react';
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import { login } from "../../services/authService";
import logger from "../../services/logService";
import * as userService from "../../services/userService";
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
        username: Joi.string().alphanum().min(2).max(15).required().label('Username'),
        email: Joi.string().email().required().label('Email'),
        password: Joi.string().min(8).max(30).required().label('Password')
    };

    doSubmit = async () => {
        try {
            const { data: user } = this.state;
            await userService.register(user);
            const { data } = await login(user.username, user.password);
            localStorage.setItem('token', data.access_token);
            window.location = '/confirm';
        } catch (ex) {
            logger.log(ex);
            toast.error(`${ ex.response.data }`);
        }
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

