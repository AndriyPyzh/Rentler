import moment from "moment";
import React, { Component } from 'react';
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import apartmentService from "../../services/apartmentService";
import logger from "../../services/logService";
import userService from "../../services/userService";
import ScrollToTop from "../shared/scrollToTop";

class ApartmentDetails extends Component {
    state = {
        apartment: {},
        addressStr: '',
        user: {}
    };

    async populateApartment() {
        try {
            const { data: apartment } = await apartmentService.getById(this.props.match.params.id);
            const address = apartment.address;
            const addressStr = `${ address.houseNumber } ${ address.street }, ${ address.city }`;
            this.setState({ apartment, addressStr });
        } catch (ex) {
            logger.log(ex);
            if (ex.response && ex.response.status === 400) {
                toast.error(ex.response.data.message.toString());
                window.location = '/not-found';
            } else
                toast.error(ex.message.toString());
        }
    }

    async populateUserInfo(owner) {
        const { data } = await userService.getByUsername(owner);
        this.setState({ user: data });
    }

    async componentDidMount() {
        await this.populateApartment();
        await this.populateUserInfo(this.state.apartment.owner);
    }

    getAvailableLabel() {
        const { availableFrom } = this.state.apartment;
        if (new Date(availableFrom) < new Date())
            return <span className="available-from">Available: <b>Right Now</b></span>;
        return <span className="available-from">
            Available From: <b>{ moment(availableFrom).format("MMMM DD") }</b>
        </span>;
    }

    formatPrice(price) {
        return price && price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    getAmenitiesHeight() {
        const x = document.getElementById('amenities');
        return x ? x.clientHeight : 0;
    }

    getAmenities() {
        const { amenities } = this.state.apartment;
        return amenities ? amenities.map(
            (a, indx) => {
                return (
                    <React.Fragment>
                        <div className="col" style={ { fontSize: 14, height: 40, fontWeight: 500 } }>
                            <div className="amenitie d-inline-flex"/>
                            { a }
                        </div>
                        { indx % 2 === 1 ? <div className="w-100"/> : '' }
                    </React.Fragment>);
            }) : '';
    }

    getPetsPolicy() {
        const { petPolicy } = this.state.apartment;
        let classes = '';
        switch (petPolicy) {
            case 'Dogs allowed':
                classes = 'dogs-icon-white d-inline-flex';
                break;
            case 'Cats allowed':
                classes = 'cats-icon-white d-inline-flex';
                break;
            case 'No pets':
                classes = 'no-pets-icon-white d-inline-flex';
                break;
            default:
                return '';
        }
        return (
            <React.Fragment>
                <div className={ classes + " d-inline-flex" }/>
                <span><b>{ petPolicy }</b></span>
            </React.Fragment>);
    }

    render() {
        const { name, type, beds, bath, squareMeters, price, description, owner, amenities } = this.state.apartment;
        const { firstName, lastName, phoneNumber } = this.state.user;
        const { addressStr } = this.state;
        return (
            <div className="d-flex justify-content-center" style={ { marginTop: 60 } }>
                <ScrollToTop/>
                <div style={ { width: 940 } }>
                    <div className="apt-det-back"/>
                    <div className="d-flex justify-content-between" style={ { marginTop: 105 } }>
                        <span className="apt-badge">{ type }</span>
                        <span className="apt-badge">{ addressStr }</span>
                    </div>
                    <div className="row" style={ { marginLeft: 0, marginRight: 0 } }>
                        <div className="" style={ { width: 700, paddingLeft: 10, paddingRight: 10 } }>
                            <div className="d-flex align-items-center" style={ { height: 300 } }>
                                <div style={ { fontSize: 72, lineHeight: '72px', fontWeight: "bold", color: "white" } }>
                                    { name }
                                </div>
                            </div>
                            <div style={ { height: 100, color: "white" } }>
                                <div className="d-flex align-items-center">
                                    <div className="beds-icon-white d-inline-flex"/>
                                    <span>Bed: <b>{ beds }</b></span>
                                    <div className="bath-icon-white d-inline-flex"/>
                                    <span>Bath: <b>{ bath }</b></span>
                                    <div className="sqft-icon-white d-inline-flex"/>
                                    <span>Sq Ft: <b>{ squareMeters }</b></span>
                                    { this.getPetsPolicy() }
                                </div>
                            </div>
                            <div style={ { marginTop: 100, marginBottom: 100 } }>
                                <div className="apt-h">{ type } Description</div>
                                <div style={ { fontSize: 20 } }>{ description }</div>
                            </div>
                            <div id="amenities" className="amenities">
                                <div className="amenities-back" style={ { height: this.getAmenitiesHeight() } }/>
                                <div style={ { paddingTop: 70, paddingBottom: 70 } }>
                                    <div className="apt-h">Amenities
                                    </div>
                                    <div className="row">
                                        { this.getAmenities() }
                                    </div>
                                </div>
                            </div>
                            <div className="row" style={ { marginTop: 50, marginBottom: 50 } }>
                                <div className="col font-weight-bold" style={ { fontSize: 24, height: 55 } }>Contact
                                    Information
                                </div>
                                <div className="col-2 apt-avatar"/>
                                <div className="col" style={ { marginLeft: 10 } }>
                                    <div className="row">
                                        <Link to={ "/profile/" + owner }
                                              className="no-button link black-link">{ firstName } { lastName }</Link>
                                    </div>
                                    <div className="row" style={ { color: '#9c99b6' } }>{ phoneNumber }</div>
                                </div>
                            </div>
                        </div>
                        <div style={ { width: 240, paddingLeft: 10, paddingRight: 10 } }>
                            <div className="apt-det-card text-center">
                                <div className="d-flex align-items-center border-bottom"
                                     style={ { height: 45 } }>
                                    <div className="save">
                                        <span style={ { fontSize: 20 } }>&#9825; </span>
                                        <span style={ { fontSize: 19, fontWeight: 300 } }>Save</span>
                                    </div>
                                </div>
                                <div className="d-flex align-items-center"
                                     style={ { height: 105, paddingTop: 25, paddingBottom: 25 } }>
                                    <div className="w-100">
                                        <div className="w-100" style={ { color: '#9c99b6', fontSize: 14 } }>price</div>
                                        <div className="w-100 font-weight-bold"
                                             style={ { fontSize: 24 } }>${ this.formatPrice(price) }</div>
                                    </div>
                                </div>
                                <div className="d-flex align-items-center"
                                     style={ { height: 40, backgroundColor: '#f4f5f7' } }>
                                    { this.getAvailableLabel() }
                                </div>
                                <div style={ { height: 60 } }>
                                    <button className="no-button green-button apply-button">
                                        Apply
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default ApartmentDetails;
