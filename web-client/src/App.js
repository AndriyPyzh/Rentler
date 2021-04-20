import React, { Component } from 'react';
import './App.css';
import NavBar from "./components/navBar";

class App extends Component {
    render() {
        return (
            <React.Fragment>
                <NavBar/>
                <div className="container">
                    <h1>Rentler Web Client</h1>
                </div>
            </React.Fragment>
        );
    }
}

export default App;
