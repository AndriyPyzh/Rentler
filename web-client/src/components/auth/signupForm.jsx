import React from 'react';
import EmptyPage from "../shared/emptyPage";

class SignupForm extends EmptyPage {
    state = {};

    render() {
        return (
            <h1>
                Sign Up
                <button onClick={ this.close }> exit</button>
            </h1>
        );
    }
}

export default SignupForm;
