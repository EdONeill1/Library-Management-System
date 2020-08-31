# bibliotech

Bibliotech was a library management system done as a project for a Web Development course in University College Dublin. 

I worked on the backend and created many of the foundations of some of the front end when I was implementing features into the backend.

The technologies used were:
* SpringBoot and Java
* Docker
* Pom


The command to deploy the program from the root folder (before src) was the following:
	 mvn clean && mvn package && docker-compose build && docker-compose up  

I found that the following docker command was useful because sometimes the project wouldn't run: docker system prune 


All backend files are located within src/main/java
All resources (html files, images, etc) are located within src/main/resources

Reservation and renewal functionality is shallow - Once an artefact is reserved it cannot be reserved again but to who reserved it isn't shown because I couldn't figure out a mapping of artefacts to members. The mapping I aimed for was a many to one relationship. Renewal works similar. You can renew a members current loan and it tells you have been successful but that's it. 


