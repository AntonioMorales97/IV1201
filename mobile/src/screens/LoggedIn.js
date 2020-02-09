import React, { Component } from 'react';
import { SafeAreaView ,View, Text, FlatList} from 'react-native';
import { Button } from '../components/common/';
import deviceStorage from '../services/deviceStorage';
import axios from 'axios';
import {ApplicationList} from '../components';
import Recruiter from './Recruiter';
/**
 * Handles loggedin Users, supports further development with the choice of implementing a applicant view.
 */
export default class LoggedIn extends Component {
  constructor(props){
    super(props);
       this.state = {
      jwt: this.props.jwt,
      username: this.props.username,
      role: this.props.role,
     // applications: [],
  
      loading: true,
    }
  }
  /**
   *  Loads all applications. SHOULD BE MOVED TO RECRUITER VIEW.
   */
  /*componentDidMount(){
     //axios.get("https://iv1201-backend.herokuapp.com/applications",
       axios.get("http://192.168.0.3:8080/applications", {
         headers:{
           Authorization: 'Bearer ' + this.props.jwt
         }
       }
        ).then((response) => {
          this.setState({applications: response.data._embedded.applicationResponses}) 
         
            }).catch((err)=>{
                console.log(err);
                this.setState({loading:false});
                this.setState({error:err.response.data.message});
            });
 
  }*/

    
  render() {
   
    if(this.state.role =="APPLICANT"){
    return(
      <View style={styles.container}>
      <Recruiter deleteJWT={this.props.deleteJWT} jwt ={this.state.jwt} username={this.state.username} /*applications={this.state.applications}*/ />
      </View>
    );
    }
    else{
      return(
        <View style={styles.container}> 
          <Text>App is not made for applicants</Text>
          <Button onPress ={this.props.deleteJWT}>
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
