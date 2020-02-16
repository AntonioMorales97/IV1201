import {
  GET_SUCCESS,
  CLEAR_SUCCESS
} from '../../actions/success/success-types';

const initialState = {
  msg: {},
  successId: null,
  status: null,
  id: null
};

export default function(state = initialState, action) {
  const { type, payload } = action;
  switch (type) {
    case GET_SUCCESS:
      return {
        msg: payload.msg,
        successId: payload.successId,
        status: payload.status,
        id: payload.id
      };
    case CLEAR_SUCCESS:
      return {
        msg: {},
        successId: null,
        status: null,
        id: null
      };
    default:
      return state;
  }
}
