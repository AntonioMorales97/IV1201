import React, { Component, Fragment } from 'react';
import { Spinner } from 'reactstrap';
import ApplicationView from './application-view';
import { connect } from 'react-redux';
//import PropTypes from 'prop-types';
import {
  getApplication,
  updateApplication
} from '../../actions/application/application-actions';

class ApplicationContainer extends Component {
  componentDidMount() {
    this.props.getApplication(this.props.match.params.id);
  }

  acceptApplication = () => {
    this.props.updateApplication(
      this.props.application.application.id,
      'accepted'
    );
  };

  rejectApplication = () => {
    this.props.updateApplication(
      this.props.application.application.id,
      'rejected'
    );
  };

  unhandleApplication = () => {
    this.props.updateApplication(
      this.props.application.application.id,
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
