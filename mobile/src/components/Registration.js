import React, { Component, Fragment } from 'react';
import { View, Text } from 'react-native';
import { Input, TextLink, Button, Loading } from './common';

import backendCalls from '../services/backendCalls';
/**
 * A registration class, that handles rendering of the registration view, aswell as registering a person..
 */
class Registration extends Component {
    constructor(props) {
        super(props);
        this.state = {
            surName: '',
            lastName: '',
            ssn: '',
            email: '',
            username: '',
            password: '',
            error: '',
            loading: false
        };
        this.registerUser = backendCalls.registerUser.bind(this);
    }
  
    render() {
        const { surName, lastName, ssn, email, username, password, error, loading } = this.state;
        const { form, section } = styles;
        return (
            <Fragment>
                <View style={form}>
                    <View style={section}>
                        <Input placeholder="Surename"
                            label="surName"
                            value={surName}
                            onChangeText={surName => this.setState({ surName })}
                        />
                    </View>
                    <View style={section}>
                        <Input placeholder="Lastname" label="lastName" value={lastName} onChangeText={lastName => this.setState({ lastName })} />
                    </View>
                    <View style={section}>
                        <Input placeholder="social security number" label="ssn" value={ssn} onChangeText={ssn => this.setState({ ssn })} />
                    </View>
                    <View style={section}>
                        <Input placeholder="email" label="email" value={email} onChangeText={email => this.setState({ email })} />
                    </View>
                    <View style={section}>
                        <Input placeholder="username" label="username" value={username} onChangeText={username => this.setState({ username })} />
                    </View>
                    <View style={section}>
                        <Input secureTextEntry placeholder="password" label="password" value={password} onChangeText={password => this.setState({ password })} />
                    </View>
                    <Text style={styles.errorTextStyle}>
                        {error}
                    </Text>
                    {!loading ?
                        <Button onPress={this.registerUser}>
                            Register
                </Button>
                        :
                        <Loading size={'large'} />}
                </View>
                <TextLink onPress={this.props.authSwitch}>
                    Already have an account? Log in!
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
export { Registration };