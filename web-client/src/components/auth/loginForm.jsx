import Joi from 'joi-browser';
import React from 'react';
import { Link } from "react-router-dom";
import Form from "../shared/form";
import AuthPage from "./authPage";

class LoginForm extends Form {
    state = {
        data: {
            username: '',
            password: ''
        },
        errors: {},
        showMessages: {}
    };

    schema = {
        username: Joi.string().alphanum().min(4).max(15).required().label('Username'),
        password: Joi.string().min(8).max(30).required().label('Password')
    };

    doSubmit = () => {
        console.log('submitted');
        console.log(this.state.data);
        this.props.history.replace("/apartments");
    };

    render() {
        return (
            <AuthPage
                title={ "Log In" }
                footer={ <span>Don’t have Rentler account? <Link to={ "/signup" } className="text-purple">Sign up</Link></span> }
                { ...this.props }
            >
                <form onSubmit={ this.handleSubmit }>
                    <div className="card-body" style={ { padding: 40 } }>
                        { super.renderInput("username", "Username", "enter username") }
                        { super.renderInput("password", "Password", "enter password", "password") }
                        <div className="text-right " style={ { fontSize: 14 } }>
                            <Link to="/reset-password" className="text-purple">
                                Forgot your password?
                            </Link>
                        </div>
                    </div>
                    { super.renderButton("Log In", "btn btn-primary auth-button") }
                </form>
            </AuthPage>
        );
    }
}

export default LoginForm;

