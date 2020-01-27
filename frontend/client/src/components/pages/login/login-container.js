import React, { Component } from 'react';
import LoginView from './login-view';
import { Redirect } from 'react-router-dom';

import { LOGIN_SUCCESS, LOGIN_FAIL } from '../../../actions/auth/auth-types';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { login } from '../../../actions/auth/auth-actions';
import { clearError } from '../../../actions/error/error-actions';
import { clearSuccess } from '../../../actions/success/success-actions';

class LoginContainer extends Component {
  state = {
    email: '',
    password: '',
    errorMessage: null,
    successMessage: null,
    tryLogin: false
  };

  static propTypes = {
    isAuthenticated: PropTypes.bool,
    error: PropTypes.object.isRequired,
    success: PropTypes.object.isRequired,
    login: PropTypes.func.isRequired,
    clearError: PropTypes.func.isRequired,
    clearSuccess: PropTypes.func.isRequired
  };

  onChange = e => {
    this.setState({ [e.target.name]: e.target.value });
  };

  componentDidUpdate(prevProps) {
    const { error, success } = this.props;
    if (error !== prevProps.error) {
      if (error.id === LOGIN_FAIL) {
        this.setState({ errorMessage: error.msg.msg, tryLogin: false });
      } else {
        this.setState({ errorMessage: null });
      }
    }

    if (success !== prevProps.success) {
      if (success.id === LOGIN_SUCCESS) {
        this.setState({
          successMessage: success.msg.msg,
          tryLogin: false
        });
      } else {
        this.setState({ successMessage: null });
      }
    }
  }

  componentWillUnmount() {
    //Clean before we leave
    this.props.clearError();
    this.props.clearSuccess();
  }

  renderRedirect = () => {
    if (this.props.isAuthenticated) {
      if (this.props.user.role === 'admin') {
        console.log('admin, redirect to admin');
        const { from } = this.props.location.state || {
          from: { pathname: '/admin' }
        };
        return <Redirect to={from} />;
      }
      console.log('applicant, redirect to apply');
      const { from } = this.props.location.state || {
        from: { pathname: '/apply' }
      };
      return <Redirect to={from} />;
    }
  };

  onSubmit = e => {
    e.preventDefault();
    if (this.state.tryLogin) return;
    this.setState({
      tryLogin: true
    });
    this.props.clearError();
    this.props.clearSuccess();
    console.log(this.state.email);
    console.log(this.state.password);

    const { email, password } = this.state;

    const user = {
      email,
      password
    };

    this.props.login(user);
  };

  render() {
    return (
      <LoginView
        renderRedirect={this.renderRedirect}
        onSubmit={this.onSubmit}
        onChange={this.onChange}
        errorMessage={this.state.errorMessage}
        successMessage={this.state.successMessage}
        tryLogin={this.state.tryLogin}
      />
    );
  }
}

const mapStateToProps = state => ({
  isAuthenticated: state.auth.isAuthenticated,
  user: state.auth.user,
  error: state.error,
  success: state.success
});
export default connect(mapStateToProps, { login, clearError, clearSuccess })(
  LoginContainer
);
//export default LoginContainer;
