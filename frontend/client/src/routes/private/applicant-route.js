import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { APPLICANT_ROLE } from '../../constants/role/role-constants';

const redirectToLogin = location => (
  <Redirect to={{ pathname: '/login', state: { from: location } }} />
);

const redirectToApplications = () => (
  <Redirect to={{ pathname: '/applications' }} />
);

/**
 * Defines an Applicant route, i.e. only a logged in user with 'applicant'
 * authorization can go to.
 */
const ApplicantRoute = ({
  component: Component,
  auth: { isAuthenticated, isLoading, user },
  ...rest
}) => (
  <Route
    {...rest}
    render={props => {
      if (!isAuthenticated && !isLoading) {
        return redirectToLogin(props.location);
      } else {
        if (user.role === APPLICANT_ROLE) {
          return <Component {...props} />;
        } else {
          return redirectToApplications();
        }
      }
    }}
  />
);

ApplicantRoute.propTypes = {
  auth: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  auth: state.auth
});

export default connect(mapStateToProps)(ApplicantRoute);
