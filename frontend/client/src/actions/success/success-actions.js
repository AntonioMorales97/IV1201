import { GET_SUCCESS, CLEAR_SUCCESS } from './success-types';

/**
 * Constructs a success.
 */
export const returnSuccess = (msg, successId, status, id = null) => {
  return {
    type: GET_SUCCESS,
    payload: { msg, successId, status, id }
  };
};

/**
 * Clears the success.
 */
export const clearSuccess = () => {
  return {
    type: CLEAR_SUCCESS
  };
};
