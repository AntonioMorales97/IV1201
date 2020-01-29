import axios from 'axios';
import {
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  LOGOUT_FAIL,
  LOGOUT_SUCCESS,
  REGISTER_SUCCESS,
  REGISTER_FAIL
} from './auth-types';
import { returnError } from '../error/error-actions';
import { returnSuccess } from '../success/success-actions';

export const login = ({ username, password }) => dispatch => {
  if (!username || !password) {
    dispatch(returnError({ msg: 'Please enter all fields' }, 400, LOGIN_FAIL));
    return;
  }

  //const res = { user: { email, password, role: 'admin' } };
  const body = JSON.stringify({ username, password });

  axios
    .post('/authenticate', body)
    .then(res => {
      dispatch({
        type: LOGIN_SUCCESS,
        payload: { user: { role: 'admin' } } // Must be i payload of response from server
      });
    })
    .catch(err => {
      dispatch(
        returnError(
          { msg: err.response.data.message },
          err.response.status,
          LOGIN_FAIL
        )
      );
      dispatch({ type: LOGIN_FAIL });
    });

  /*
  console.log(body);
  if (email === 'pelle@kth.se' && password === '123123') {
    dispatch(
      returnSuccess(
        {
          msg: 'Login success'
        },
        200,
        LOGIN_SUCCESS
      )
    );
    dispatch({
      type: LOGIN_SUCCESS,
      payload: res
    });
  } else {
    dispatch(returnError({ msg: 'Wrong credentials' }, 400, LOGIN_FAIL));
    dispatch({ type: LOGIN_FAIL });
  }
  */
};

export const logout = () => dispatch => {
  /**
   * Axios below...
   */
  dispatch({ type: LOGOUT_SUCCESS });
  /**
   * or fail
   */
  console.log('Logout fail is not used' + LOGOUT_FAIL);
};

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
      returnError({ msg: 'Please enter all fields' }, 400, REGISTER_FAIL)
    );
    return;
  }

  if (ssn.length < 10) {
    dispatch(
      returnError({ msg: 'SSN must be atleast 10 digits' }, 400, REGISTER_FAIL)
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
          { msg: 'Successful registration! Please login' },
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
          err.response.status,
          REGISTER_FAIL
        )
      );
      dispatch({
        type: REGISTER_FAIL
      });
    });
};
