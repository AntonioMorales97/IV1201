import {
  GET_APPLICATION,
  APPLICATION_ERROR,
  CLEAR_APPLICATION,
  UPDATE_APPLICATION,
  GET_APPLICATIONS
} from '../../actions/application/application-types';

const initialState = {
  application: null,
  applications: [],
  loading: true,
  error: {}
};

export default function(state = initialState, action) {
  const { type, payload } = action;
  switch (type) {
    case GET_APPLICATION:
    case UPDATE_APPLICATION:
      return {
        ...state,
        application: payload,
        loading: false
      };
    case GET_APPLICATIONS:
      return {
        ...state,
        applications: payload,
        loading: false
      };
    case CLEAR_APPLICATION:
      return {
        ...state,
        application: null,
        loading: false
      };
    case APPLICATION_ERROR:
      return {
        ...state,
        error: payload,
        loading: false,
        application: null
      };
    default:
      return state;
  }
}
