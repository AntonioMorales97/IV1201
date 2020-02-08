import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

const redirectToLogin = location => (
  <Redirect to={{ pathname: '/login', state: { from: location } }} />
);

const redirectToApplications = () => (
  <Redirect to={{ pathname: '/applications' }} />
);

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
        if (user.role === 'APPLICANT') {
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
