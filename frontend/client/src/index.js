import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './i18n';
import * as serviceWorker from './serviceWorker';

// eslint-disable-next-line
import $ from 'jquery';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import 'bootstrap/dist/css/bootstrap.min.css';

ReactDOM.render(<App />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();