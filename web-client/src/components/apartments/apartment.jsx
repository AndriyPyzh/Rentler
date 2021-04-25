import React, { Component } from 'react';

class Apartment extends Component {
    state = {};

    render() {
        return (
            <div style={ { width: 370, height: 200, padding: 10 } }>
                <div className="card">
                    <div className="card-body">
                        This is some text within a card body.
                    </div>
                </div>
            </div>
        );
    }
}

export default Apartment;
