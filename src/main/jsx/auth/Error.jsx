import React from 'react';

import { Route, Switch } from 'react-router-dom';



class Error extends React.Component {

    render() {
        return (
            <React.Fragment>
                <div>
                    <h1>에러</h1>
                    <h3>에러코드: {this.props.match.params.errorCode}</h3>
                    <h3>에러메세지: {this.props.match.params.errorMessage}</h3>
                </div>
            </React.Fragment>
        );
    }

}

export default Error;