## Spring-boot-project
A simple Web-based application for student profile management.

	# Java Spring Boot Server Side with PostgreSQL Database

	**Tasted on java version "17.0.1" 2021-10-19 LTS

	- PostgreSQL was used to build the database - "studentdb" with these commands -->

	CREATE DATABASE studentdb;
	GRANT ALL PRIVILEGES ON DATABASE "studentdb" TO postgres;

	*NOTE: Here, "postgres" mentioned on the Granting privileges line - is the username.

	- you will be needing to change the 2 & 3 NO lines from the "./student/src/main/resources/application.properties" file in order to put your username and password so that you can run the application to start the API.
	 OR
	 you can just use the generated "student/target/student-0.0.1-SNAPSHOT.jar" file from the parent directory "Spring-boot-project" with bash command =>

		cd student/target && java -jar ./student-0.0.1-SNAPSHOT.jar

	 to start the API server on http://localhost:8080

	# ReactJs Fontend Side with Material UI

	**Tasted on npm version 8.3.0**

	- Most of the requirment was filled by Material UI's buildin components

	- Didn't touch any css

	- To deploy the fontend side from the parent directory "Spring-boot-project" with bash command =>
	
		cd reactjs-app-for-student-api && npm start
	
	This command will deploy the reactJS app on http://localhost:3000

Command::
 
 npm run deploy

  should deploy both of the server concurrently

