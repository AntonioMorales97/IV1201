import axios from 'axios';

/**
 * Sets the Authorization header to: 'Bearer ' + token.
 * If the backend expects another header or format, change it here.
 */
export const setAuthToken = token => {
  if (token) {
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  } else {
    delete axios.defaults.headers.common['Authorization'];
  }
};

/**
 * Deletes the Authorization header.
 */
export const deleteAuthToken = () => {
  delete axios.defaults.headers.common['Authorization'];
};
