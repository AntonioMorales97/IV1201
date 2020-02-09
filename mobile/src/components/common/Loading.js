import React from 'react';
import { View, ActivityIndicator } from 'react-native';
/**
 * Rendered whenever loading is indicated.
 * @param {} param0 
 */
const Loading = ({ size }) => {
    return (
        <View style={styles.spinnerContainer}>
            <ActivityIndicator size={size} />
        </View>
    );
};
const styles = {
    spinnerContainer: {
        flex: -1,
        marginTop: 12,
        marginBottom: 12,
    },
}
export { Loading };