import React, { Component } from 'react';
import { Link } from "react-router-dom";

class NavBar extends Component {
    state = {};

    render() {
        return (
            <nav className="navbar navbar-expand-xl navbar-light bg-light border-bottom sticky-top">
                <Link className="navbar-brand ml-3" to="/">
                    <img src="../logo.svg" width="30" height="30"
                         className="d-inline-block align-top" alt=""/>
                    <span className="font-weight-bold m-2">Rentler</span>
                </Link>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <div className="navbar-nav border-right">
                        <Link className="nav-item nav-link m-1 mx-2" to="/apartments">Rent</Link>
                        <Link className="nav-item nav-link m-1 mx-2 mr-3" to="/apartments">Buy</Link>
                    </div>
                    <form className="input-group px-4 mr-auto">
                        <input className="form-control w-100 fw-icon"
                               type="text"
                               placeholder="&#xF002; Where do you want to leave?"
                        />
                    </form>
                    <div className="navbar-nav d-flex align-items-center border-left" style={{ width: '540px'}}>
                        <Link className="btn btn-outline-warning ml-4 mr-2 list-property-button"
                              to="/add-apartment">List a Property</Link>
                        <Link className="nav-item nav-link m-1 mx-2" to="/login">Log In</Link>
                        <Link className="nav-item nav-link m-1 mx-2" to="/signup">Sign Up</Link>
                    </div>
                </div>
            </nav>
        );
    }
}

export default NavBar;
