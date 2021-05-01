import React, { Component } from 'react';

class ApartmentApplications extends Component {
    state = {};

    render() {
        return (
            <div style={ { marginTop: 60 } }>
                <h1>applications for {this.props.match.params.id}</h1>
            </div>
        );
    }
}

export default ApartmentApplications;
