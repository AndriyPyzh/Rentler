import { Component } from 'react';

class EmptyPage extends Component {
    componentDidMount() {
        this.props.hideNavbar();
        this.props.hideFooter();
    }

    close = () => {
        this.props.showNavBar();
        this.props.showFooter();
        this.props.history.goBack();
    };
}

export default EmptyPage;
