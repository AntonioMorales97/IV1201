import React, { Component, Fragment } from 'react';
import { Spinner } from 'reactstrap';
import ApplicationView from './application-view';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import {
  getApplication,
  updateApplication
} from '../../actions/application/application-actions';
import { clearSuccess } from '../../actions/success/success-actions';
import { clearError } from '../../actions/error/error-actions';
import {
  APPLICATION_ERROR,
  UPDATE_APPLICATION_SUCCESS
} from '../../actions/application/application-types';

/**
 * Application component. Holds the logic and state of a specific application. Renders the ApplicationView.
 */
class ApplicationContainer extends Component {
  state = {
    errorMessageKey: null,
    successMessageKey: null,
    loading: false
  };

  static propTypes = {
    error: PropTypes.object.isRequired,
    success: PropTypes.object.isRequired,
    clearError: PropTypes.func.isRequired,
    clearSuccess: PropTypes.func.isRequired,
    getApplication: PropTypes.func.isRequired,
    updateApplication: PropTypes.func.isRequired
  };

  componentDidMount() {
    this.props.getApplication(this.props.match.params.id);
  }

  componentDidUpdate(prevProps) {
    const { error, success } = this.props;
    if (error !== prevProps.error) {
      if (error.id === APPLICATION_ERROR) {
        this.setState({ errorMessageKey: error.errorId, loading: false });
        this.props.clearError();
      } else {
        this.setState({ errorMessageKey: null });
      }
    }

    if (success !== prevProps.success) {
      if (success.id === UPDATE_APPLICATION_SUCCESS) {
        this.setState({
          successMessageKey: success.successId,
          loading: false
        });
        this.props.clearSuccess();
      } else {
        this.setState({ successMessageKey: null });
      }
    }
  }

  componentWillUnmount = () => {
    this.props.clearSuccess();
    this.props.clearError();
  };

  acceptApplication = () => {
    if (this.state.loading) return;
    this.setState({ loading: true });
    this.props.updateApplication(
      this.props.application.application.id,
      this.props.application.application.version,
      'accepted'
    );
  };

  rejectApplication = () => {
    if (this.state.loading) return;
    this.setState({ loading: true });
    this.props.updateApplication(
      this.props.application.application.id,
      this.props.application.application.version,
      'rejected'
    );
  };

  unhandleApplication = () => {
    if (this.state.loading) return;
    this.setState({ loading: true });
    this.props.updateApplication(
      this.props.application.application.id,
      this.props.application.application.version,
      'unhandled'
    );
  };

  render() {
    return (
      <Fragment>
        {this.props.application.application === null ||
        this.props.application.loading ? (
          <Spinner />
        ) : (
          <ApplicationView
            application={this.props.application.application}
            acceptApplication={this.acceptApplication}
            rejectApplication={this.rejectApplication}
            unhandleApplication={this.unhandleApplication}
            errorMessageKey={this.state.errorMessageKey}
            successMessageKey={this.state.successMessageKey}
            loading={this.state.loading}
          />
        )}
      </Fragment>
    );
  }
}

const mapStateToProps = state => ({
  application: state.application,
  error: state.error,
  success: state.success
});

export default connect(mapStateToProps, {
  getApplication,
  updateApplication,
  clearError,
  clearSuccess
})(ApplicationContainer);
