import React, { Component } from 'react';
import { ScrollView ,View, Text, FlatList} from 'react-native';

import { Button ,Input} from '../components/common';
import axios from 'axios';
import {ApplicationList, Application} from '../components';
/**
 * Class tha handles the recruiters view.
 */
export default class Recruiter extends Component {
  constructor(props){
    super(props);
      this.state = {
      watchingApplication:'',
      status:'',
      error:'',
      applications:[],
      loading: true,
    }
    //this.selectingApplication=this.selectApplication.bind(this);
    this.goBackToList =this.goBackToList.bind(this);
    
  }
  
  
    /**
    *  Loads all applications. SHOULD BE MOVED TO RECRUITER VIEW.
    */
  componentDidMount(){
     axios.get("https://iv1201-backend-dev.herokuapp.com/applications",{
       //axios.get("http://192.168.0.3:8080/applications", {
         headers:{
           Authorization: 'Bearer ' + this.props.jwt
         }
       }
        ).then((response) => {
          //console.error(response)
          if(response.data._embedded==undefined){
            this.setState({error:"No applictaions to show!"});
          }
          
          this.setState({applications: response.data._embedded.applicationListResponses}) 
         
            }).catch((err)=>{
                console.log(err);
                this.setState({loading:false});
                this.setState({error:err.response.data.message});
            });
 
  }
   /**
   * Function to get a speicifc applications data.
   */
  selectApplication=(id)=>{
      axios.get("https://iv1201-backend-dev.herokuapp.com/application/"+id,{
       //axios.get("http://192.168.0.3:8080/application/"+id, {
         headers:{
           Authorization: 'Bearer ' + this.props.jwt
         }
       }
        ).then((response) => {
               
          this.setState({watchingApplication: response.data}) 
         
            }).catch((err)=>{
                console.log(err.response.data.message);
                this.setState({loading:false});
                this.setState({error:err.response.data.message});
            });
  }
  /**
   * Function that changes the status of a specific application.
   */
  changeStatus=(status)=>{
       let body = {
    name: status
  }
      axios.get("https://iv1201-backend-dev.herokuapp.com/StatusChange/"+this.state.watchingApplication.id,{
       //axios.get("http://192.168.0.3:8080/StatusChange/"+this.state.watchingApplication.id, {
         headers:{
           Authorization: 'Bearer ' + this.props.jwt
         }, body
       }
        ).then((response) => {
       
          this.setState({watchingApplication: response.data})
         
            }).catch((err)=>{
                console.log(err.response.data.message);
                this.setState({loading:false});
                this.setState({error:err.response.data.message});
            });
  }
  /**
   * Sets the currently watching application sate to '', used to go back to application list view.
   */
  goBackToList(){
      this.setState({watchingApplication:''});
  }
  /**
   * Renders all applications.
   */
  renderApplications(){
   
    return this.state.applications.map(application =>{
        
      return <ApplicationList selectApplication = {this.selectApplication} application= {application}/>
    })
  }

  render() {
   const status = this.state.status;
    if(!this.state.watchingApplication){
    return(
      <View style={styles.container}>
        <Text style ={styles.welcomeText}>Applications</Text>
        <Text style={styles.errorTextStyle}>
                        {this.state.error}
                    </Text>
        {this.renderApplications()}
        <Button style={styles.button} onPress ={this.props.deleteJWT}>
          Logout
        </Button>
      </View>
    );
    }
    else{
      return(
        <View style={styles.container}> 
            <Text style ={styles.welcomeText}>Viewing Application</Text>
            <ScrollView >
                <Application   application ={this.state.watchingApplication}/>
                <View style={styles.form}>
                    <Input
                    placeholder="New Status"
                    label="status"
                    value={status}
                    onChangeText={status => this.setState({ status })}
                    />
                </View>
                <Button onPress ={this.changeStatus(this.state.status)}>Alter status</Button>
            </ScrollView>
            <View style ={styles.buttons}>
                <View style={styles.buttonView}> 
                    <Button  onPress ={this.goBackToList} style ={styles.logout}>
                        Go back to List
                    </Button>
                </View>
                <View style={styles.buttonView}> 
                    <Button onPress ={this.props.deleteJWT} style ={styles.logout}>
                        Logout
                    </Button>
                </View>
            </View>
        </View>
      )
    }
  }
}

const styles = {
  container: {
    flex: 1,
    justifyContent: 'center'
  },
  form: {
    
        backgroundColor: '#f8f8f8',
        borderBottomWidth:1,
        borderColor:'#eee',
  },
  welcomeText: {
    
    position: 'absolute',
    top: 0,
    fontSize: 30,
   
    alignSelf: 'center',
  },
  item: {
    backgroundColor: '#f9c2ff',
    padding: 20,
    marginVertical: 8,
    marginHorizontal: 16,
  },
  title: {
    fontSize: 32,
  },
  buttons:{
      flexDirection:'row',
      left:0,
  }
  ,
  logout:{
    justifyContent: 'flex-end'
  },
  buttonView:{
      flex:1,
  },
 
};
/* <FlatList 
          data = {this.state.applications}
          renderItem={({application}) =>  <Application application={application}/>}
        />*/