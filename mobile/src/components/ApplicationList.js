import React from 'react';
import {View, Text, TouchableOpacity} from 'react-native';
/**
 * Class that renders a application in the applicationList view.
 * @param {*} param0 
 */
const ApplicationList = ({application, selectApplication}) => {
    
    return (
        <TouchableOpacity style ={styles.list} onPress={() => selectApplication(application.id)} >
            <View style ={styles.listItemView}>
                <Text styles={styles.listItemText}>
                    {application.firstName}
                </Text>
                <Text styles={styles.listItemText}>
                    {application.lastName}
                </Text>
                <Text styles={styles.listItemText}>
                    {application.creationDate}
                </Text>
            </View>
        </TouchableOpacity>
    )
}

const styles ={
    list:{
        padding:15,
        backgroundColor: '#f8f8f8',
        borderBottomWidth:1,
        borderColor:'#eee',
    },
    listItemView:{
        flexDirection:'row',
        justifyContent:'space-between',
        alignItems: 'center',
    },
    listItemText:{
        fontSize: 18,
    }
}
export { ApplicationList };