Quick Start
====
```
$ cd backend
$ mvn package exec:java   # builds project, executes tests, starts the application
$ firefox ../frontend/default.html   # navigate to frontend application
```

That's it! The application will use pre-populated mysql database hosted on _db4free.net_.

Build instructions in detail
--------------
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

Questions
--------------
##### Why there are separate projects for front-end and back-end?
I prefer to have independent modules for frontend and backend. It has several advantages:
* each project can be developed by independent teams within it's own repository and own release cycle
* frontend and backend application can be deployed on different hosts (eg. frontend application can be served from _CDN_ servers)

##### What has been changed in front-end?
Not much. I have added configurable REST URL. I have also improved user experience when saving a new entry - there is a visual feedback after the item has been saved and rather then reloading the entire list, it is updated only with the new item.

##### Why _JDBI_ and not some _ORM_ framework like _Hibernate?_
For simpicity. I find it very easy to setup and it is more than sufficient for the purpose of this coding test.

##### Why did you use _Lombok_?
To remove boilerplate code like getters, all-args constructors or builders and make it more readable.

##### Where can I change database connection properties?
In `backend/src/main/config/app.yml`. _VAT_ rate is also configurable there.

##### How come the integration tests execute without mysql database?
They use _Apache Derby_ with _memory_ storage.

##### What is the code coverage of the tests?
With unit and acceptance tests - it's 100%.

##### Why can't I produce a deployable _war_ archive?
I assummed that it was not in scope of the task and I rather focused on making it easy to run with a single command. Though, I can add support for this if requested. 