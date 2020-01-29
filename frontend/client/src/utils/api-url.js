const baseURL =
  process.env.NODE_ENV === 'production'
    ? process.env.REST_URL
    : 'http://localhost:8080';

export default baseURL;
