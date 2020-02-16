import React, { Component, Fragment } from 'react';
import { Spinner } from 'reactstrap';
import ApplicationView from './application-view';
import { connect } from 'react-redux';
//import PropTypes from 'prop-types';
import {
  getApplication,
  updateApplication
} from '../../actions/application/application-actions';
import {
  APPLICATION_ERROR,
  UPDATE_APPLICATION_SUCCESS
} from '../../actions/application/application-types';

class ApplicationContainer extends Component {
  state = {
    errorMessageKey: null,
    successMessageKey: null
  };

  componentDidMount() {
    this.props.getApplication(this.props.match.params.id);
  }

  componentDidUpdate(prevProps) {
    const { error, success } = this.props;
    if (error !== prevProps.error) {
      if (error.id === APPLICATION_ERROR) {
        this.setState({ errorMessageKey: error.errorId });
      } else {
        this.setState({ errorMessageKey: null });
      }
    }

    if (success !== prevProps.success) {
      if (success.id === UPDATE_APPLICATION_SUCCESS) {
        this.setState({
          successMessageKey: success.successId
        });
      } else {
        this.setState({ successMessageKey: null });
      }
    }
  }

  acceptApplication = () => {
    this.props.updateApplication(
      this.props.application.application.id,
      this.props.application.application.version,
      'accepted'
    );
  };

  rejectApplication = () => {
    this.props.updateApplication(
      this.props.application.application.id,
      this.props.application.application.version,
      'rejected'
    );
  };

  unhandleApplication = () => {
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
export default connect(mapStateToProps, { getApplication, updateApplication })(
  ApplicationContainer
);
