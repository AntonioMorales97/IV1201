import axios from 'axios';
import {
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  LOGOUT,
  REGISTER_SUCCESS,
  REGISTER_FAIL
} from './auth-types';
import { returnError } from '../error/error-actions';
import { returnSuccess } from '../success/success-actions';
import { setAuthToken, deleteAuthToken } from '../../utils/config-auth-token';

/**
 * Authenticates a user.
 */
export const login = ({ username, password }) => dispatch => {
  if (!username || !password) {
    dispatch(returnError({ msg: 'Enter all fields!' }, 1, 400, LOGIN_FAIL));
    return;
  }

  const body = JSON.stringify({ username, password });

  axios
    .post('/authenticate', body)
    .then(res => {
      setAuthToken(res.data.jwtToken);
      dispatch({
        type: LOGIN_SUCCESS,
        payload: { user: { role: res.data.role }, token: res.data.jwtToken }
      });
    })
    .catch(err => {
      dispatch(
        returnError(
          { msg: err.response.data.message },
          err.response.data.code,
          err.response.status,
          LOGIN_FAIL
        )
      );
      dispatch({ type: LOGIN_FAIL });
    });
};

/**
 * Logs out a user.
 */
export const logout = () => dispatch => {
  deleteAuthToken();
  dispatch({ type: LOGOUT });
};

/**
 * Registers a new user.
 */
export const register = ({
  firstName,
  lastName,
  ssn,
  username,
  email,
  password
}) => dispatch => {
  if (!firstName || !lastName || !ssn || !username || !email || !password) {
    dispatch(
      returnError({ msg: 'Please enter all fields!' }, 10, 400, REGISTER_FAIL)
    );
    return;
  }

  if (ssn.length < 10) {
    dispatch(
      returnError(
        { msg: 'SSN must be atleast of length 10!' },
        11,
        400,
        REGISTER_FAIL
      )
    );
    return;
  }

  const body = JSON.stringify({
    firstName,
    lastName,
    ssn,
    username,
    email,
    password
  });

  axios
    .post('/register', body)
    .then(res => {
      dispatch(
        returnSuccess(
          { msg: 'Successful registration!' },
          1,
          res.status,
          REGISTER_SUCCESS
        )
      );
      dispatch({
        type: REGISTER_SUCCESS
      });
    })
    .catch(err => {
      dispatch(
        returnError(
          { msg: err.response.data.message },
          err.response.data.code,
          err.response.status,
          REGISTER_FAIL
        )
      );
      dispatch({
        type: REGISTER_FAIL
      });
    });
};
