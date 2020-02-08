import React from 'react';
import { Route, Switch } from 'react-router-dom';
import { Login, Apply, Recruit, Register } from '../components/pages';
import RecruitRoute from './private/recruit-route';
import ApplicantRoute from './private/applicant-route';

import Application from '../components/application';
import Applications from '../components/applications';

const Routes = () => {
  return (
    <Switch>
      <Route exact path='/login' component={Login} />
      <Route exact path='/register' component={Register} />
      <RecruitRoute exact path='/recruit' component={Recruit} />
      <ApplicantRoute exact path='/apply' component={Apply} />
      <RecruitRoute exact path='/applications/:id' component={Application} />
      <RecruitRoute exact path='/applications' component={Applications} />
    </Switch>
  );
};

export default Routes;
