import { GET_SUCCESS, CLEAR_SUCCESS } from './success-types';

export const returnSuccess = (msg, successId, status, id = null) => {
  return {
    type: GET_SUCCESS,
    payload: { msg, successId, status, id }
  };
};

export const clearSuccess = () => {
  return {
    type: CLEAR_SUCCESS
  };
};
