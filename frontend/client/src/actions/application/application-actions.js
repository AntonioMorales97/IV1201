import axios from 'axios';
import { GET_APPLICATION } from './application-types';

//import { returnError } from '../error/error-actions';
//import { returnSuccess } from '../success/success-actions';

export const getApplications = () => dispatch => {
  axios
    .get('/applications')
    .then(res => {
      console.log(res);
    })
    .catch(err => {
      console.log(err);
    });
};

export const getApplication = id => dispatch => {
  console.log('TODO...');
  console.log('Retrieve application with id: ' + id);
  //Mock
  const application = {
    id: id,
    firstName: 'Pelle',
    lastName: 'Polle',
    date_of_birth: '1997-06-02',
    email: 'pelle@kth.se',
    status: 'accepted',
    experiences: [
      { id: 1, name: 'Korvgrillning' },
      { id: 2, name: 'Karuselldrift' }
    ],
    availabilities: [
      { id: 1, from: '2020-02-10', to: '2020-03-10' },
      { id: 2, from: '2020-06-10', to: '2020-07-30' }
    ]
  };

  //Axios
  dispatch({
    type: GET_APPLICATION,
    payload: application
  });
};

export const updateApplication = (id, status) => dispatch => {
  console.log('Update application with id: ' + id + ' to status: ' + status);
};
