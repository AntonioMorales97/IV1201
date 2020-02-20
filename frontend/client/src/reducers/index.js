import { combineReducers } from 'redux';
import authReducer from './auth/auth-reducer';
import errorReducer from './error/error-reducer';
import successReducer from './success/success-reducer';
import applicationReducer from './application/application-reducer';

/**
 * Combines the reducers to one store.
 */
export default combineReducers({
  auth: authReducer,
  error: errorReducer,
  success: successReducer,
  application: applicationReducer
});
