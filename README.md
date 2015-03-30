Build instructions (30 seconds guide)
====
```
$ cd backend
$ vim src/main/config/app.yml   # provide connection details to your mysql database
$ mysql db_name < src/sql/mysql-schema.sql   # execute schema creation script
$ mvn package exec:java   # builds project, executes tests, starts the application
$ firefox ../frontend/default.html   # navigate to frontend application
```

Build instructions
====
The project consists of two subfolders:
 * `/frontend` - containing front-end application
 * `/backend` - containing REST API application

**Frontend** sub-project comes with prebuilt application and to run it, it's enough to open `/frontend/default.html` directly from the filesystem - it will use `http://localhost:8080` for _REST API_ - it is configurable in `/frontend/src/js/apps/codingtest/config.js`.

**Backend** application can be built and run with _maven_ by executing the following command in `/backend` directory:
```
$ mvn package exec:java
```
It will build the application, execute unit and acceptance tests and run the project on port `8080`. Before launching the application, it is required to supply database connection details in `backend/src/main/config/app.yml` and to create database schema by executing `backend/src/sql/mysql-schema.sql` script. 

Technologies used
--------------
Frameworks and libraries used by **backend** application include:
* Dropwizard
* Guice
* JDBI
* Lombok