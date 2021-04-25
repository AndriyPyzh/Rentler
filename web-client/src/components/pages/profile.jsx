import React, { Component } from 'react';
import userService from "../../services/userService";

class Profile extends Component {
    state = {
        avatar: null,
        initials: null,
        firstName: null,
        lastName: null,
        email: null,
        phoneNumber: null
    };

    populateInfo = async () => {
        const { username } = this.props.match.params;
        const { data } = await userService.getByUsername(username);
        const initials = (data.firstName[0] + data.lastName[0]).toUpperCase();
        this.setState({
            initials,
            firstName: data.firstName,
            lastName: data.lastName,
            phoneNumber: data.phoneNumber,
            email: data.email
        });
    };

    async componentDidMount() {
        await this.populateInfo();
    }

    render() {
        const { avatar, email, initials, firstName, lastName, phoneNumber } = this.state;
        return (
            <div className="d-flex justify-content-center">
                <div className="font-weight-bold profile-back">{initials}</div>
                <div className="text-center profile-info" >
                    <div style={ { marginTop: 120 } }>
                        <div className="d-flex justify-content-center">
                            { !avatar && <div className="empty-avatar d-flex justify-content-center"/> }
                            { avatar && <img src={ this.state.data.avatar } alt="avatar uploaded" className="avatar"/> }
                        </div>
                        <div className="font-weight-bold profile-name">{ `${ firstName } ${ lastName }` }</div>
                    </div>
                    <div style={ { fontSize: 18} }>{ email }</div>
                    <div style={ { fontSize: 14, marginTop: 5} }>{ phoneNumber }</div>
                </div>
            </div>
        );
    }
}

export default Profile;
