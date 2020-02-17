# IV1201 Design of Global Applications (Mobile)

[![Build Status](https://travis-ci.org/AntonioMorales97/IV1201.svg?branch=master)](https://travis-ci.org/AntonioMorales97/IV1201)

This is the mobile prototype part of the Recruitment application which uses the REST API (backend) to present data to the users.
It consists of a react-native application created with ```npx react-native init -projectname- ```.

### Recommended Software for local setup
```

Android Studios - setup like recommended on https://facebook.github.io/react-native/docs/getting-started
```
```

Nodejs 8.3 or newer
```
```

python2 
```
```

jdk8 8 or newer
```
### Local setup
```
Open virtual device fron android studios (Pixel 2 API 29).
```
```
npx react-native run-android
```
The react-native app should deploy to the Pixel 2 emulator.


### Running tests
No tests have been setup, since this is a prototype.

### Code structure
The code for the view follows React's component based architecture. This means that everything in the screen is a component or is a part of a component. The props can be functions, states, and other types of data. This gives a higher cohesion and becomes easier to maintain and understand. The map structure follows Components which holds the global components, screens which are the different views of the application and services which contains helper modules. 

### Disclaimer 
This is a Simple prototype and code and architecture may have to be reevaluated for future projects to keep the code well structured and easy to work with.


### Versions
The different versions can be seen in the ```package.json``` files. Some versions will be listed here:
* ```npx``` 6.9.0
* ```node``` 12.1.0
* ```react``` 16.9.0
* ```react-native``` 0.61.5

### License 
MIT
