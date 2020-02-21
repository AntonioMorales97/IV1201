import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { RECRUIT_ROLE } from '../../constants/role/role-constants';

const redirectToLogin = location => (
  <Redirect to={{ pathname: '/login', state: { from: location } }} />
);

const redirectToApply = () => <Redirect to={{ pathname: '/apply' }} />;

/**
 * Defines a Recruit route, i.e. only a logged in user with 'recruit'
 * authorization can go to.
 */
const RecruitRoute = ({
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
        if (user.role === RECRUIT_ROLE) {
          return <Component {...props} />;
        } else {
          return redirectToApply();
        }
      }
    }}
  />
);

RecruitRoute.propTypes = {
  auth: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  auth: state.auth
});

export default connect(mapStateToProps)(RecruitRoute);
