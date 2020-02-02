import React, { Component } from 'react';
import ApplicationView from './application-view';

class ApplicationContainer extends Component {
  state = {
    id: null
  };
  componentDidMount() {
    const { id } = this.props.match.params;
    this.setState({ id });
  }

  render() {
    return <ApplicationView id={this.state.id} />;
  }
}

export default ApplicationContainer;
