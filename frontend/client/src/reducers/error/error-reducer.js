import { GET_ERROR, CLEAR_ERROR } from '../../actions/error/error-types';

/**
 * Store related to errors in this SPA.
 */
const initialState = {
  msg: {},
  errorId: null,
  status: null,
  id: null
};

/**
 * Updates the store accordingly.
 */
export default function(state = initialState, action) {
  const { type, payload } = action;
  switch (type) {
    case GET_ERROR:
      return {
        msg: payload.msg,
        errorId: payload.errorId,
        status: payload.status,
        id: payload.id
      };
    case CLEAR_ERROR:
      return {
        msg: {},
        errorId: null,
        status: null,
        id: null
      };
    default:
      return state;
  }
}
