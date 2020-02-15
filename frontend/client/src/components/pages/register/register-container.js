import React, { Component } from 'react';
import RegisterView from './register-view';
import { Redirect } from 'react-router-dom';
import {
  REGISTER_SUCCESS,
  REGISTER_FAIL
} from '../../../actions/auth/auth-types';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { register } from '../../../actions/auth/auth-actions';
import { clearError } from '../../../actions/error/error-actions';
import { clearSuccess } from '../../../actions/success/success-actions';

class RegisterContainer extends Component {
  state = {
    firstName: '',
    lastName: '',
    ssn: '',
    username: '',
    email: '',
    password: '',
    errorMessageKey: null,
    successMessageKey: null,
    tryRegister: false,
    registerSuccess: false
  };

  static propTypes = {
    isAuthenticated: PropTypes.bool,
    error: PropTypes.object.isRequired,
    success: PropTypes.object.isRequired,
    register: PropTypes.func.isRequired,
    clearError: PropTypes.func.isRequired,
    clearSuccess: PropTypes.func.isRequired
  };

  onChange = e => {
    this.setState({ [e.target.name]: e.target.value });
  };

  componentDidUpdate(prevProps) {
    const { error, success } = this.props;
    if (error !== prevProps.error) {
      if (error.id === REGISTER_FAIL) {
        this.setState({ errorMessageKey: error.msg.msg, tryRegister: false });
      } else {
        this.setState({ errorMessageKey: null });
      }
    }

    if (success !== prevProps.success) {
      if (success.id === REGISTER_SUCCESS) {
        this.setState({
          successMessageKey: success.msg.msg,
          tryRegister: false,
          registerSuccess: true
        });
      } else {
        this.setState({ successMessageKey: null });
      }
    }
  }

  componentWillUnmount() {
    //Clean before we leave
    this.props.clearError();
    //this.props.clearSuccess();
  }

  renderRedirect = () => {
    if (this.state.registerSuccess) {
      return <Redirect to='/login' />;
    }
    if (this.props.isAuthenticated) {
      if (this.props.user.role === 'admin') {
        const { from } = this.props.location.state || {
          from: { pathname: '/admin' }
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
    if (this.state.tryRegister) return;
    this.setState({
      tryRegister: true
    });
    this.props.clearError();
    this.props.clearSuccess();

    const { firstName, lastName, ssn, username, email, password } = this.state;

    const user = {
      firstName,
      lastName,
      ssn,
      username,
      email,
      password
    };

    this.props.register(user);
  };

  render() {
    return (
      <RegisterView
        renderRedirect={this.renderRedirect}
        onSubmit={this.onSubmit}
        onChange={this.onChange}
        errorMessageKey={this.state.errorMessageKey}
        successMessageKey={this.state.successMessageKey}
        tryRegister={this.state.tryRegister}
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
export default connect(mapStateToProps, { register, clearError, clearSuccess })(
  RegisterContainer
);
