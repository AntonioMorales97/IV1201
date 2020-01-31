import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import App from './App';
import './i18n';
import * as serviceWorker from './serviceWorker';

// eslint-disable-next-line
import $ from 'jquery';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import 'bootstrap/dist/css/bootstrap.min.css';

console.log(process.env.NODE_ENV);

axios.defaults.baseURL =
  process.env.NODE_ENV === 'production'
    ? process.env.REST_URL
    : 'http://localhost:8080';
axios.defaults.headers.post['Content-Type'] = 'application/json';

console.log(process.env.REST_URL);
console.log(axios.defaults.baseURL + '<- index');

ReactDOM.render(<App />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
