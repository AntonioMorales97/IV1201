import React, { Component, Fragment } from 'react';
import { Text, View } from 'react-native';
import { Input, TextLink, Loading, Button } from './common';
import axios from 'axios';
import deviceStorage from '../services/deviceStorage';

class Login extends Component {
  constructor(props){
    super(props);
    this.state = {
      username: '',
      password: '',
      error: '',
      loading: false
    };
    this.loginUser = this.loginUser.bind(this);
  }
    loginUser(){
        const {username, password} = this.state;
        this.setState({error:'', loading: 'true'});

        let sendData = {
    username: username,
    password: password
}

        //axios.post("https://iv1201-backend.herokuapp.com/authenticate",sendData)
        axios.post("http://192.168.0.3:8080/authenticate",sendData)
        .then((response) =>{
            deviceStorage.saveItem('username', username);
            deviceStorage.saveItem('role', response.data.role);
            deviceStorage.saveItem("id_token", response.data.jwtToken);
            this.props.newJWT(response.data.jwtToken, username, response.data.role);
            }).catch((err)=>{
                console.log(err.data);
                this.setState({loading:false});
                this.setState({error:err.response.data.message});
            });
    }
  render() {
    const { username, password, error, loading } = this.state;
    const { form, section, errorTextStyle } = styles;

    return (
      <Fragment>
        <View style={form}>
          <View style={section}>
            <Input
              placeholder="username"
              label="username"
              value={username}
              onChangeText={username => this.setState({ username })}
            />
          </View>

          <View style={section}>
            <Input
              secureTextEntry
              placeholder="password"
              label="Password"
              value={password}
              onChangeText={password => this.setState({ password })}
            />
          </View>

          <Text style={errorTextStyle}>
            {error}
          </Text>

          {!loading ?
            <Button onPress={this.loginUser}>
              Login
            </Button>
            :
            <Loading size={'large'} />}

        </View>
        <TextLink onPress={this.props.authSwitch}>
          Don't have an account? Register!
        </TextLink>
      </Fragment>
    );
  }
}

const styles = {
  form: {
    width: '100%',
    borderTopWidth: 1,
    borderColor: '#ddd',
  },
  section: {
    flexDirection: 'row',
    borderBottomWidth: 1,
    backgroundColor: '#fff',
    borderColor: '#ddd',
  },
  errorTextStyle: {
    alignSelf: 'center',
    fontSize: 18,
    color: 'red'
  }
};

export { Login };