import React, { Component } from 'react';
import LoginView from './login-view';
import { Redirect } from 'react-router-dom';
import {
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  REGISTER_SUCCESS
} from '../../../actions/auth/auth-types';
import { RECRUIT_ROLE } from '../../../constants/role/role-constants';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { login } from '../../../actions/auth/auth-actions';
import { clearError } from '../../../actions/error/error-actions';
import { clearSuccess } from '../../../actions/success/success-actions';

/**
 * Holds the state and logic of the LoginView. Renders the LoginView.
 */
class LoginContainer extends Component {
  state = {
    username: '',
    password: '',
    errorMessageKey: null,
    successMessageKey: null,
    tryLogin: false
  };

  static propTypes = {
    isAuthenticated: PropTypes.bool,
    user: PropTypes.object,
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
      this.setState({ successMessageKey: success.successId });
    }
  }

  componentDidUpdate(prevProps) {
    const { error, success } = this.props;
    if (error !== prevProps.error) {
      if (error.id === LOGIN_FAIL) {
        this.setState({ errorMessageKey: error.errorId, tryLogin: false });
      } else {
        this.setState({ errorMessageKey: null });
      }
    }

    if (success !== prevProps.success) {
      if (success.id === LOGIN_SUCCESS) {
        this.setState({
          successMessageKey: success.successId,
          tryLogin: false
        });
      } else {
        this.setState({ successMessageKey: null });
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
      if (this.props.user.role === RECRUIT_ROLE) {
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
        errorMessageKey={this.state.errorMessageKey}
        successMessageKey={this.state.successMessageKey}
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
