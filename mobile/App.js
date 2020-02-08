import React, { Component } from 'react';
import { Loading } from './src/components/common';
import Auth from './src/screens/Auth';
import LoggedIn from './src/screens/LoggedIn';
import deviceStorage from './src/services/deviceStorage'
export default class App extends Component {
  constructor() {
    super();
    this.state = {
      jwt: '',
      user: '',
      role: '',
      loading: true
    }
    this.newJWT = this.newJWT.bind(this);
    this.deleteJWT = deviceStorage.deleteJWT.bind(this);
    this.loadJWT = deviceStorage.loadJWT.bind(this);
    this.loadJWT();
  }
  /**
   * Adds a jwt to the state, along with its username, and role.
   * @param {*} jwt jsonwebToken.
   * @param {*} user Username of the jwt.
   * @param {*} role Role of the jwt.
   */
  newJWT(jwt, user, role){
    this.setState({
      jwt: jwt,
      user: user,
      role: role,
    });
  }  
/**
 * The Entry point of the app.
 */
  render() {
    if(this.state.loading){
      return(
        <Loading size={'large'} />
      );
    }else if (!this.state.jwt) {
      return (
        <Auth newJWT={this.newJWT} />
      );
    } else if (this.state.jwt) {
      return (
        <LoggedIn deleteJWT={this.deleteJWT} jwt ={this.state.jwt} user={this.state.user} role= {this.state.role}/>
      );
        
    }
  }
}