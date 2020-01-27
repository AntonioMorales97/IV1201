//import axios from 'axios';
import {
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  LOGOUT_FAIL,
  LOGOUT_SUCCESS
} from './auth-types';
import { returnError } from '../error/error-actions';
import { returnSuccess } from '../success/success-actions';

export const login = ({ email, password }) => dispatch => {
  if (!email || !password) {
    dispatch(returnError({ msg: 'Please enter all fields' }, 400, LOGIN_FAIL));
    return;
  }

  const res = { user: { email, password, role: 'admin' } };
  const body = JSON.stringify(res.user);

  /**
   * Axios below...
   */
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
