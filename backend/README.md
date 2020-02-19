# IV1201 Design of Global Applications (Backend)

[![Build Status](https://travis-ci.org/AntonioMorales97/IV1201.svg?branch=master)](https://travis-ci.org/AntonioMorales97/IV1201)

This is the backend RESTful API of the application. The REST server is built using Maven and is a Spring boot application. The setup of this server in a local environment and how to setup for production using Heroku's cloud server will be
described here.

### Environment variables
* ```EMAIL_PASSWORD``` used for the Java mail sender
* ```JWT_SECRET``` used to set the secret for generating JSON Web Tokens
* ```PROJECT_PATH``` should be set to ```backend``` to deploy from this path
* ```DATABASE_URL``` should be set automatically by Heroku when a Postgres database is created. Otherwise it should be the URI to the database in Heroku.
* ```HEROKU_POSTGRESQL_<COLOR>_URL``` should be set automatically by Heroku when a new database is created. Otherwised it should be the URI to the database (old) with the 
right "color", e.g. ```HEROKU_POSTGRESQL_GREEN_URL```. This variable is needed only if migration of data is going to be used from the old database, thus should be the URI to the old database. 
* ```OLD_DB``` should be set to point to the environment variable that has the URI to the old database. This is because it makes it more flexible when using different names of databases.
E.g. if the Postgres database is of color green, then this variable should be set to ```HEROKU_POSTGRESQL_GREEN_URL```, i.e. point to the variable above.

### Migration
Since this whole application is an upgrade of an older recruitment system, the data in the old database needs to be migrated to the new system's database.
The migration expects an old Postgres database to exist and the connection to be given by an URI in an environment variable (See the section about environment variables). If no such old database exists,
a minimal copy of the old database can be created by the following script: [old_database](./src/main/resources/old_database.sql). 
The migration is automatic and will take care of the rest.

### Local setup
Most of the environment variables needs to be set for it to work locally, but not all. For example, the DATABASE_URL does not need to be set since the ```application.properties``` holds configurations for a database not created by Heroku. See also the section about versions.
* Create a Postgres database with the required configurations in the ```application.properties``` file (or change the configurations).
* If migration will be needed, read the section about migration.
* Install and build the project with maven
* Start the main function and the application will be started on localhost:8080.
  
**OBS!** This is a REST server, hence no view will be served to the clients here. If using without a frontend, use an HTTP client like Postman to the different endpoints in the controllers in the Presentation layer (see section about code structure).

### Production setup
This backend has been deployed to Heroku. Heroku will automatically notice the Spring application and that a Postgres database is used and take care of everything. Heroku will:
* Create the ```DATABASE_URL``` environment variable
* Create a Postgres database
* Configure so JPA uses the new database instead of the one configured in ```application.properties```.

**OBS!** To use migration read the section about migration. One example is to create an additional database that represents the old database and set up the needed environment variables.

The needed steps for deploying to Heroku are:
* Set buildpack to deploy from this path (backend). The buildpack is ```https://github.com/timanovsky/subdir-heroku-buildpack.git``` and should be placed first in the buildpack chain of Heroku
* Set up required environment variables (see section about environment variables)
* Deploy to Heroku

### Running tests
The testing library is JUnit4 and the tests are unit tests. To run the tests simply make sure that the test runner is JUnit4
and run the main test (or run each test individually).

### Code structure, Architecture and Design
The code follows the Domain Driven Design (DDD) principles with a layered architecture. The layers are: Presentation, Application, Domain, 
and Repository. The presentation holds the REST controllers that will receive HTTP requests and call the appropriate service in 
the Application layer. The application layer thus holds the services and will use the Domain models to run operations and possibly
store data in the database using repositories from the Repository layer. Furthemore, classes that don't belong in any of the mentioned
layers, have their own "layer"/folder where the name specifies its functionality for higher cohesion.
  
The tests has similar structure to the code they are testing. This makes it easier for navigation and easier to understand and scale.

### Versions
The different versions can be seen the ```pom.xml``` file of the project. However, some of the versions of the bigger components
will be listed here:
* ```Java 11```
* ```JUnit 4```
* ```Maven 4```

### License
MIT
