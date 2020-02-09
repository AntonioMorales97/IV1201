import React, { Component } from 'react';
import {View, Text } from 'react-native';
import { Button } from '../components/common/';

import Recruiter from './Recruiter';
/**
 * Handles loggedin Users, supports further development with the choice of implementing a applicant view.
 */
export default class LoggedIn extends Component {
  constructor(props) {

    super(props);
    this.state = {
      jwt: this.props.jwt,
      username: this.props.user,
      role: this.props.role,
      loading: true,
    }
  }

  render() {

    if (this.state.role == "RECRUIT") {
      return (
        <View style={styles.container}>
          <Recruiter deleteJWT={this.props.deleteJWT} jwt={this.state.jwt} username={this.state.username} />
        </View>
      );
    }
    else {
      return (
        <View style={styles.container}>
          <Text>App is not made for applicants</Text>
          <Button onPress={this.props.deleteJWT}>
            Logout
        </Button>
        </View>
      )
    }
  }
}

const styles = {
  container: {
    flex: 1,
    justifyContent: 'center'
  }
};
