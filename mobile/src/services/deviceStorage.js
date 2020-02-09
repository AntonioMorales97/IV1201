import { AsyncStorage } from 'react-native';
/**
 * A class to handle all the Api calls to the backend. 
 */
const deviceStorage = {
  /**
   *  Loads all applications. SHOULD BE MOVED TO RECRUITER VIEW.
   */
  async loadJWT() {
    try {
      const value = await AsyncStorage.getItem('id_token');
      const username = await AsyncStorage.getItem('username');
      const role = await AsyncStorage.getItem('role');
      if (value !== null) {

        this.setState({
          jwt: value,
          user: username,
          role: role,
          loading: false
        });
      } else {
        this.setState({
          loading: false
        });
      }
    } catch (error) {
      console.log("getItem " + error.message);
    }
  },
  /**
   * saves a key value pair in the local device storage.
   * @param {*} key the key to connect the value to.
   * @param {*} value the acctual value to save.
   */
  async saveItem(key, value) {
    try {
      await AsyncStorage.setItem(key, value);
    } catch (err) {
      console.log("Set item error: " + err.message);
    }
  },
  /**
   * Delte the jwt web token from local storage.
   */
  async deleteJWT() {
    try {
      await AsyncStorage.removeItem('id_token')
        .then(
          () => {
            this.setState({
              jwt: ''
            })
          }
        );
    } catch (error) {
      console.log('AsyncStorage Error: ' + error.message);
    }
  }
};
export default deviceStorage;