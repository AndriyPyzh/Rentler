import Joi from 'joi-browser';
import React from 'react';
import { Link } from "react-router-dom";
import Form from "../shared/form";
import AuthPage from "./authPage";

class ConfirmForm extends Form {
    state = {
        data: {
            firstname: '',
            lastname: '',
            phone: ''
        },
        errors: {},
        showMessages: {}
    };

    schema = {
        firstname: Joi.string().regex(/^[a-zA-Z]*$/).max(20).required().label('First Name'),
        lastname: Joi.string().regex(/^[a-zA-Z]*$/).max(20).required().label('Last Name'),
        phone: Joi.string().regex(/^\+(?:[0-9] ?){6,14}[0-9]$/).required().label('Phone Number')
    };

    doSubmit = () => {
        console.log('submitted');
        console.log(this.state.data);
        this.props.history.replace("/apartments")
    };

    render() {
        return (
            <AuthPage
                title={ "Confirm Information" }
                footer={ <span>Already have Rentberry account? <Link to={ "/signup" }
                                                                     className="text-purple">Log in</Link></span> }
                { ...this.props }
            >
                <form onSubmit={ this.handleSubmit }>
                    <div className="card-body" style={ { padding: 40 } }>
                        <div className="avatar d-flex justify-content-center"/>
                        <div className="text-center">
                            <label htmlFor="file-upload" className="custom-file-upload" style={ { fontSize: 14 } }>
                                Change Photo
                            </label>
                            <input type="file" id="img" name="img" accept="image/*"/>
                        </div>
                        { super.renderInput("firstname", "First Name", "enter first name") }
                        { super.renderInput("lastname", "Last Name", "enter last name") }
                        { super.renderInput("phone", "Phone Number", "enter phone number") }
                    </div>
                    { super.renderButton("Confirm", "btn btn-primary auth-button") }
                </form>
            </AuthPage>
        );
    }
}

export default ConfirmForm;

