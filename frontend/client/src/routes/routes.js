import React from 'react';
import { Route, Switch } from 'react-router-dom';
import { Login, Apply, Register } from '../components/pages';
import RecruitRoute from './private/recruit-route';
import ApplicantRoute from './private/applicant-route';

import Application from '../components/application';
import Applications from '../components/applications';

/**
 * Contains all the routes in this SPA. Notice the three different routes: Route,
 * ApplicantRoute, and RecruitRoute. Route requires no authentication or authorization.
 * ApplicantRoute and RecruitRoute requires authentication and authorization.
 */
const Routes = () => {
  return (
    <Switch>
      <Route exact path='/login' component={Login} />
      <Route exact path='/register' component={Register} />
      <ApplicantRoute exact path='/apply' component={Apply} />
      <RecruitRoute exact path='/applications/:id' component={Application} />
      <RecruitRoute exact path='/applications' component={Applications} />
    </Switch>
  );
};

export default Routes;
