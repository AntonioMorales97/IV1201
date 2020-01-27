import { GET_ERROR, CLEAR_ERROR } from '../../actions/error/error-types';

const initialState = {
  msg: {},
  status: null,
  id: null
};

export default function(state = initialState, action) {
  const { type, payload } = action;
  switch (type) {
    case GET_ERROR:
      return {
        msg: payload.msg,
        status: payload.status,
        id: payload.id
      };
    case CLEAR_ERROR:
      return {
        msg: {},
        status: null,
        id: null
      };
    default:
      return state;
  }
}
