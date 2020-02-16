import { GET_ERROR, CLEAR_ERROR } from './error-types';

export const returnError = (msg, errorId, status, id = null) => {
  return {
    type: GET_ERROR,
    payload: { msg, errorId, status, id }
  };
};

export const clearError = () => {
  return {
    type: CLEAR_ERROR
  };
};
