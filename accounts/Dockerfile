#start with a base image containig java run time

FROM openjdk:17-jdk-slim

#information who maintains this docer image
MAINTAINER arpitPathak

#copying the jar from the target
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#executing the application
ENTRYPOINT ["java" , "-jar" ,"accounts-0.0.1-SNAPSHOT.jar"]

