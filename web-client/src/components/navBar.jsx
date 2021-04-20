import React, { Component } from 'react';

class NavBar extends Component {
    state = {};

    render() {
        return (
            <nav className="navbar navbar-expand-lg navbar-light bg-light sticky-top">
                <a className="navbar-brand ml-3" href="#">
                    <img src="../logo.svg" width="30" height="30"
                         className="d-inline-block align-top" alt=""/>
                    <span className="font-weight-bold m-2">Rentler</span>
                </a>
                <div className="collapse navbar-collapse">
                    <div className="navbar-nav ">
                        <a className="nav-item nav-link active" href="#">Rent</a>
                        <a className="nav-item nav-link" href="#">Buy</a>
                    </div>
                </div>

            </nav>
        );
    }
}

export default NavBar;
