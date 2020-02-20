/**
 * The URL used to the backend. If it is in production
 * the environment variable 'REACT_APP_REST_URL' should be set to the backend REST API. In development mode,
 * the backend REST API is expected to run on localhost:8080; if not, changed it here.
 */
const baseURL =
  process.env.NODE_ENV === 'production'
    ? process.env.REACT_APP_REST_URL
    : 'http://localhost:8080';

export default baseURL;
