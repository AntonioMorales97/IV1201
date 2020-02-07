import {AsyncStorage} from 'react-native';
const deviceStorage={
    async loadJWT(){
        try{
            const value = await AsyncStorage.getItem('id_token');
            if(value !== null){
                this.setState({
                    jwt: value,
                    loading:false
                });
            }else{
                this.setState({
                    loading:false
                });
            }
        }catch(error){
            console.log("getItem "+ error.message);
        }
    },
    async saveItem(key, value){
        try{
            await AsyncStorage.setItem(key, value);
        }catch(err){
            console.log("Set item error: "+err.message);
        }
    },
     async deleteJWT() {
    try{
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