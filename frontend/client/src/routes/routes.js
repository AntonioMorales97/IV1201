import React from 'react';
import { Route, Switch } from 'react-router-dom';
import { Login, Apply, Admin, Register } from '../components/pages';
import AdminRoute from './private/admin-route';
import ApplicantRoute from './private/applicant-route';

const Routes = () => {
  return (
    <Switch>
      <Route exact path='/login' component={Login} />
      <Route exact path='/register' component={Register} />
      <AdminRoute exact path='/admin' component={Admin} />
      <ApplicantRoute exact path='/apply' component={Apply} />
    </Switch>
  );
};

export default Routes;
