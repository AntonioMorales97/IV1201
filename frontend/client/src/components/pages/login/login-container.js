import React, { Component } from 'react';
import LoginView from './login-view';
import { Redirect } from 'react-router-dom';

import {
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  REGISTER_SUCCESS
} from '../../../actions/auth/auth-types';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { login } from '../../../actions/auth/auth-actions';
import { clearError } from '../../../actions/error/error-actions';
import { clearSuccess } from '../../../actions/success/success-actions';

class LoginContainer extends Component {
  state = {
    username: '',
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

  componentDidMount() {
    const { success } = this.props;
    if (success.id === REGISTER_SUCCESS) {
      this.setState({ successMessage: success.msg.msg });
    }
  }

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
      if (this.props.user.role === 'RECRUITER') {
        const { from } = this.props.location.state || {
          from: { pathname: '/applications' }
        };
        return <Redirect to={from} />;
      }
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

    const { username, password } = this.state;

    const user = {
      username,
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
