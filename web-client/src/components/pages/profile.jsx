import Joi from "joi-browser";
import React from 'react';
import Logout from "../auth/logout";
import Form from "../shared/form";

class Profile extends Form {
    state = {
        data: {
            avatar: null,
            firstName: '',
            lastName: '',
            email: '',
            phone: '',
            dateOfBirth: Date.now(),
            newPassword: ''
        },
        errors: {},
        showMessages: {}
    };

    schema = {
        firstName: Joi.string().regex(/^[a-zA-Z]*$/).max(20).required().label('First Name').options({
            language: { string: { regex: { base: 'can contain only letters' } } }
        }),
        lastName: Joi.string().regex(/^[a-zA-Z]*$/).max(20).required().label('Last Name').options({
            language: { string: { regex: { base: 'can contain only letters' } } }
        }),
        email: Joi.string().email().required().label('Email'),
        phone: Joi.string().regex(/^\+(?:[0-9] ?){6,14}[0-9]$/).required().label('Phone Number').options({
            language: { string: { regex: { base: 'should be a valid phone number' } } }
        }),
        dateOfBirth: Joi.date(),
        password: Joi.string().min(8).max(30).required().label('New Password')
    };

    render() {
        const { avatar } = this.state.data;

        return (
            <div className="d-flex justify-content-center">
                <div className="row profile">
                    <div className="avatar-section">
                        <div className="d-flex justify-content-center">
                            { !avatar && <div className="empty-avatar d-flex justify-content-center"/> }
                            { avatar && <img src={ this.state.data.avatar } alt="avatar uploaded" className="avatar"/> }
                        </div>
                        { super.renderImageInput("avatar", "Change Photo", "link") }
                    </div>
                    <div className="info-section">
                        <form>
                            { super.renderInput("firstName", "First Name", "enter first name...") }
                            { super.renderInput("lastName", "Last Name", "enter last name...") }
                            { super.renderInput("email", "Email", "enter email...") }
                            <div style={ { marginTop: 30 } }>
                                <label style={ { fontWeight: "bold", fontSize: 24 } }>Phone Number</label>
                                { super.renderInput("phone", "Phone Number", "enter phone number...") }
                            </div>
                            <div style={ { marginTop: 30 } }>
                                <label style={ { fontWeight: "bold", fontSize: 24 } }>Date of Birth</label>
                                { super.renderInput("dateOfBirth", "Date of Birth", "enter date of birth...", "date", {
                                    min: "1950-01-01",
                                    max: "2004-01-01"
                                }) }
                            </div>
                            <div style={ { marginTop: 30 } }>
                                <label style={ { fontWeight: "bold", fontSize: 24 } }>Phone Number</label>
                                { super.renderInput("newPassword", "Create Password", "enter new password...") }
                            </div>
                            <div style={ { marginTop: 30 } }>
                                { super.renderButton("Save Changes", "btn btn-primary profile-button shadow-lg") }
                                <div className="w-100 text-center" style={ { marginTop: 10 } }>
                                    <button className="no-button link gray-link">Discard changes</button>
                                </div>
                            </div>
                        </form>
                        <div style={ { marginTop: 30, marginBottom: 30 } }>
                            <Logout classes="no-button link red-link">Log Out</Logout>
                        </div>
                    </div>

                </div>
            </div>
        );
    }
}

export default Profile;
