import '../webapp/css/custom.css';
import 'bootstrap/dist/css/bootstrap.min.css';
 
import React from 'react';
import ReactDOM from 'react-dom';

import { BrowserRouter } from 'react-router-dom';

import App from './App.jsx';

 
class MainPage extends React.Component {
 
    render() {
        return (
			<BrowserRouter>
				<App />
			</BrowserRouter>
        );
    }
 
}
 
ReactDOM.render(<MainPage/>, document.getElementById('root'));