import axios from 'axios';
import {
  GET_APPLICATION,
  GET_APPLICATIONS,
  UPDATE_APPLICATION,
  APPLICATION_ERROR,
  UPDATE_APPLICATION_SUCCESS
} from './application-types';
import { returnError } from '../error/error-actions';
import { returnSuccess } from '../success/success-actions';

/**
 * Gets all the applications from the backend.
 */
export const getApplications = () => dispatch => {
  axios
    .get('/applications')
    .then(res => {
      dispatch({
        type: GET_APPLICATIONS,
        payload: res.data._embedded.applicationMetadataResponses
      });
    })
    .catch(err => {
      console.log(err);
    });
};

/**
 * Get a specific application from the backend.
 */
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

/**
 * Updates a specific application with the given status.
 */
export const updateApplication = (id, version, status) => dispatch => {
  const body = JSON.stringify({ name: status, version });

  axios
    .put(`/alter-status/${id}`, body)
    .then(res => {
      dispatch(
        returnSuccess(
          { msg: 'Successfully updated application!' },
          2,
          res.status,
          UPDATE_APPLICATION_SUCCESS
        )
      );
      dispatch({
        type: UPDATE_APPLICATION,
        payload: res.data
      });
    })
    .catch(err => {
      if (err.response) {
        if (err.response.status === 409) {
          if (err.response.data.application) {
            dispatch({
              type: UPDATE_APPLICATION,
              payload: err.response.data.application
            });
          }
          dispatch(
            returnError(
              { msg: err.response.data.message },
              err.response.data.code,
              err.response.status,
              APPLICATION_ERROR
            )
          );
        } else {
          console.log(err.response);
        }
      } else {
        console.log(err);
      }
    });
};
