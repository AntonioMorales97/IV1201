import React, { Component } from 'react';
import { View } from 'react-native';
import { Login, Registration } from '../components';
/**
 * Handles Authentication choices.
 */
export default class Auth extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showLogin: true
    };
    this.whichForm = this.whichForm.bind(this);
    this.authSwitch = this.authSwitch.bind(this);
  }
  /**
   * Used to swap the state showLogin.
   */
  authSwitch() {
    this.setState({
      showLogin: !this.state.showLogin
    });
  }
  /**
   * Function that handles the rendering of the different views.
   */
  whichForm() {
    if (!this.state.showLogin) {
      return (
        <Registration authSwitch={this.authSwitch} />
      );
    } else {
      return (
        <Login newUser={this.props.newUser} authSwitch={this.authSwitch} />
      );
    }
  }
  render() {
    return (
      <View styles={styles.container}>
        {this.whichForm()}
      </View>
    );
  }
}
const styles = {
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  }
};


