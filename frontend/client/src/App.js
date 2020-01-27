import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Header from './components/header';
import Routes from './routes/routes';
import { Provider } from 'react-redux';
import store from './store';

import './App.css';

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className='App'>
          <Header />
          <Switch>
            <Route component={Routes} />
          </Switch>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
