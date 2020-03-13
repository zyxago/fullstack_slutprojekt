# Recipe List Fullstack Project

## To run project

### Database
1. Set up a mysql server to host the db schema on
2. Import the .sql file located in the database folder

### Backend

1. Set up a wildfly/glassfish server to deploy the web application on
2. Configure a config.properties file to have the right values to be able to connect to your db-server
   the config file should have the following fields
   <br>`db_password=`
   <br>`db_username=`
   <br>`db_host=`
   <br>`db_schema=`

### Frontend

1. In app.js change the variable `mainPath` to your deployed backend path (example:`fullstack_slutprojekt-1.0`)
1. Build the frontend side of the webapp by running the following script in the frontend folder <br>`npm build`
   the build folder will then be mapped to the webapp folder   
