
import React from 'react';
import { View, Text } from 'react-native';

/**
 * Class that renders a specific application.
 * @param {*} param0 The application to show.
 */
const Application = ({ application }) => {
    return (
        <View style={styles.container}>

            <View style={styles.list}>
                <Text>
                    <Text style={styles.listHeader}>Id:</Text>
                    <Text style={styles.listItemText}> {application.id} </Text>
                </Text>
            </View>
            <View style={styles.list}>
                <Text>
                    <Text style={styles.listHeader}>Surname: </Text>
                    <Text style={styles.listItemText}> {application.person.firstName} </Text>
                </Text>
            </View>
            <View style={styles.list}>
                <Text>
                    <Text style={styles.listHeader}>Lastname: </Text>
                    <Text style={styles.listItemText}> {application.person.lastName} </Text>
                </Text>
            </View>
            <View style={styles.list}>
                <Text>
                    <Text style={styles.listHeader}>Ssn: </Text>
                    <Text style={styles.listItemText}> {application.person.ssn} </Text>
                </Text>
            </View>
            <View style={styles.list}>
                <Text>
                    <Text style={styles.listHeader}>Email: </Text>
                    <Text style={styles.listItemText}> {application.person.email} </Text>
                </Text>
            </View>
            <Text style={styles.listHeader}>Competence List: </Text>
            {application.competenceProfile.map((competence) => {
                return <View style={styles.list}>
                    <Text>
                        <Text style={styles.listHeader}>Competence:</Text>
                        <Text style={styles.listItemText}> {competence.competence.name}</Text>
                        <Text style={styles.listHeader}>- Years:</Text>
                        <Text style={styles.listItemText}>{competence.yearsOfExperience}</Text>
                    </Text>
                </View>
            })}

            <Text style={styles.listHeader}>Availability List: </Text>

            {application.availability.map((availability) => {
                return <View style={styles.list}>
                    <Text>
                        <Text style={styles.listHeader}>From:</Text>
                        <Text style={styles.listItemText}> {availability.fromDate}</Text>
                        <Text style={styles.listHeader}>- To:</Text>
                        <Text style={styles.listItemText}>{availability.toDate}</Text>
                    </Text>
                </View>
            })}

        </View>
    )
}

const styles = {
    container: {
        top: 50,

    },
    list: {
        padding: 15,
        backgroundColor: '#f8f8f8',
        borderBottomWidth: 1,
        borderColor: '#eee',
    },
    listitem: {
        fontWeight: 'bold',
    },
    listHeader: {
        fontSize: 20,
        padding: 15,
        backgroundColor: '#f8f8f8',
        borderBottomWidth: 1,
        borderColor: '#eee',
        fontWeight: 'bold',
    },
    listItemText: {
        fontSize: 18,
    }
}
export { Application };
