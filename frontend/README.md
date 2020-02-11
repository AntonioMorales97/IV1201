# IV1201 Design of Global Applications (Frontend)

[![Build Status](https://travis-ci.org/AntonioMorales97/IV1201.svg?branch=master)](https://travis-ci.org/AntonioMorales97/IV1201)

This is the frontend part of the Recruitment application which uses the REST API (backend) to present data to the users.
It consists of a minimal express server used to serve the frontend on any HTTP requests. The frontend is a Single Page Application (SPA) built using React's ```create-react-app```.

### Local setup
```
npm install
```
```
npm start
```
The SPA should be opened in localhost:3000.

### Production setup
The SPA is ready for deployment. This frontend has been deployed on Heroku's cloud service. 
Needed environment variables: 
  
```NPM_CONFIG_PRODUCTION``` should be set to ```false```.  
```PROJECT_PATH``` should be set to ```frontend```.  
```REACT_APP_REST_URL``` should be set to the URL of the REST API.  
  
**OBS!** Heroku should be set to deploy from this repository and thus needs an additional buildpack to deploy from this path (frontend). The buildpack is ```https://github.com/timanovsky/subdir-heroku-buildpack.git``` and should be placed first in the buildpack chain of Heroku.

### Running tests
The testing library is Jest and comes with React's create-react-app. All the files ending with ```.test.js``` will be automatically run by Jest.  
  
```npm run test```
