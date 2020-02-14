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

### Code structure
The code for the view follows React's component based architecture. This means that everything in the view is a component or is a part of a component. The components follow the container component pattern, meaning that the logic and view in a component is split into a container and a view file respectively. In other words: the view part of a component will render and use props given by the container, following React's one-way data flow. The props can be functions, states, and other types of data. This gives a higher cohesion and becomes easier to maintain and understand. Furthermore, the directory structure for components keeps every related component together. For example if a component is only used within and other component, then it should be in its subfolder since it is its child component. The ```index.js``` in every component folder is for exporting the "main" component, which is described by the name of the folder.  
  
The tests are kept together with the component they are testing and has the same name as the specific file they are testing except it  ends with ```.test.js``` so it can be run by Jest.  
  
#### Redux
The redux store is provided at the App level, making it available for every component that imports redux and connects to it. The redux stores are called reducers and are placed in a seperate folder from the components. The different reducers are for keeping related things together for a higher cohesion. The different actions that can be called from components are also kept in a seperate folder like the reducers which also exports action types for the reducers to use and store retrieved data that comes with every different action. The action are (mostly) calls to the backend REST API with axios. The flow from a component to an updated store could be described with CARS: Component -> Action -> Reducer -> Store.

#### i18n (internationalization)
The internationalization uses ```i18n.js```, configured at root level and imported in ```index.js```, making at available for every component that imports from ```react-i18next```. The different languages are stored in the locales-folder in JSON files.
