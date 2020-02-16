import axios from 'axios';
import {
  GET_APPLICATION,
  GET_APPLICATIONS,
  UPDATE_APPLICATION,
  APPLICATION_ERROR
} from './application-types';

import { returnError } from '../error/error-actions';
//import { returnSuccess } from '../success/success-actions'; !!!!!!!!

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
      dispatch({
        type: APPLICATION_ERROR
      });
      if (err.response.data) {
        dispatch(
          returnError(
            { msg: err.response.data.message },
            err.response.data.code,
            err.response.status,
            APPLICATION_ERROR
          )
        );
      }
      console.log(err);
    });
};

export const updateApplication = (id, version, status) => dispatch => {
  const body = JSON.stringify({ name: status, version });

  axios
    .put(`/alter-status/${id}`, body)
    .then(res => {
      dispatch({
        type: UPDATE_APPLICATION,
        payload: res.data
      });
    })
    .catch(err => {
      if (err.response.data) {
        dispatch(
          returnError(
            { msg: err.response.data.message },
            err.response.data.code,
            err.response.status,
            APPLICATION_ERROR
          )
        );
      }
      console.log(err);
    });
};
