import React from 'react';
import {View, Text, TouchableOpacity} from 'react-native';
/**
 * A button commonly used in the application.
 * @param {} param0 
 */
const Button=({
    onPress, children    
}) =>{
    const {button, text} = styles;
    return(
        <View style ={{flexDirection:'row'}}> 
            <TouchableOpacity onPress={onPress} style ={button}>
                <Text style ={text}>
                    {children}
                </Text>
            </TouchableOpacity>
        </View>
    );
};

const styles={
    text:{
        alignSelf:'center',
        color:'white',
        fontSize: 18,
        fontWeight: '700',
        paddingTop: 10,
        paddingBottom:10,
    },
    button:{
        flex:1,
        borderWidth:2,
        borderCOlor:'mediumpurple',
        backgroundColor: "rebeccapurple",
        borderRadius: 25,
        marginTop: 20,
        marginLeft: 100,
        marginRight: 100,
        marginBottom: 20,
        minWidth:200,
        minHeight:20,
        
    },
};
export { Button };