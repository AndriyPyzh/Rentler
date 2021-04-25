import React, { Component } from 'react';

class ApartmentsList extends Component {
    state = {
        apartments: [],
        showBottomLoader: false
    };

    loadApartments = () => {
        const apartments = [
            <h1>Apartment1</h1>,
            <h1>Apartment2</h1>,
            <h1>Apartment3</h1>,
            <h1>Apartment4</h1>,
            <h1>Apartment5</h1>,
            <h1>Apartment6</h1>,
            <h1>Apartment7</h1>,
            <h1>Apartment8</h1>,
            <h1>Apartment9</h1>,
            <h1>Apartment</h1>,
            <h1>Apartment</h1>,
            <h1>Apartment</h1>,
            <h1>Apartment</h1>,
            <h1>Apartment</h1>,
            <h1>Apartment</h1>,
            <h1>Apartment</h1>,
            <h1>Apartment</h1>,
            <h1>Apartment</h1>
        ];
        this.setState({ apartments, showBottomLoader: true });
    };

    componentWillMount() {
        window.addEventListener('scroll', this.loadMore);
        setTimeout(this.loadApartments, 3000);
    }

    componentWillUnmount() {
        window.removeEventListener('scroll', this.loadMore);
    }


    loadMore = () => {
        if (window.innerHeight + document.documentElement.scrollTop === document.scrollingElement.scrollHeight) {
            let apartments = [...this.state.apartments];
            for (let i = 0; i < 100000; i++) {
                for (let j = 0; j < 100000; j++) {
                    let k = i * j;
                }
            }
            apartments.push(<h1>New Apartment</h1>);
            apartments.push(<h1>New Apartment</h1>);
            apartments.push(<h1>New Apartment</h1>);
            apartments.push(<h1>New Apartment</h1>);
            apartments.push(<h1>New Apartment</h1>);
            apartments.push(<h1>New Apartment</h1>);
            apartments.push(<h1>New Apartment</h1>);
            this.setState({ apartments });
        }
    };

    render() {
        const { apartments, showBottomLoader } = this.state;
        const showLoader = apartments.length === 0;
        return (
            <div style={ { marginTop: 130 } }>
                { showLoader &&
                <div className="d-flex justify-content-center" style={ { paddingTop: 300 } }>
                    <div className="loader"/>
                </div>
                }

                { !showLoader && apartments }

                { showBottomLoader &&
                <div className="d-flex justify-content-center">
                    <div className="loader"/>
                </div>
                }
            </div>
        );
    }
}

export default ApartmentsList;
