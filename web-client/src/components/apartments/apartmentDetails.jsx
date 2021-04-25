import React, { Component } from 'react';
import apartmentService from "../../services/apartmentService";

class ApartmentDetails extends Component {
    state = {
        apartment: {}
    };

    async componentDidMount() {
        const { data: apartment } = await apartmentService.getById(this.props.match.params.id);
        this.setState({ apartment });
    }

    render() {
        const { apartment } = this.state;
        return (
            <div style={ { marginTop: 70 } }>
                <h1>Apartment - { apartment.name } ({ apartment.id })</h1>
            </div>
        );
    }
}

export default ApartmentDetails;
