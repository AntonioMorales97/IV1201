import axios from 'axios';
import {
  GET_APPLICATION,
  GET_APPLICATIONS,
  UPDATE_APPLICATION
} from './application-types';

//import { returnError } from '../error/error-actions';
//import { returnSuccess } from '../success/success-actions';

export const getApplications = () => dispatch => {
  axios
    .get('/applications')
    .then(res => {
      dispatch({
        type: GET_APPLICATIONS,
        payload: res.data._embedded.applicationListResponses
      });
    })
    .catch(err => {
      console.log(err);
    });
};

export const getApplication = id => dispatch => {
  axios
    .get(`/application/${id}`)
    .then(res => {
      dispatch({
        type: GET_APPLICATION,
        payload: res.data
      });
    })
    .catch(err => {
      console.log(err);
    });
};

export const updateApplication = (id, status) => dispatch => {
  const body = JSON.stringify({ name: status });

  axios
    .put(`/alter-status/${id}`, body)
    .then(res => {
      dispatch({
        type: UPDATE_APPLICATION,
        payload: res.data
      });
    })
    .catch(err => {
      console.log(err);
    });
};
