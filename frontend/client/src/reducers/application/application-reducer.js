import {
  GET_APPLICATION,
  APPLICATION_ERROR,
  CLEAR_APPLICATION,
  UPDATE_APPLICATION,
  GET_APPLICATIONS
} from '../../actions/application/application-types';

const initialState = {
  application: null,
  applications: [
    {
      id: 2,
      firstName: 'Per',
      lastName: 'Strand',
      email: 'per@strand.kth.se',
      status: 'accepted',
      _links: {
        self: 'http://localhost:8080/application/2'
      }
    },
    {
      id: 3,
      firstName: 'Per',
      lastName: 'Strand',
      email: 'per@strand.kth.se',
      status: 'rejected',
      _links: {
        self: 'http://localhost:8080/application/3'
      }
    },
    {
      id: 4,
      firstName: 'Per',
      lastName: 'Strand',
      email: 'per@strand.kth.se',
      status: 'unhandled',
      _links: {
        self: 'http://localhost:8080/application/4'
      }
    }
  ],
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
        profiles: payload,
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
