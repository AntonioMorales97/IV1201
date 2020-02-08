import React, { Component } from 'react';
import Applications from './applications-view';
import { connect } from 'react-redux';
//import PropTypes from 'prop-types';
import { getApplications } from '../../actions/application/application-actions';

class ApplicationsContainer extends Component {
  componentDidMount() {
    this.props.getApplications();
  }

  render() {
    return <Applications applications={this.props.application.applications} />;
  }
}
const mapStateToProps = state => ({
  application: state.application,
  error: state.error,
  success: state.success
});
export default connect(mapStateToProps, { getApplications })(
  ApplicationsContainer
);
