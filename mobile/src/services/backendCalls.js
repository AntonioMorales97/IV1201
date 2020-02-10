import axios from 'axios';
import deviceStorage from './deviceStorage';
/**
 * A class to handle all the device storage. 
 */
const backendCalls = {
  /**
   * Loads the jwt from the device storage.
   */
  async getAllApplications(jwt) {
    axios.get("https://iv1201-backend.herokuapp.com/applications", {
      //axios.get("http://192.168.0.3:8080/applications", {
      headers: {
        Authorization: 'Bearer ' + jwt
      }
    }
    ).then((response) => {
      console.log(response.data._embedded.applicationListResponses);


      this.setState({ applications: response.data._embedded.applicationListResponses })

    }).catch((err) => {
      console.log(err);
      this.setState({ loading: false });
      this.setState({ error: err.response.data.message });
    })
  },
  /**
   * Function to get a speicifc applications data.
   **/

  async selectApplication(id) {
    axios.get("https://iv1201-backend.herokuapp.com/application/" + id, {
      //axios.get("http://192.168.0.3:8080/application/"+id, {
      headers: {
        Authorization: 'Bearer ' + this.props.jwt
      }
    }
    ).then((response) => {
      this.setState({ watchingApplication: response.data })

    }).catch((err) => {
      console.log(err.response.data.message);
      this.setState({ loading: false });
      this.setState({ error: err.response.data.message });
    });
  },
  /**
 * Function that changes the status of a specific application.
 */
  async changeStatus(status) {
    let body = {
      name: status
    }

    axios.put("https://iv1201-backend.herokuapp.com/alter-status/" + this.state.watchingApplication.id, body, {
      headers: {
        Authorization: 'Bearer ' + this.props.jwt
      }
    }
    ).then((response) => {

      this.setState({ watchingApplication: response.data })

    }).catch((err) => {
      console.log(err.response.data.message);
      this.setState({ loading: false });
      this.setState({ error: err.response.data.message });
    });
  },
  /**
  * Function to login a specific user.
   */
  async loginUser() {
    const { username, password } = this.state;
    this.setState({ error: '', loading: 'true' });

    let sendData = {
      username: username,
      password: password
    }

    axios.post("https://iv1201-backend.herokuapp.com/authenticate", sendData)
      //axios.post("http://192.168.0.3:8080/authenticate",sendData)
      .then((response) => {
        deviceStorage.saveItem('username', username);
        deviceStorage.saveItem('role', response.data.role);
        deviceStorage.saveItem("id_token", response.data.jwtToken);
        this.props.newUser(response.data.jwtToken, username, response.data.role);

      }).catch((err) => {

        this.setState({ loading: false });
        this.setState({ error: err.response.data.message });
      });
  },
  /**
   * Function that registers a applicant in the backend database.
   */
  async registerUser() {
    const { surName, lastName, ssn, email, username, password } = this.state;
    this.setState({ error: '', loading: true });


    axios.post("https://iv1201-backend.herokuapp.com/register", {
      //axios.post("http://192.168.0.3:8080/register",{
      firstName: surName,
      lastName: lastName,
      email: email,
      ssn: ssn,
      username: username,
      password: password,
    }).then((response) => {
      console.log("sucess");
      this.setState({ loading: false });
      this.props.authSwitch;

    }).catch((err) => {
      this.setState({ error: err.response.data.message });
      this.setState({ loading: false });
    });
  }
};
export default backendCalls;