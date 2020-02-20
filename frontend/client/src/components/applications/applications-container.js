import React, { Component } from 'react';
import ApplicationsView from './applications-view';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { getApplications } from '../../actions/application/application-actions';

/**
 * Holds the state and logic for the ApplicationsView. Renders the ApplicationsView.
 */
class ApplicationsContainer extends Component {
  static propTypes = {
    application: PropTypes.object.isRequired,
    getApplications: PropTypes.func.isRequired
  };

  componentDidMount() {
    this.props.getApplications();
  }

  render() {
    return (
      <ApplicationsView applications={this.props.application.applications} />
    );
  }
}
const mapStateToProps = state => ({
  application: state.application
});

export default connect(mapStateToProps, { getApplications })(
  ApplicationsContainer
);
