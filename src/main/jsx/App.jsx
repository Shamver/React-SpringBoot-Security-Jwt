import React from 'react';

import { Route, Switch } from 'react-router-dom';

import Home from './home/Home.jsx';
import Login from './auth/Login.jsx';
import Error from './auth/Error.jsx';


class App extends React.Component {

    render() {
        return (
            <React.Fragment>
                <Route path={"/"} exact component={Home}/>
                <Route path={"/login"} component={Login}/>
                <Switch>
                    <Route path={"/errorpage/:errorMessage/:errorCode"} component={Error}/>
                </Switch>
            </React.Fragment>
        );
    }

}

export default App;