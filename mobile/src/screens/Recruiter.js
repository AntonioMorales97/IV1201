import React, { Component } from 'react';
import { ScrollView, View, Text } from 'react-native';

import { Button, Input } from '../components/common';

import { ApplicationList, Application } from '../components/common';
import backendCalls from '../services/backendCalls';
/**
 * Class tha handles the recruiters view.
 */
export default class Recruiter extends Component {
  constructor(props) {
    super(props);
    this.state = {
      watchingApplication: '',
      status: '',
      error: '',
      applications: [],
      loading: true,
    }
    this.getAllApplications = backendCalls.getAllApplications.bind(this);
    this.selectApplication = backendCalls.selectApplication.bind(this);
    this.changeStatus = backendCalls.changeStatus.bind(this);
    this.getAllApplications(this.props.jwt);
    this.goBackToList = this.goBackToList.bind(this);

  }
  /**
   * Sets the currently watching application sate to '', used to go back to application list view.
   */
  goBackToList() {
    this.setState({ error: '' });
    this.setState({ watchingApplication: '' });
  }
  /**
   * Renders all applications.
   */
  renderApplications() {

    return this.state.applications.map(application => {

      return <ApplicationList selectApplication={this.selectApplication} application={application} />
    })
  }

  render() {
    const status = this.state.status;
    if (!this.state.watchingApplication) {
      return (
        <View style={styles.container}>
          <Text style={styles.welcomeText}>Welcome recruiter {this.props.username}</Text>
          <Text style={styles.errorTextStyle}>
            {this.state.error}
          </Text>
          {this.renderApplications()}
          <Button style={styles.button} onPress={this.props.deleteJWT}>
            Logout
        </Button>
        </View>
      );
    }
    else {
      return (
        <View style={styles.container}>
          <Text style={styles.welcomeText}>Status: {this.state.watchingApplication.status.name}</Text>
          <ScrollView >

            <Application application={this.state.watchingApplication} />
            <View style={styles.form}>
              <Input
                placeholder="New Status"
                label="New Status"
                value={status}
                onChangeText={status => this.setState({ status })}
              />
            </View>
            <Text style={styles.errorTextStyle}>
              {this.state.error}
            </Text>
            <Button onPress={() => this.changeStatus(this.state.status, this.watchingApplication.version)}>Alter status</Button>

          </ScrollView>
          <View style={styles.buttons}>
            <View style={styles.buttonView}>
              <Button onPress={this.goBackToList} style={styles.logout}>
                Go back to List
                    </Button>
            </View>
            <View style={styles.buttonView}>
              <Button onPress={this.props.deleteJWT} style={styles.logout}>
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
    borderBottomWidth: 1,
    borderColor: '#eee',
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
  buttons: {

    flexDirection: 'row',
    left: 0,
  }
  ,
  logout: {
    justifyContent: 'flex-end'
  },
  buttonView: {
    flex: 1,
  },
  errorTextStyle: {
    alignSelf: 'center',
    fontSize: 18,
    color: 'red'
  }

};
