
import React from 'react';
import {View, Text, ScrollView} from 'react-native';

/**
 * Class that renders a specific application.
 * @param {*} param0 The application to show.
 */
const Application = ({application}) => {   

 
    return (
        <View style={styles.container}>
        <View style={styles.list}>
            
                <Text styles={styles.listItemText}>
                      Id: {application.id} 
                </Text>
       </View>
        <View style={styles.list}>
            
                <Text styles={styles.listItemText}>
                      Surname: {application.firstName} 
                </Text>
       </View>
        <View style={styles.list}>
            
                <Text styles={styles.listItemText}>
                     Lastname: {application.lastName} 
                </Text>
       </View>
       <View style={styles.list}>
            
                <Text styles={styles.listItemText}>
                      Ssn: {application.ssn} 
                </Text>
       </View>
       <View style={styles.list}>
            
                <Text styles={styles.listItemText}>
                     Email: {application.email} 
                </Text>
       </View>
       <View style={styles.listHeader}> 
            <Text>Competence List: </Text>
        </View>
                {application.competenceProfile.map((competence)=>{
           return <View style={styles.list}> 
           <Text>{competence.competence.name } - 
             {competence.yearsOfExperience}</Text>
            </View>
        })}
       <View style={styles.listHeader}> 
            <Text>Availability List: </Text>
        </View>
       {application.availability.map((availability)=>{
           return <View style={styles.list}> 
           <Text>From: {availability.fromDate } - To:
             {availability.toDate}</Text>
            </View>
        })}
        <View style={styles.list}>
            
            <Text styles={styles.listItemText}>
                Status: {application.email} 
            </Text>
            
        </View>
        <View style={styles.list}>
            
            <Text styles={styles.listItemText}>
                Status: {application.email} 
            </Text>
            
        </View>
       </View>
    )
}

const styles ={
    container:{
        top: 50,
       
    },
      list:{
        padding:15,
        backgroundColor: '#f8f8f8',
        borderBottomWidth:1,
        borderColor:'#eee',
    },
    listHeader:{
        padding:15,
        backgroundColor: '#f8f8f8',
        borderBottomWidth:1,
        borderColor:'#eee',
        fontWeight: 'bold',
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
export { Application };
