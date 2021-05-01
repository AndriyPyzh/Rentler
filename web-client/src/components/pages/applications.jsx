import React, { Component } from 'react';
import ScrollToTop from "../shared/scrollToTop";

class Applications extends Component {
    state = {};

    render() {
        return (
            <div>
                <ScrollToTop/>
                <div className="text-center font-weight-bold"
                     style={ { marginBottom: 20, marginTop: 200, fontSize: 48 } }>
                    Nothing to See Here Yet
                </div>
                <div className="text-center text-gray" style={ { fontSize: 20 } }>Your applications will appear here
                </div>
            </div>
        );
    }
}

export default Applications;
